package com.example.uploadtest.config;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;


import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;

/**
 * @author Jun
 * @date 2022/12/12 11:47
 * @description NonStaticResourceHttpRequestHandler
 */
@Component
public class NonStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {

    public final static String ATTR_FILE = "NON-STATIC-FILE";

    @Override
    protected Resource getResource(HttpServletRequest request) {
        final Path filePath = (Path) request.getAttribute(ATTR_FILE);
        return new FileSystemResource(filePath);
    }

}