package com.piyush.user.setting;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "SETTING-SERVICE",
        url = "${application.config.setting-url}"
)
public interface SettingClientt {

    @GetMapping("/refCode/{refCode}")
    String findLongNameByRefcode(@PathVariable("refCode") String refCode, @RequestHeader(name="Authorization") String token);

}
