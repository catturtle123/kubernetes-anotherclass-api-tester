package com.pro.app.controller;

import com.pro.app.component.FileUtils;
import com.pro.app.service.Sprint5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
public class Sprint5Controller {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private Sprint5Service sprint5Service;


    @GetMapping("/connection/{serviceName}/{path}")
    @ResponseBody
    public ResponseEntity<Object> connection(@PathVariable String serviceName, @PathVariable String path)  {

        String body = sprint5Service.connectionPool(serviceName, path);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/null-pointer-exception")
    public String npeTest() {
        return sprint5Service.makeError();
    }

    @GetMapping("/service-unavailable")
    public ResponseEntity<String> serviceUnavailable() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)  // 503 상태 코드
                .body("Service is temporarily unavailable. Please try again later.");

    }

    @GetMapping("/sleep")
    public void sleep() {
        sprint5Service.sleep();
    }
}