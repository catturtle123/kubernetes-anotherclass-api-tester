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

import java.net.URI;

@Service
public class Sprint5Service {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final RestTemplate api1RestTemplate;

    public Sprint5Service(@Qualifier("api1RestTemplate") RestTemplate api1RestTemplate) {
        this.api1RestTemplate = api1RestTemplate;
    }

    public String connectionPool(String serviceName, String path, String podName) {

        // Cust Service로 Reqeust 전송
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String url = "http://" + serviceName + "/" + path;

        if (!podName.equals("none")) {
            url = url + "?podName=" + podName;
        }

        ResponseEntity<String> response = api1RestTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class);

        return response.getBody();
    }

    public String makeError() {
        String str = null;
        // 무조건 NPE 발생
        return str.toLowerCase();
    }

    public void sleep() {
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}