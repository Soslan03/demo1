package com.example.badtiev_sobes.demo.service;

import com.example.badtiev_sobes.demo.exceptions.NotFoundSnDiUiException;
import com.example.badtiev_sobes.demo.model.dto.MigrateServerDto;
import com.example.badtiev_sobes.demo.repository.ManageServiceRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Slf4j
@Service
@AllArgsConstructor
public class ManageServiceImpl implements ManageService {



    private final ManageServiceRepository manageServiceRepository;



    @Override
    @Transactional
  //  @Timed("service")
    public boolean updateServername(MigrateServerDto request) {
//        LogUtil.info(log, "updateServername", "Updating server name {}", request);

        int[] updated = manageServiceRepository.updateServername(request);

        //  LogUtil.info(log, "updateService", "Updated rows = {}", updated);
        if (updated[1] <= 0)  {
            //  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new NotFoundSnDiUiException( "Cannot find server name " + request.getOldServername()
                    +" or userID"+request.getUserId()+" or dealerID"+request.getDealerId());
             }
        return true;


    }
}
