package com.pro.app.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;

@Component
public class ConnectionPool {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Bean(name = "api1RestTemplate")
    public RestTemplate api1RestTemplate() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(2);               // 전체 커넥션 수
        cm.setDefaultMaxPerRoute(1);     // 특정 호스트당 최대 커넥션 수

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(3000);  // 연결 대기 시간

        return new RestTemplate(factory);
    }
}
