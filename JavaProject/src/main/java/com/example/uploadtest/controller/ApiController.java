package com.example.uploadtest.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * @author Jun
 * @date 2022/12/8 10:09
 * @description ApiController
 */
@Controller
public class ApiController {
    @RequestMapping("")
    public String index(){
        return "upload";
    }

    @RequestMapping("test")
    public String test(){ return "test"; }

    @RequestMapping("index")
    public String index1(){ return "index"; }

    @RequestMapping("show")
    public String show(){ return "show"; }


}
