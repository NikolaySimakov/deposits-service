package ru.mts.aggregator.dto;

import lombok.Data;
import ru.mts.starter.dto.DepositDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class DepositList {

    private List<DepositDto> deposits;

    public DepositList() {
        deposits = new ArrayList<>();
    }
}
