package com.khaja.tacocloud.tacoorder.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

import com.khaja.tacocloud.tacoorder.model.TacoOrder;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);

    @GetMapping("/current")
    public String orderForm(@SessionAttribute("tacoOrder")TacoOrder tacoOrder, Model model){ 
        model.addAttribute("tacoOrder", tacoOrder);
        log.info("Inside current to show order form {}", tacoOrder);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
            SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
