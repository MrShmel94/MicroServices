package com.example.ws.microservices.firstmicroservices.controller;

import com.example.ws.microservices.firstmicroservices.configuration.FileUploadConfig;
import com.example.ws.microservices.firstmicroservices.customError.MissingHeadersException;
import com.example.ws.microservices.firstmicroservices.dto.performance.gd.ClearPerformanceGDDto;
import com.example.ws.microservices.firstmicroservices.dto.performance.gd.PerformanceRowDTO;
import com.example.ws.microservices.firstmicroservices.service.performance_gd.CheckHeaderService;
import com.example.ws.microservices.firstmicroservices.service.performance_gd.ClearPerformanceEmployeeService;
import com.example.ws.microservices.firstmicroservices.service.performance_gd.PerformanceService;
import com.example.ws.microservices.firstmicroservices.service.redice.RedisCacheService;
import com.example.ws.microservices.firstmicroservices.serviceImpl.performance_gd.FileUploadService;
import com.example.ws.microservices.firstmicroservices.serviceImpl.performance_gd.PerformanceServiceImpl;
import com.example.ws.microservices.firstmicroservices.utils.XlsxParserGDUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/download")
@RequiredArgsConstructor
public class DownloadController {


    private final FileUploadService fileUploadService;
    private final FileUploadConfig fileUploadConfig;
    private final PerformanceServiceImpl performanceService;
    private final ClearPerformanceEmployeeService clearPerformanceEmployeeService;
    private final RedisCacheService redisCacheService;

    @GetMapping("/getPerformance")
    public List<PerformanceRowDTO> getGet(@RequestParam("start") LocalDate start,
                                          @RequestParam("end") LocalDate end){
        return performanceService.getPerformanceData(start, end);
    }

    @GetMapping("/getClearPerformance")
    public Map<String, List<ClearPerformanceGDDto>> getGetGet(@RequestParam("start") LocalDate start,
                                                           @RequestParam("end") LocalDate end){
        var test = clearPerformanceEmployeeService.getAllPerformanceEmployee(start, end);
        for(Map.Entry<String, List<ClearPerformanceGDDto>> entry : test.entrySet()){
            redisCacheService.saveToCacheWithTTL("Performance" + entry.getKey(), entry.getValue(), Duration.ofMinutes(10));
        }

        //redisCacheService.saveToCacheWithTTL("PerformanceNon" + "304917", test.get("304917"), Duration.ofMinutes(30));
        Optional<List<ClearPerformanceGDDto>> optList = redisCacheService.getFromCache(
                "PerformanceNon304917",
                new TypeReference<List<ClearPerformanceGDDto>>() {}
        );
        List<ClearPerformanceGDDto> list = optList.orElse(Collections.emptyList());
        return test;
    }


    /**
     * Upload multiple files for performance data processing.
     *
     * @param files a list of uploaded files
     * @return a list of results (success or error messages)
     */
    @Operation(summary = "Upload performance files", description = "Receives one or more files to parse and store via COPY.")
    @PostMapping("/uploadPerformanceGD")
    public ResponseEntity<List<String>> uploadFile(
            @Parameter(description = "List of files to upload")
            @RequestParam("file") List<MultipartFile> files
    ) {
        if (files.size() > fileUploadConfig.getMaxFileCount()) {
            return ResponseEntity.badRequest()
                    .body(List.of("Too many files uploaded. Maximum allowed: " + fileUploadConfig.getMaxFileCount()));
        }

        List<String> results = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.getSize() > fileUploadConfig.getMaxFileSize()) {
                results.add(file.getOriginalFilename() + " - Error: File size exceeds limit of "
                        + (fileUploadConfig.getMaxFileSize() / (1024 * 1024)) + " MB");
                 continue;
            }

            try {
                fileUploadService.handleUpload(file);
                results.add(file.getOriginalFilename() + " - Successfully processed.");
            } catch (Exception e) {
                log.error("Failed to process file {} : {}", file.getOriginalFilename(), e.getMessage());
                results.add(file.getOriginalFilename() + " - Error: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(results);
    }
}
