package com.javatechie.config;

import com.javatechie.dto.RegisterNotification;
import com.javatechie.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class NotificationRestClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.config.notification-url}")
    private String notificationUrl;

    public String sendEmail(RegisterNotification registerNotification) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<RegisterNotification> requestEntity = new HttpEntity<>(registerNotification, headers);
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                notificationUrl + "/onregister",
                POST,
                requestEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        return  responseEntity.getBody();
    }
}
