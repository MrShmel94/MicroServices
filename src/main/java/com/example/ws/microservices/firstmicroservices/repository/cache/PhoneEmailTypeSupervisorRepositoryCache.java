package com.example.ws.microservices.firstmicroservices.repository.cache;

import com.example.ws.microservices.firstmicroservices.dto.templateTables.PhoneEmailTypeSupervisorDTO;
import com.example.ws.microservices.firstmicroservices.entity.vision.PhoneEmailTypeSupervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneEmailTypeSupervisorRepositoryCache extends JpaRepository<PhoneEmailTypeSupervisor, Integer> {

    @Query("""
            SELECT new com.example.ws.microservices.firstmicroservices.dto.templateTables.PhoneEmailTypeSupervisorDTO(
                p.id, p.name
            )
            FROM PhoneEmailTypeSupervisor p
            """)
    List<PhoneEmailTypeSupervisorDTO> getAllType();
}
