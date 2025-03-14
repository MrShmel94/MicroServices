package com.example.ws.microservices.firstmicroservices.serviceImpl;

import com.example.ws.microservices.firstmicroservices.dto.EmailDTO;
import com.example.ws.microservices.firstmicroservices.repository.EmailRepository;
import com.example.ws.microservices.firstmicroservices.service.EmailSupervisorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EmailSupervisorServiceImpl implements EmailSupervisorService {

    private final EmailRepository emailRepository;

    @Override
    public List<EmailDTO> getAllEmailsPerUserId(Integer userId) {
        return emailRepository.findAllByEmailSupervisorId(userId);
    }
}
