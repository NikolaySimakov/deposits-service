package ru.mts.deposit.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.deposit.service.DepositService;
import ru.mts.starter.dto.DepositDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/deposits")
    public List<DepositDto> getRequestStatuses() {
        return depositService.getDeposits();
    }

    @PostMapping("/add")
    public void createDeposit(@RequestBody DepositDto depositDto) {
        depositService.createNewDeposit();
    }

}
