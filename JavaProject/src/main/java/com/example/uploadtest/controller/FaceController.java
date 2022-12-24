package com.example.uploadtest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.example.uploadtest.entity.LoginUser;
import com.example.uploadtest.entity.User;
import com.example.uploadtest.service.LoginUserService;
import com.example.uploadtest.util.Base64Utils;
import com.example.uploadtest.util.FaceContrast;
import com.example.uploadtest.util.MyFaceFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jun
 * @date 2022/12/10 10:25
 * @description FaceController
 */
@RestController
@CrossOrigin
public class FaceController {
    FaceEngine faceEngine = new FaceEngine();{
        // 引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();

        // 检测模式， 选择图片
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);

        // 人脸检测角度
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

        // 功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        //是否支持年龄检测
        functionConfiguration.setSupportAge(true);
        //是否支持3D人脸检测
        functionConfiguration.setSupportFace3dAngle(true);
        //是否支持人脸检测
        functionConfiguration.setSupportFaceDetect(true);
        //是否支持人脸识别
        functionConfiguration.setSupportFaceRecognition(true);
        //是否支持性别检测
        functionConfiguration.setSupportGender(true);
        //是否支持RGB活体检测
        functionConfiguration.setSupportLiveness(true);
        //是否支持RGB活体检测
        functionConfiguration.setSupportIRLiveness(true);

        // 设置引擎功能配置
        engineConfiguration.setFunctionConfiguration(functionConfiguration);

        // 初始化引擎
        int initCode = faceEngine.init(engineConfiguration);

        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
    }

    @Autowired
    private LoginUserService faceService;

    @RequestMapping("add")
    public String faceAdd(String user_id, String base) {
        // 去掉base64编码中的图片头信息
        String base64 = base.split(",")[1];
        try {
            //base64转图片
            Base64Utils.generateImg(base64, "./temp/temp.jpg");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取人脸特征
        byte[] extract = new MyFaceFeature().extract(new File("./temp/"+"temp.jpg"), faceEngine);
        if(extract == null) {
            return "检测人脸失败";
        }
//        String extracts = new String(extract);
        System.out.println(user_id);
        System.out.println(extract);
        //把人脸特征报错到数据库
//        return faceService.faceAdd(new LoginUser(user_id,extract))?"{\"msg\":\"注册成功\"}":"{\"msg\":\"注册失败\"}";
        return faceService.faceAdd(new LoginUser(user_id,extract))?"注册成功":"注册失败";
    }


    @RequestMapping("login")
    public String login(String base) {
        // 去掉base64编码中的图片头信息
        String base64 = base.split(",")[1];
        try {
            //base64转图片
            Base64Utils.generateImg(base64, "./temp/temp.jpg");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取人脸特征
        byte[] extract = new MyFaceFeature().extract(new File("./temp/"+"temp.jpg"), faceEngine);
        if(extract == null) {
            return null;
        }
        //查询所有人脸 一一对比
        ////人脸对比对象
        FaceContrast faceContrast = new FaceContrast();
        ////获取所有注册过的用户
        List<LoginUser> userList = faceService.getAllUserFace();

        ////循环对比
        for(LoginUser user : userList) {
            System.out.println(user);
//            System.out.println(user.getExtract());
//            System.out.println(faceContrast.contrast(extract, user.getExtract(), faceEngine)>0.8);
            //如果大于0.8就表示是同一人
            if(faceContrast.contrast(extract, user.getExtract(), faceEngine)>0.8) {
                System.out.println("成功");
//                System.out.println(user.getUsername());
                String username= user.getUsername();
//                return "{\"user_id\":\""+user.getUsername()+"\"}";
//                JSONObject jsonOb = JSONObject.parseObject(json);
                return username;
            }
        }
        System.out.println("失败");
        return null;
    }
}
