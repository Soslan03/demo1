package com.example.badtiev_sobes.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class VehicleForUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(nullable = true)
    private int user_id; //- идентификатор пользователя
    @Column(nullable = true)
    private int dealer_id;// - идентификатор дилера
    private Long vehicle_belongs_id;//
}
