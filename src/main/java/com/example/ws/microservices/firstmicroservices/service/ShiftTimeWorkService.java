package com.example.ws.microservices.firstmicroservices.service;

import com.example.ws.microservices.firstmicroservices.dto.ShiftTimeWorkDTO;

import java.util.List;

public interface ShiftTimeWorkService {

    List<ShiftTimeWorkDTO> getShiftTimeWorkByNameSite(String siteName);
}
