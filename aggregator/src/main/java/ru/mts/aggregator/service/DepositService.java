package ru.mts.aggregator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.mts.aggregator.dto.DepositList;
import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.dto.DepositTermsDto;

import java.math.BigDecimal;
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

    public String[] getDepositTypes() {
        ResponseEntity<String[]> response = restTemplate.getForEntity(depositAPI + "/deposit-types", String[].class);
        return response.getBody();
    }

    public String[] getDepositDurations() {
        ResponseEntity<String[]> response = restTemplate.getForEntity(depositAPI + "/deposit-durations", String[].class);
        return response.getBody();
    }

    public String[] getTypesPercentPeriods() {
        ResponseEntity<String[]> response = restTemplate.getForEntity(depositAPI + "/types-percent-period", String[].class);
        return response.getBody();
    }

    public String getDepositRate(DepositTermsDto depositTermsDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(depositTermsDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(depositAPI + "/deposit-rate", entity,
                String.class, headers);
        return response.getBody();
    }
}
