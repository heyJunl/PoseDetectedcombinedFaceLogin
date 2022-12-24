package com.example.uploadtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author Jun
 * @date 2022/12/7 21:24
 * @description uploadConfig
 */

public class UploadConfig {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //20MB
        multipartResolver.setMaxUploadSize(20971520);

        // 3MB
        multipartResolver.setMaxInMemorySize(3048576);
        return multipartResolver;
    }
}
