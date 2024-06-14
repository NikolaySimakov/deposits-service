package ru.mts.customerservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.customerservice.dto.ReplenishResponse;
import ru.mts.customerservice.dto.ReplenishSum;
import ru.mts.starter.annotation.Logging;
import ru.mts.starter.dto.PhoneNumberDto;
import ru.mts.customerservice.service.CustomerService;
import ru.mts.starter.dto.CustomerDto;
import ru.mts.starter.dto.DepositTermsDto;
import ru.mts.starter.exceptions.InvalidDepositException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    @Logging(value = "Get customers", enter = true, exit = true, includeParams = true, includeResult = true)
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/new")
    @Logging(value = "Create new customer", enter = true, exit = true, includeParams = true, includeResult = true)
    public void addCustomer(@RequestBody CustomerDto customer) {
        customerService.addCustomer(customer);
    }

    @PostMapping("/verify")
    @Logging(value = "Verify sms code", enter = true, exit = true, includeParams = true, includeResult = true)
    public Boolean verifyUserBySmsCode(@RequestParam String code) {
        return customerService.verifyCustomerBySmsCode(code);
    }

    @PostMapping("/deposit/new")
    @Transactional
    @Logging(value = "Create new deposit", enter = true, exit = true, includeParams = true, includeResult = true)
    public ResponseEntity<?> createNewDeposit(@RequestParam String phone, @RequestBody DepositTermsDto depositTerms) {
        if (!customerService.validateDepositAmount(phone, depositTerms.getDepositSum())) {
            // Если валидация не прошла, возвращаем ошибку 400 с сообщением
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Deposit amount exceeds the amount in the account");
        }

        try {
            customerService.createDeposit(phone, depositTerms);
            customerService.subtractDepositAmountFromBankAccount(phone, depositTerms.getDepositSum());
        } catch (IllegalAccessException | InvalidDepositException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/deposit/replenish/{id}")
    @Transactional
    @Logging(value = "Replenish deposit", enter = true, exit = true, includeParams = true, includeResult = true)
    public ResponseEntity<?> replenishDeposit(@PathVariable("id") long id, @RequestParam String phone,
                                              @RequestBody ReplenishSum replenishSum) {
        if (!customerService.validateDepositAmount(phone, replenishSum.getSum())) {
            // Если валидация не прошла, возвращаем ошибку 400 с сообщением
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are insufficient funds in the account");
        }

        ReplenishResponse replenishResponse = new ReplenishResponse();

        try {
            BigDecimal updatedSum = customerService.replenishDeposit(id, replenishSum.getSum());
            replenishResponse.setUpdatedSum(updatedSum);
            replenishResponse.setDate(LocalDateTime.now());

            customerService.subtractDepositAmountFromBankAccount(phone, replenishSum.getSum());
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok().body(replenishResponse);
    }

    @PostMapping("/deposit/close/{id}")
    @Transactional
    @Logging(value = "Close deposit", enter = true, exit = true, includeParams = true, includeResult = true)
    public ResponseEntity<?> closeDeposit(@PathVariable("id") long id, @RequestParam String phone) {
        try {
            BigDecimal depositAmount = customerService.subtractDepositAmount(id);
            customerService.transferDepositAmountToBankAccount(phone, depositAmount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        try {
            customerService.deleteDepositById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
