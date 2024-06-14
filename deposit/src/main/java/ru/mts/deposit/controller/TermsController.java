package ru.mts.deposit.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.deposit.service.TermsService;
import ru.mts.starter.dto.DepositTermsDto;
import ru.mts.starter.dto.DepositTypeDto;
import ru.mts.starter.dto.RequestStatusDto;
import ru.mts.starter.dto.TypePercentPaymentDto;
import ru.mts.starter.enums.DepositDurationEnum;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TermsController {

    private final TermsService termsService;

    public TermsController(TermsService termsService) {
        this.termsService = termsService;
    }

    @GetMapping("/statuses")
    public List<RequestStatusDto> getRequestStatuses() {
        return termsService.getRequestStatuses();
    }

    @GetMapping("/deposit-types")
    public List<String> getDepositTypes() {
        return termsService.getDepositTypes();
    }

    @GetMapping("/deposit-durations")
    public List<String> getDepositDurations() {
        return termsService.getDepositDurations();
    }

    @GetMapping("/types-percent-period")
    public List<String> getTypePercentPeriod() {
        return termsService.getTypePercentPayments();
    }

    @PostMapping("/terms")
    public void getTerms(@RequestBody DepositTermsDto depositTermsDto) {

    }

    @PostMapping("/deposit-rate")
    public BigDecimal getDepositRate(@RequestBody DepositTermsDto depositTermsDto) {
        return termsService.calculateRate(depositTermsDto);
    }
}
