package ru.mts.aggregator.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mts.aggregator.service.DepositService;
import org.springframework.ui.Model;
import ru.mts.starter.annotation.Logging;
import ru.mts.starter.dto.DepositTermsDto;

import java.util.Optional;


@Controller
public class UIDepositsController {

    private final DepositService depositService;

    public UIDepositsController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/deposits")
    @Logging(value = "Get deposit", enter = true, exit = true, includeParams = true, includeResult = true)
    public String index(Model model) {
        model.addAttribute("depositsList", depositService.getDeposits());
        return "deposits";
    }

    @GetMapping("/terms")
    @Logging(value = "Get terms", enter = true, exit = true, includeParams = true, includeResult = true)
    public String terms(Model model, @RequestParam(name = "rate", required = false) String rate) {
        model.addAttribute("depositTypesStr", depositService.getDepositTypes());
        model.addAttribute("depositDurationsStr", depositService.getDepositDurations());
        model.addAttribute("typesPercentPeriodsStr", depositService.getTypesPercentPeriods());
        model.addAttribute("depositTerms", new DepositTermsDto());
        if (rate != null && !rate.isEmpty()) {
            model.addAttribute("rate", rate);
        }
        return "terms";
    }

    @PostMapping("/terms")
    @Logging(value = "Get deposit rate", enter = true, exit = true, includeParams = true, includeResult = true)
    public String terms(@ModelAttribute("depositTerms") DepositTermsDto depositTermsDto) {
        try {
            String depositRate = depositService.getDepositRate(depositTermsDto);
            return "redirect:/terms?rate=" + depositRate;
        } catch (Exception e) {
            return "redirect:/terms";
        }
    }

}
