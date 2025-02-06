package com.javatechie.user;

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

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class UserRestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.config.user-url}")
    private String userUrl;

    public String hello() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<RegisterNotification> requestEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                userUrl + "/hello",
                GET,
                requestEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        return  responseEntity.getBody();
    }

    public String saveUserInfoDetails(UserInfoRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<UserInfoRequest> requestEntity = new HttpEntity<>(request, headers);
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                userUrl + "/user-info",
                POST,
                requestEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while saving the user details: " + responseEntity.getStatusCode());
        }
        return  responseEntity.getBody();
    }

}
