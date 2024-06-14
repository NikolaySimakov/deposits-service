package ru.mts.customerservice.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.customerservice.dto.ReplenishResponse;
import ru.mts.customerservice.dto.ReplenishSum;
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
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/new")
    public void addCustomer(@RequestBody CustomerDto customer) {
        customerService.addCustomer(customer);
    }

    @PostMapping("/verify")
    public Boolean verifyUserBySmsCode(@RequestParam String code) {
        return customerService.verifyCustomerBySmsCode(code);
    }

    @PostMapping("/new-deposit")
    @Transactional
    public ResponseEntity<?> createNewDeposit(@RequestParam String phone, @RequestBody DepositTermsDto depositTerms) {
        if (!customerService.validateDepositAmount(phone, depositTerms.getDepositSum())) {
            // Если валидация не прошла, возвращаем ошибку 400 с сообщением
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deposit amount exceeds the amount in the account");
        }

        customerService.createDeposit(phone, depositTerms);
        customerService.subtractDepositAmount(phone, depositTerms.getDepositSum());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/replenish-deposit/{id}")
    @Transactional
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        customerService.subtractDepositAmount(phone, replenishSum.getSum());

        return ResponseEntity.ok().body(replenishResponse);
    }

}
