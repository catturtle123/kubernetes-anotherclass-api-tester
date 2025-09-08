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
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.core5.util.Timeout;



@Component
public class ConnectionPool {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Bean(name = "api1RestTemplate")
    public RestTemplate api1RestTemplate() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(2);               // 전체 커넥션 수

        // RequestConfig에 timeout 설정
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setResponseTimeout(Timeout.ofSeconds(3))           // 요청 후 응답 최대 대기 시간
//                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
//                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(3000); // 연결 대기 시간 (3초)

        return new RestTemplate(factory);
    }
}
