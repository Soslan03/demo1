package com.example.badtiev_sobes.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Entity
@Data
public class VehicleBelongs {
    @Id

    @Column(name = "id", nullable = false)
    private Long id;
    private Integer vehicle_id;
    private String serverName;

}
