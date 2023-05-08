package com.example.badtiev_sobes.demo.controllers;

import com.example.badtiev_sobes.demo.exceptions.NotFoundSnDiUiException;
import com.example.badtiev_sobes.demo.model.dto.MigrateServerDto;
import com.example.badtiev_sobes.demo.service.ManageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.StringUtils.hasText;


@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ServiceController {
    private final ManageService manageService;





    @Operation(summary = "переименование имени сервера для миграции")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request params")
    @ApiResponse(responseCode = "404", description = "Bad request params")
    @ApiResponse(responseCode = "500", description = "Internal error")
    @PostMapping("/migrateserver")
    public ResponseEntity<String> updateServerName(@RequestBody MigrateServerDto request) {

       // LogUtil.info(log, "updateServerName", "Server name update requested {}", request);

        if (request == null || ((request.getUserId() == null) && (request.getDealerId() == null))
                || ((request.getUserId() != null) && (request.getDealerId() != null))
                || !hasText(request.getOldServername()) || !hasText(request.getNewServername())) {
         //   LogUtil.warn(log, "updateServerName", "bad request, userId or dealerId is missing");
            return ResponseEntity.badRequest().build();
        }


       // request.setOldServername(consulClientService.getRealServerName(request.getOldServername()));
       // request.setNewServername(consulClientService.getRealServerName(request.getNewServername()));
        try {
            boolean result = manageService.updateServername(request);
            if (!result) {
                /// LogUtil.warn(log, "updateServerName",
                //      "Servername update error, the number of requested identifiers does not match stored in db!");
                return ResponseEntity.internalServerError().build();
            }


        } catch (Exception e ) {
            //   LogUtil.error(log, "updateService", "Failed to update service with params {}" + request, e);
            if (e instanceof NotFoundSnDiUiException) {
                System.out.println(("Cannot find server name \'" + request.getOldServername()
                        + "\' or userID \'" + request.getUserId() + "\' or dealerID \'" + request.getDealerId()+"\'"));
                return  new  ResponseEntity<>("Bad request params",HttpStatus.NOT_FOUND);
            }


            return ResponseEntity.internalServerError().build();
        }


        // LogUtil.info(log, "updateServerName", "Servername update success, send response");
        return ResponseEntity.ok("Servername update success");
    }

}
