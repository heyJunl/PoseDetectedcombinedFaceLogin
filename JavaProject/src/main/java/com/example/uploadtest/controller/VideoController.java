package com.example.uploadtest.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.uploadtest.config.NonStaticResourceHttpRequestHandler;
import com.example.uploadtest.entity.User;
import com.example.uploadtest.service.UserService;
import com.example.uploadtest.util.HttpFileUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Jun
 * @date 2022/12/7 20:50
 * @description VideoController
 */
@RestController
@AllArgsConstructor
public class VideoController {

    @Autowired
    private UserService userService;

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;


    @PostMapping("/uploadVideo")
    public JSONObject uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        File files = new File("./temp.mp4");
        FileUtils.copyInputStreamToFile(file.getInputStream(), files);
        //File file = new File("src/main/resources/static/mn.mp4");

        //File文件转MultipartFile
        FileInputStream input = new FileInputStream(files);
//        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray((InputStream) file));
        MultipartFile multipartFile = new MockMultipartFile("file", file.getOriginalFilename(), "text/plain", IOUtils.toByteArray(input));
        Object data = HttpFileUtil.doPostFormData("http://172.16.61.129:5000/upload", "file", multipartFile);

        JSONObject todata = JSONObject.parseObject(data.toString());
        Object userName = todata.get("user_name");
        Object userNum = todata.get("user_num");
        System.out.println(data);
        System.out.println(todata);
        User user = new User();
        user.setUserName(String.valueOf(userName));
        user.setUserNum(String.valueOf(userNum));
        userService.save(user);
//        return HttpFileUtil.doPostFormData("http://172.16.61.129:5000/upload", "file", multipartFile);
        return todata;
    }

    @GetMapping("/queryData")
    public List<User> queryData(){
        List<User> list = userService.list();
        return list;
    }

    @GetMapping(value = "/playVideo" ,produces ="application/json;charset=utf-8")
    public void playVideo(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        OutputStream os = null;
        try {
            response.setContentType("video/mp4");
            File file = new File("src/main/resources/static/show.mp4");
            response.addHeader("Content-Length", "" + file.length());
            is = new FileInputStream(file);
            os = response.getOutputStream();
            IOUtils.copy(is, os);
        } catch (Exception e) {
//            log.error("播放MP4失败", e);
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
