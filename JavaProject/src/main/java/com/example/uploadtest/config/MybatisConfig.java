package com.example.uploadtest.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jun
 * @date 2022/10/17 15:20
 * @description MybatisConfig
 */
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
