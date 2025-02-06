package com.javatechie.user;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "USER-SERVICE",
        url = "${application.config.user-url}"
)
public interface UserClient {
    @PostMapping("/user-info")
    String saveUserInfoDetails(@RequestBody UserInfoRequest userInfoRequest);

    @GetMapping("/hello")
    String hello();
}
