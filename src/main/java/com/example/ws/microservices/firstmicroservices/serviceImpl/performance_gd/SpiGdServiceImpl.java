package com.example.ws.microservices.firstmicroservices.serviceImpl.performance_gd;

import com.example.ws.microservices.firstmicroservices.entity.performance_gd.SpiGd;
import com.example.ws.microservices.firstmicroservices.repository.performance_gd.SpiGdRepository;
import com.example.ws.microservices.firstmicroservices.service.performance_gd.SpiGdService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpiGdServiceImpl implements SpiGdService {

    private final SpiGdRepository spiGdRepository;

    @Override
    public void saveSpi(SpiGd spiGd) {
        spiGdRepository.save(spiGd);
    }
}
