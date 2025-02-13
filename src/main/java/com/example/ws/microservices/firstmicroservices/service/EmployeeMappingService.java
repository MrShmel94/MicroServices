package com.example.ws.microservices.firstmicroservices.service;

import com.example.ws.microservices.firstmicroservices.dto.EmployeeDTO;
import com.example.ws.microservices.firstmicroservices.request.CreateEmployeeRequest;
import com.example.ws.microservices.firstmicroservices.response.CreateEmployeeResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface EmployeeMappingService {

    Optional<EmployeeDTO> findByExpertis(String expertis);
    List<EmployeeDTO> findByExpertisIn(List<String> expertisList);

    CreateEmployeeResponse createEmployees(@Valid List<CreateEmployeeRequest> createEmployeeRequests);
}
