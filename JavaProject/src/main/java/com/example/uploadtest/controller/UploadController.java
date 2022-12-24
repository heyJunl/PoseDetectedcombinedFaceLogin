package com.example.uploadtest.controller;

import com.example.uploadtest.util.UploadUtil;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Jun
 * @date 2022/11/28 11:21
 * @description UploadController
 */
@RestController
public class UploadController {
    // 本地存储图片方式
    @PostMapping("/upload")
    public String upload(MultipartFile file)  {
        // file检验,最好判断一下文件有没有后缀
        if (file.isEmpty()) {
            return "图片上传失败";
        }
        // 图片重命名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String end = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid+end;
        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\src\\main\\resources\\static\\images\\";     //linux用/替换\\
        // 上传文件路径
        String path = pre+fileName;
        try {
            file.transferTo(new File(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    // OSS对象存储
    @PostMapping("/upImg")
    public String upImg(MultipartFile file) throws IOException {
        return UploadUtil.uploadImage(file);
    }
}
