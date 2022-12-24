package com.example.uploadtest.util;

import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceSimilar;

/**
 * @author Jun
 * @date 2022/12/10 10:04
 * @description FaceContrast
 */
public class FaceContrast {
    public float contrast(byte[] face1, byte[] face2, FaceEngine faceEngine) {
        // 特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(face1);
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(face2);
        FaceSimilar faceSimilar = new FaceSimilar();
        int compareCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        // 返回相似度
        return faceSimilar.getScore();
    }
}
