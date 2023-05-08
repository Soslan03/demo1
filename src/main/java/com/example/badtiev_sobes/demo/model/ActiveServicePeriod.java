package com.example.badtiev_sobes.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class ActiveServicePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    private Integer vehicleId;// - идентификатор ТС
//    private UUID serviceId;// - идентификатор услуги
//
//    private Date startDate;// - дата начала действия услуги
//    private Date endDate// - дата окончания действия услуги
//    private Integer startYearMonth;// - дата начала действия услуги в формате yyyymm
//    private Integer endYearMonth;// - дата окончания действия услуги в формате yyyymm
//    private Integer createdAt;// - дата создания записи
//    private Integer updatedAt;// - дата обновления записи
    private String serverName;// - имя сервера где подключена услуга

}
