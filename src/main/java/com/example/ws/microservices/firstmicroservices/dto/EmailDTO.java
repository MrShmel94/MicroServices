package com.example.ws.microservices.firstmicroservices.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDTO {

    private Integer id;
    private String email;
    private String type;

}
