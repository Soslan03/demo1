package com.example.badtiev_sobes.demo.repository;

import com.example.badtiev_sobes.demo.config.ApplicationConfig;
import com.example.badtiev_sobes.demo.exceptions.NotFoundSnDiUiException;
import com.example.badtiev_sobes.demo.model.dto.MigrateServerDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Repository
public class ManageServiceRepositoryImpl implements ManageServiceRepository {

    private static final String FIND_VEHICLE_BELONGS_ID =
            "SELECT vehicle_belongs_id FROM vehicle_for_user a" +
                    " WHERE a.user_id = ?" +
                    " OR a.dealer_id = ?;";
    private static final String UPDATE_SERVERNAME_IN_VEHICLE_BELONGS =
            "UPDATE vehicle_belongs a " +
                    "SET server_name = ?  " +
                    "WHERE a.id IN (?) " +
                    "AND a.server_name = ?;";
    private static final String FIND_VEHICLE_ID_IN_VEHICLE_BELONGS =
            "SELECT vehicle_id FROM vehicle_belongs a" +
                    " WHERE a.id IN (SELECT vehicle_belongs_id FROM vehicle_for_user a " +
                    "                     WHERE a.user_id = ?" +
                    "                     OR a.dealer_id = ?)" +
                    " AND a.server_name = ?;";
    private static final String UPDATE_SERVERNAME_IN_ACTIVE_SERVICE_PERIOD =
            "UPDATE active_service_period a " +
                    "SET server_name = ?  " +
                    "WHERE a.vehicle_id IN (?) " +
                    "AND a.server_name = ?;";


    private final JdbcTemplate jdbcTemplate;
    private final int batchSaveSize;

    public ManageServiceRepositoryImpl(JdbcTemplate jdbcTemplate, ApplicationConfig applicationConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.batchSaveSize = applicationConfig.getBatchSaveSize();
    }


    @Override
    // @Timed("service")
    public int[] updateServername(MigrateServerDto request) throws NotFoundSnDiUiException {
        int[] result = {0, 0};


        int[][] batchResult;
        var listOfVehicleBelongsId = jdbcTemplate.queryForList(FIND_VEHICLE_BELONGS_ID,
                Long.class, request.getUserId(), request.getDealerId());
        System.out.println("listOfVehicleBelongsId: " + listOfVehicleBelongsId);
        if (listOfVehicleBelongsId.isEmpty()) {
            return result;
        }
        var listOfVehicleId = jdbcTemplate.queryForList(FIND_VEHICLE_ID_IN_VEHICLE_BELONGS,
                Integer.class, request.getUserId(), request.getDealerId(), request.getOldServername());
        System.out.println("listOfVehicleId: " + listOfVehicleId);
        if (listOfVehicleId.isEmpty()) {
            return result;
        }
        batchResult = jdbcTemplate.batchUpdate(UPDATE_SERVERNAME_IN_VEHICLE_BELONGS
                , listOfVehicleBelongsId
                , batchSaveSize,
                (ps, vehicleBelongsId) -> {
                    ps.setString(1, request.getNewServername());
                    ps.setObject(2, vehicleBelongsId);
                    ps.setString(3, request.getOldServername());
                });
//        result += batchResult.length;
        result[0] = Arrays.stream(batchResult[0]).sum();
        System.out.println("batchResult[0]: " + Arrays.toString(batchResult[0]) + " batchResult.length: " + batchResult.length);
        System.out.println("listOfVehicleId: " + listOfVehicleId);
        System.out.println("result" + Arrays.toString(result));
        int[][] batchResult2 = jdbcTemplate.batchUpdate(UPDATE_SERVERNAME_IN_ACTIVE_SERVICE_PERIOD
                , listOfVehicleId
                , batchSaveSize,
                (ps, vehicleId) -> {
                    ps.setString(1, request.getNewServername());
                    ps.setInt(2, vehicleId);
                    ps.setString(3, request.getOldServername());
                });

        System.out.println("batchResult2[0]: " + Arrays.toString(batchResult2[0]) + " batchResult2.length: " + batchResult2.length);
//        result +=  Arrays.stream(batchResult2).flatMapToInt(Arrays::stream).sum();
        result[1] = Arrays.stream(batchResult2[0]).sum();
        if (result[1] == 0) {
            result[0] = 0;
        }
        System.out.println("result =" + Arrays.toString(result));


        return result;
    }

}
