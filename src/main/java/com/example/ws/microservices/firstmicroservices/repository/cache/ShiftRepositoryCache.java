package com.example.ws.microservices.firstmicroservices.repository.cache;

import com.example.ws.microservices.firstmicroservices.entity.vision.simpleTables.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShiftRepositoryCache extends JpaRepository<Shift, Short> {

    @Query("SELECT a FROM Shift a LEFT JOIN FETCH a.site")
    List<Shift> findAllWithSite();

}
