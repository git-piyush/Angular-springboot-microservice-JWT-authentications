package com.piyush.user.fee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "FEE-SERVICE",
        url = "${application.config.fee-url}"
)
public interface FeeClient {

    @GetMapping("/fee-details/{user-id}")
    String findStudentFeeDetails(@PathVariable("user-id") String userId, @RequestHeader(name="Authorization") String token);

}
