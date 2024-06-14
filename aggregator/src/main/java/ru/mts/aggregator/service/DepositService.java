package ru.mts.aggregator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.mts.aggregator.dto.DepositList;
import ru.mts.starter.dto.DepositDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DepositService {

    @Value("${apis.deposit}")
    private String depositAPI;

    private final RestTemplate restTemplate;

    public DepositService() {
        this.restTemplate = new RestTemplate();
    }

    public DepositDto[] getDeposits() {
        ResponseEntity<DepositDto[]> response = restTemplate.getForEntity(depositAPI + "/deposits/active", DepositDto[].class);
        return response.getBody();
    }



}
