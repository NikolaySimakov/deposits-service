package ru.mts.deposit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.deposit.service.DepositService;
import ru.mts.starter.annotation.Logging;
import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.entity.Deposit;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/deposits/active")
    @Logging(value = "Get active deposits", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<DepositDto> getActiveDeposits() {
        return depositService.getActiveDeposits();
    }

    @GetMapping("/deposits/rejected")
    @Logging(value = "Get rejected deposits", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<DepositDto> getRejectedDeposits() {
        return depositService.getRejectedDeposits();
    }

    @GetMapping("/deposit/{id}")
    @Logging(value = "Get deposit by id", enter = true, exit = true, includeParams = true, includeResult = true)
    public ResponseEntity<?> getDepositById(@PathVariable("id") long id) {
        try {
            DepositDto depositDto = depositService.getDepositById(id);
            return new ResponseEntity<>(depositDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deposit not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    @Logging(value = "Create deposit", enter = true, exit = true, includeParams = true, includeResult = true)
    public void createDeposit(@RequestBody DepositDto depositDto) {
        depositService.createNewDeposit(depositDto);
    }

    @DeleteMapping("/delete/{id}")
    @Logging(value = "Delete deposit by id", enter = true, exit = true, includeParams = true, includeResult = true)
    public void deleteDeposit(@PathVariable("id") long id) {
        depositService.deleteDepositById(id);
    }
}
