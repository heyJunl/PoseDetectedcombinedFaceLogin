package com.example.uploadtest.util;

import org.apache.ibatis.annotations.Param;

import java.io.*;
import java.util.Base64;

/**
 * @author Jun
 * @date 2022/12/10 8:59
 * @description Base64Utils
 */
public class Base64Utils {
    /**
     * img2base64
     * @param imgPath
     * @return
     * @throws IOException
     */
    public static String getImgStr(String imgPath) throws IOException {
        String imgFile = imgPath;
        InputStream in = null;
        byte[] data = null;
        String encode = null;

        Base64.Encoder encoder = Base64.getEncoder();
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encodeToString(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            in.close();
        }
        return encode;
    }

    /**
     * base64 2 img
     * @param imgData 图片编码
     * @param imgFilePath 存到本地路径
     * @return
     * @throws IOException
     * 对字节数组字符串进行Base64解码并生成图片
     */
    @SuppressWarnings("finally")
    public static boolean generateImg(String imgData, String imgFilePath) throws IOException {
        if (imgData == null) {
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            byte[] b = decoder.decode(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }
}
