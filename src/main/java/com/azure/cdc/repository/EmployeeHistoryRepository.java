package com.azure.cdc.repository;

import com.azure.cdc.model.EmployeeHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmployeeHistoryRepository extends MongoRepository<EmployeeHistory,String> {

    @Query("{name:'?0'}")
    EmployeeHistory findItemBuyName(String name);
}
