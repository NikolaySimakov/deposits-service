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

    @GetMapping("/deposits/active")
    public List<DepositDto> getActiveDeposits() {
        return depositService.getActiveDeposits();
    }

    @GetMapping("/deposits/rejected")
    public List<DepositDto> getRejectedDeposits() {
        return depositService.getRejectedDeposits();
    }

    @PostMapping("/new")
    public void createDeposit(@RequestBody DepositDto depositDto) {
        depositService.createNewDeposit(depositDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRejectedDeposit(@PathVariable("id") long id) {
        depositService.deleteRejectedDepositById(id);
    }
}
