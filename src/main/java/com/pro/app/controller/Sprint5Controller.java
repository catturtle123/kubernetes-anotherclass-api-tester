package com.pro.app.controller;

import com.pro.app.component.FileUtils;
import com.pro.app.service.DefaultService;
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

    @Autowired
    private DefaultService defaultService;

    @GetMapping("/connection/{serviceName}/{path}")
    @ResponseBody
    public ResponseEntity<Object> connection(@PathVariable String serviceName, @PathVariable String path, @RequestParam(defaultValue = "none") String podName)  {

        String body = sprint5Service.connectionPool(serviceName, path, podName);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/null-pointer-exception")
    public String npeTest() {
        return sprint5Service.makeError();
    }

    @GetMapping("/service-unavailable")
    public ResponseEntity<String> serviceUnavailable() {

        String hostname =  defaultService.hostname();

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)  // 503 상태 코드
                .body(hostname + " Service is temporarily unavailable. Please try again later.");

    }

    @GetMapping("/sleep")
    public ResponseEntity<Object> sleepPodName(@RequestParam(defaultValue = "none") String podName) {

        String hostname =  defaultService.hostname();
        log.info("hostname: "+hostname);
        if (hostname.equals(podName)) {
            log.info("sleep 5s:");
            sprint5Service.sleep();
        }
        return ResponseEntity.ok(hostname);
    }
}