package com.piyush.fee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fee")
public class FeeController {

    @GetMapping("/fee-details/{userId}")
    public String feeDetails(@PathVariable Long userId){
        return "Fee Details";
    }

}
