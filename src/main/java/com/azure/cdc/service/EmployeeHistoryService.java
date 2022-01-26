package com.azure.cdc.service;

import com.azure.cdc.repository.EmployeeHistoryRepository;
import com.azure.cdc.model.EmployeeHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeHistoryService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeHistoryService.class);

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    public void loadCache(List<EmployeeHistory> employeeHistoryList){

        employeeHistoryRepository.saveAll(employeeHistoryList);

    }
}
