package com.example.ws.microservices.firstmicroservices.entity.vision.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 64)
    String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "description")
    private String description;

    @Column(name = "user_id", nullable = false)
    private String userId;
}
