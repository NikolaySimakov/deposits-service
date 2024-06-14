package ru.mts.deposit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.deposit.service.DepositService;
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
    public List<DepositDto> getActiveDeposits() {
        return depositService.getActiveDeposits();
    }

    @GetMapping("/deposits/rejected")
    public List<DepositDto> getRejectedDeposits() {
        return depositService.getRejectedDeposits();
    }

    @GetMapping("/deposit/{id}")
    public ResponseEntity<?> getDepositById(@PathVariable("id") long id) {
        try {
            DepositDto depositDto = depositService.getDepositById(id);
            return new ResponseEntity<>(depositDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deposit not found", HttpStatus.NOT_FOUND);
        }
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
