package ru.mts.account.service;

import org.springframework.stereotype.Service;
import ru.mts.starter.dao.SmsRepository;
import ru.mts.starter.dto.SmsDto;
import ru.mts.starter.entity.Sms;
import ru.mts.starter.mapper.SmsMapper;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SmsService {

    private final SmsRepository smsRepository;

    public SmsService(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    public String generateSmsCode() {
        return generateRandomNumber();
    }

    public SmsDto createSms(String phoneNumber, String code) {
        Sms sms = new Sms();
        sms.setPhoneNumber(phoneNumber);
        sms.setCode(code);

        // end time
        int duration = 1;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteLater = now.plusMinutes(duration);
        sms.setEndTime(oneMinuteLater);
        Sms sms1 = smsRepository.save(sms);

        return SmsMapper.toDto(sms1);
    }

    public boolean isSmsActiveByPhoneNumberAndCode(String phoneNumber, String code) {
        return smsRepository.existsByPhoneNumberAndCodeAndIsActive(phoneNumber, code);
    }

    public boolean isSmsActiveByCode(String code) {
        return smsRepository.existsByCodeAndIsActive(code);
    }

    private String generateRandomNumber() {
        int n = 6;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int randomNumber = random.nextInt(10);
            sb.append(randomNumber);
        }

        return sb.toString();
    }
}
