package com.example.uploadtest;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.ErrorInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class UploadtestApplication {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    public static void main(String[] args) {
        SpringApplication.run(UploadtestApplication.class, args);

        // 账号
        String appId = "CpeGC456b7xQ8mdGjh7MxgyqhZMdN5uCgtCnbAjHnFJ7";
        String sdkKey = "4vbsumnXt5H5kMH4gFxdqNejnKPzEwALeiCjvdpGjUqW";

        // 读取配置
        FaceEngine faceEngine = new FaceEngine("D:\\Program Files\\JetBrains\\ArcSoft_ArcFace_Java_Windows_x64_V3.0\\libs\\WIN64");
        // 激活
        int activeCode = faceEngine.activeOnline(appId, sdkKey);

//        System.out.println(activeCode);
        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }else {
            System.out.println("引擎激活成功");
        }
    }


}
