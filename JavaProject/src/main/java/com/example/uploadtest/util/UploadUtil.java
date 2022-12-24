package com.example.uploadtest.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Jun
 * @date 2022/11/28 11:48
 * @description UploadUtil
 */
public class UploadUtil {
    // 仓库 域名
    public static final String ALI_DOMAIN = "http://";
    public static String uploadImage(MultipartFile file) throws IOException {

        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String end = "." + FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid+end;

        // 地域节点
        String ENDPOINT = "http://" ;
        String accessKeyId = "";
        String accessKeySecret = "";

        // OSS客户端对象
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, accessKeyId, accessKeySecret);
        ossClient.putObject(
                "",    // 仓库名
                fileName,   // 文件名
                file.getInputStream()
        );
        ossClient.shutdown();

        return ALI_DOMAIN+fileName;
    }

}
