package com.example.uploadtest.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Jun
 * @date 2022/12/7 16:02
 * @description HttpFileUtil
 */
public class HttpFileUtil {
    /**
     * 以post方式调用第三方接口,以form-data 形式  发送 MultipartFile 文件数据
     * @param url  post请求url
     * @param fileParamName 文件参数名称
     * @param multipartFile  文件
     */
//    public static String doPostFormData(String url, String fileParamName, MultipartFile multipartFile, Map<String, String> paramMap) {
    public static String doPostFormData(String url, String fileParamName, MultipartFile multipartFile) {
        // 创建Http实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建HttpPost实例
        HttpPost httpPost = new HttpPost(url);
        // 请求参数配置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(600000)
                .setConnectionRequestTimeout(600000).build();
        httpPost.setConfig(requestConfig);
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(StandardCharsets.UTF_8);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            String fileName = multipartFile.getOriginalFilename();
            // 文件流
            builder.addBinaryBody(fileParamName, multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
            //表单中其他参数，如果没有其他参数可以注释该部分
//            for(Map.Entry<String, String> entry: paramMap.entrySet()) {
//                builder.addPart(entry.getKey(),new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8)));
//            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            // 执行提交
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回
                String res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 接收图片
     */
//    @PostMapping("/acceptImg")
//    public String acceptImg(@RequestParam("img") MultipartFile file) throws Exception {
//        System.out.println("打印："+file);
//        return service.imgSave(file);
//    }
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/static/mn.mp4");
        //File文件转MultipartFile
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        System.out.println(HttpFileUtil.doPostFormData("http://localhost:5000/upload", "file", multipartFile));
    }
}
