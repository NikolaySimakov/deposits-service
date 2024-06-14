package ru.mts.account.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.account.service.CustomerService;
import ru.mts.account.service.SmsService;
import ru.mts.starter.annotation.Logging;
import ru.mts.starter.dto.SmsDto;

@RestController
@RequestMapping("/api")
public class SmsController {

    private final SmsService smsService;
    private final CustomerService customerService;

    public SmsController(SmsService smsService, CustomerService customerService) {
        this.smsService = smsService;
        this.customerService = customerService;
    }

    @PostMapping("/sms")
    @Logging(value = "Get SMS", enter = true, exit = true, includeParams = true, includeResult = true)
    public SmsDto getSmsCode(@RequestParam String phoneNumber) {
        if (customerService.customerExists(phoneNumber)) {
            // Here some service for sms
            String smsCode = smsService.generateSmsCode();
            return smsService.createSms(phoneNumber, smsCode);
        } else {
            return new SmsDto();
        }
    }

    @GetMapping("/verify")
    @Logging(value = "Verify SMS", enter = true, exit = true, includeParams = true, includeResult = true)
    public boolean verifyCode(@RequestParam String code) {
        return smsService.isSmsActiveByCode(code);
    }
}
