package com.example.ws.microservices.firstmicroservices.entity.vision;

import com.example.ws.microservices.firstmicroservices.entity.vision.simpleTables.Site;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "shift_time_work", schema = "vision")
@Getter
@Setter
public class ShiftTimeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "shift_code", nullable = false)
    private String shiftCode;

    @Column(name = "start_shift", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_shift", nullable = false)
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Site siteId;
}
