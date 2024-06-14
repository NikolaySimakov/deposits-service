package ru.mts.customerservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReplenishResponse {
    private BigDecimal updatedSum;
    private LocalDateTime date;
}
