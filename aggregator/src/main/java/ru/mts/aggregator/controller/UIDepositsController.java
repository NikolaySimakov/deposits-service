package ru.mts.aggregator.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mts.aggregator.service.DepositService;
import org.springframework.ui.Model;

@Controller
public class UIDepositsController {

    private final DepositService depositService;

    public UIDepositsController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/deposits")
    public String index(Model model) {
        model.addAttribute("depositsList", depositService.getDeposits());
        return "deposits";
    }

}
