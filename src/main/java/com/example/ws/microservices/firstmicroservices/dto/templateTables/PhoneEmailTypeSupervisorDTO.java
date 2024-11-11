package com.example.ws.microservices.firstmicroservices.dto.templateTables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PhoneEmailTypeSupervisorDTO {

    private Integer id;
    private String name;

}