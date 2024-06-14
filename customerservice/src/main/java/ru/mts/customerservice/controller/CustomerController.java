package ru.mts.customerservice.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.starter.dto.PhoneNumberDto;
import ru.mts.customerservice.service.CustomerService;
import ru.mts.starter.dto.CustomerDto;
import ru.mts.starter.dto.DepositTermsDto;
import ru.mts.starter.exceptions.InvalidDepositException;

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
            return ResponseEntity.badRequest().body("Deposit amount exceeds the amount in the account");
        }

        customerService.createDeposit(phone, depositTerms);
        customerService.subtractDepositAmount(phone, depositTerms.getDepositSum());

        return ResponseEntity.ok().build();
    }

}
