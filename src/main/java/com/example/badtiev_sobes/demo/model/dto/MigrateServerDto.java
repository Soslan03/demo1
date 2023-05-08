package com.example.badtiev_sobes.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MigrateServerDto {
    @JsonProperty("dealer_id")
    private Integer dealerId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("old_servername")
    private String oldServername;
    @JsonProperty("new_servername")
    private String newServername;
}
