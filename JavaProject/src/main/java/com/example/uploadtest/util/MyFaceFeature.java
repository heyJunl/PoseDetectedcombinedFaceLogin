package com.example.uploadtest.util;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @author Jun
 * @date 2022/12/10 9:44
 * @description MyFaceFeature
 */
public class MyFaceFeature {

    public byte[] extract(File file, FaceEngine faceEngine) {
        // 人脸检测
        ImageInfo imageInfo = getRGBData(file);
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        if (faceInfoList == null || faceInfoList.size()==0) {
            return null;
        }

        //人脸属性检测
        FunctionConfiguration configuration = new FunctionConfiguration();
        configuration.setSupportAge(true);
        configuration.setSupportFace3dAngle(true);
        configuration.setSupportGender(true);
        configuration.setSupportLiveness(true);
        int processCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList, configuration);
        // 3D信息检测
        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
        int face3dCode = faceEngine.getFace3DAngle(face3DAngleList);
        if (face3DAngleList == null || face3DAngleList.size() == 0) {
            return null;
        }
        System.out.println("3D角度：" + face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());

        // 活体检测
        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
        int livenessCode = faceEngine.getLiveness(livenessInfoList);
        System.out.println("活体：" + livenessInfoList.get(0).getLiveness());

        // 等于1是活体，-1是没有人脸，0是非活体，2是多张人脸
        if (livenessInfoList.get(0).getLiveness()!=1) {
            return null;
        }

        // 特征提取
        FaceFeature faceFeature = new FaceFeature();
        int extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);

        // 返回特征值
        return faceFeature.getFeatureData();
    }
}
