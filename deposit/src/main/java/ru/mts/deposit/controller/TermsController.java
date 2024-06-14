package ru.mts.deposit.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.deposit.service.TermsService;
import ru.mts.starter.annotation.Logging;
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
    @Logging(value = "Get request statuses", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<RequestStatusDto> getRequestStatuses() {
        return termsService.getRequestStatuses();
    }

    @GetMapping("/deposit-types")
    @Logging(value = "Get deposit types", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<String> getDepositTypes() {
        return termsService.getDepositTypes();
    }

    @GetMapping("/deposit-durations")
    @Logging(value = "Get deposit durations", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<String> getDepositDurations() {
        return termsService.getDepositDurations();
    }

    @GetMapping("/types-percent-period")
    @Logging(value = "Get types percent period", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<String> getTypePercentPeriod() {
        return termsService.getTypePercentPayments();
    }

    @PostMapping("/terms")
    public void getTerms(@RequestBody DepositTermsDto depositTermsDto) {

    }

    @PostMapping("/deposit-rate")
    @Logging(value = "Get deposit rate", enter = true, exit = true, includeParams = true, includeResult = true)
    public BigDecimal getDepositRate(@RequestBody DepositTermsDto depositTermsDto) {
        return termsService.calculateRate(depositTermsDto);
    }
}
