package com.example.ws.microservices.firstmicroservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {

    private Integer id;
    private String name;
    private Integer weight;
    private String description;

}
