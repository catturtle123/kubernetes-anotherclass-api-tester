package com.pro.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Sprint5Service {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final RestTemplate api1RestTemplate;

    public Sprint5Service(@Qualifier("api1RestTemplate") RestTemplate api1RestTemplate) {
        this.api1RestTemplate = api1RestTemplate;
    }

    public String connectionPoll(String serviceName) {

        // Cust Service로 Reqeust 전송
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = api1RestTemplate.exchange(
                "http://"+ serviceName+"/hostname",
                HttpMethod.GET,
                requestEntity,
                String.class);

        return response.getBody();
    }




}