package com.example.ws.microservices.firstmicroservices.request.websocket;

import java.util.List;

public record SelectionPayloadPlaning(
        Long trainingId,
        String type,
        List<SelectedEmployeeRequest> selectedEmployees
) {
}
