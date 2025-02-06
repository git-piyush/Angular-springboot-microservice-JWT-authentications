package com.piyush.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeeServiceClient {
    @Autowired
    private RestTemplate template;

    public String findStudentFeeDetails(String orderId) {
        return template.getForObject("http://FEE-SERVICE/api/v1/fee/fee-details/" + orderId, String.class);
    }
}
