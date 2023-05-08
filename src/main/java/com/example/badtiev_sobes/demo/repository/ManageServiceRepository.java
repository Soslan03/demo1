package com.example.badtiev_sobes.demo.repository;



import com.example.badtiev_sobes.demo.model.dto.MigrateServerDto;

import java.util.UUID;

public interface ManageServiceRepository {


    int[] updateServername(MigrateServerDto request);
}
