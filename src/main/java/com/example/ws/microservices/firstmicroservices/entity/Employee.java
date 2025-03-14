package com.example.ws.microservices.firstmicroservices.entity;

import com.example.ws.microservices.firstmicroservices.entity.role.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "expertis", nullable = false, length = 128)
    private String expertis;

    @Column(name = "zalos_id", nullable = false)
    private Short zalosId;

    @Column(name = "br_code", nullable = false, length = 128)
    private String brCode;

    @Column(name = "first_name", nullable = false, length = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName;

    @ColumnDefault("true")
    @Column(name = "is_work", nullable = false)
    private Boolean isWork = false;

    @ColumnDefault("false")
    @Column(name = "is_supervisor", nullable = false)
    private Boolean isSupervisor = false;

    @Column(name = "is_can_has_account", nullable = false)
    private Boolean isCanHasAccount;

    @Column(name = "valid_to", nullable = false)
    private LocalDate validToAccount;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFromAccount;

    @Column(name = "sex", nullable = false, length = 4)
    private String sex;

    @Column(name = "site_id", nullable = false)
    private Short siteId;

    @Column(name = "shift_id", nullable = false)
    private Short shiftId;

    @Column(name = "department_id", nullable = false)
    private Short departmentId;

    @Column(name = "country_id", nullable = false)
    private Short countryId;

    @Column(name = "team_id", nullable = false)
    private Short teamId;

    @Column(name = "position_id", nullable = false)
    private Short positionId;

    @Column(name = "agency_id", nullable = false)
    private Short agencyId;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UserRole> userRoles = new HashSet<>();
}