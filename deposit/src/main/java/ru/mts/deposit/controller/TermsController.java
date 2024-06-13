package ru.mts.deposit.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.deposit.service.TermsService;
import ru.mts.starter.dto.DepositTermsDto;
import ru.mts.starter.dto.DepositTypeDto;
import ru.mts.starter.dto.RequestStatusDto;
import ru.mts.starter.dto.TypePercentPaymentDto;

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
    public List<DepositTypeDto> getDepositTypes() {
        return termsService.getDepositTypes();
    }

    @GetMapping("/types-percent-period")
    public List<TypePercentPaymentDto> getTypePercentPeriod() {
        return termsService.getTypePercentPayments();
    }

    @PostMapping("/terms")
    public void getTerms(@RequestBody DepositTermsDto depositTermsDto) {
        System.out.println(depositTermsDto);
        termsService.calculateRate();
    }
}
