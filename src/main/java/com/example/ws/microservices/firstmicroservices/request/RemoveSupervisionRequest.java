package com.example.ws.microservices.firstmicroservices.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RemoveSupervisionRequest(
        @NotBlank String supervisorExpertis,
        @NotNull List<String> employeeExpertis
) {
}
