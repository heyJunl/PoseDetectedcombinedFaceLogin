package com.example.uploadtest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author Jun
 * @date 2022/12/11 15:35
 * @description RestTemplateConfig
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("simpleClientHttpRequestFactory") ClientHttpRequestFactory factory){
        RestTemplate restTemplate= new RestTemplate(factory);
        // 支持UTF-8
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;

    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 单位为ms
        factory.setReadTimeout(120000);
        factory.setConnectTimeout(120000);
        return factory;
    }
}