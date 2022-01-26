package com.azure.cdc;

import com.azure.cdc.model.EmployeeHistory;
import com.azure.cdc.service.EmployeeHistoryService;
import com.azure.spring.integration.core.EventHubHeaders;
import com.azure.spring.integration.core.api.reactor.Checkpointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.messaging.Message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.azure.spring.integration.core.AzureHeaders.CHECKPOINTER;

@SpringBootApplication
@EnableMongoRepositories
public class ConsumerApplication {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);


    @Autowired
    EmployeeHistoryService employeeHistoryService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public Consumer<Message<String>> consume() {
        return message -> {
            Checkpointer checkpointer = (Checkpointer) message.getHeaders().get(CHECKPOINTER);
            LOGGER.info("New message received: '{}', partition key: {}, sequence number: {}, offset: {}, enqueued time: {}",
                    message.getPayload(),
                    message.getHeaders().get(EventHubHeaders.PARTITION_KEY),
                    message.getHeaders().get(EventHubHeaders.SEQUENCE_NUMBER),
                    message.getHeaders().get(EventHubHeaders.OFFSET),
                    message.getHeaders().get(EventHubHeaders.ENQUEUED_TIME)
            );
            checkpointer.success()
                    .doOnSuccess(success -> LOGGER.info("Message '{}' successfully checkpointed", message.getPayload()))
                    .doOnError(error -> LOGGER.error("Exception found", error))
                    .subscribe();

            String output = message.getPayload();

            List<EmployeeHistory> employeeHistoryList = parseEmployeeObject(output);

            employeeHistoryService.loadCache(employeeHistoryList);

        };
    }

    private List<EmployeeHistory> parseEmployeeObject(String jsonPayload){
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeHistory> employeeHistoryList = null;
        try {
            objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
            employeeHistoryList = Arrays.asList(objectMapper.readValue(jsonPayload, EmployeeHistory[].class));
            employeeHistoryList.stream().forEach(employee -> {
                System.out.println("Consumer:  employeeList" + employee.toString());
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return employeeHistoryList;
    }
}
