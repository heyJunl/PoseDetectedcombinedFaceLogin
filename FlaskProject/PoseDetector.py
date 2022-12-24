# -*- coding: UTF-8 -*-
'''
@File    ：PoseDetector.py
@IDE     ：PyCharm 
@Author  ：LiJun
@Date    ：2022/12/5 9:15 
'''
import cv2
import mediapipe as mp
import math

class PoseDetector():
    '''
    人体姿势检测类
    '''
    def __init__(self,
                 static_image_mode=False,
                 upper_body_only=False,
                 smooth_landmarks=True,
                 min_detection_confidence=True,
                 min_tracking_confidence=0.5):
        '''
        初始化
        :param static_image_mode: 是否是静态图片，默认为否
        :param upper_body_only: 是否是上半身，默认为否
        :param smooth_landmarks: 设置为True减少抖动
        :param min_detection_confidence:人员检测模型的最小置信度值，默认为0.5
        :param min_tracking_confidence:姿势可信标记的最小置信度值，默认为0.5
        '''
        self.static_image_mode = static_image_mode
        self.upper_body_only = upper_body_only
        self.smooth_landmarks = smooth_landmarks
        self.min_detection_confidence = min_detection_confidence
        self.min_tracking_confidence = min_tracking_confidence
        # 创建一个Pose对象用于检测人体姿势
        self.pose = mp.solutions.pose.Pose(self.static_image_mode, self.upper_body_only, self.smooth_landmarks,
                                           self.min_detection_confidence, self.min_tracking_confidence)

    def find_pose(self, img, draw=True):
        '''
        检测姿势方法
        :param img: 一帧图像
        :param draw: 是否画出人体姿势节点和连接图
        :return: 处理过的图像
        '''
        imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        # pose.process(imgRGB) 会识别这帧图片中的人体姿势数据，保存到self.results中
        self.results = self.pose.process(imgRGB)
        if self.results.pose_landmarks:
            if draw:
                mp.solutions.drawing_utils.draw_landmarks(img, self.results.pose_landmarks,
                                                          mp.solutions.pose.POSE_CONNECTIONS)
        return img

    def find_positions(self, img):
        '''
        获取人体姿势数据
        :param img: 一帧图像
        :param draw: 是否画出人体姿势节点和连接图
        :return: 人体姿势数据列表
        '''
        # 人体姿势数据列表，每个成员由3个数字组成：id, x, y
        # id代表人体的某个关节点，x和y代表坐标位置数据
        self.lmslist = []
        if self.results.pose_landmarks:
            for id, lm in enumerate(self.results.pose_landmarks.landmark):
                h, w, c = img.shape
                cx, cy = int(lm.x * w), int(lm.y * h)
                self.lmslist.append([id, cx, cy])

        return self.lmslist

    def find_angle(self, img, p1, p2, p3, draw=True):
        '''
        获取人体姿势中3个点p1-p2-p3的角度
        :param img: 一帧图像
        :param p1: 第1个点
        :param p2: 第2个点
        :param p3: 第3个点
        :param draw: 是否画出3个点的连接图
        :return: 角度
        '''
        x1, y1 = self.lmslist[p1][1], self.lmslist[p1][2]
        x2, y2 = self.lmslist[p2][1], self.lmslist[p2][2]
        x3, y3 = self.lmslist[p3][1], self.lmslist[p3][2]

        # 使用三角函数公式获取3个点p1-p2-p3，以p2为角的角度值，0-180度之间
        angle = int(math.degrees(math.atan2(y1-y2, x1-x2) - math.atan2(y3-y2, x3-x2)))
        if angle < 0:
            angle = angle + 360
        if angle > 180:
            angle = 360 - angle

        if draw:
            cv2.circle(img, (x1, y1), 8, (0, 255, 255), cv2.FILLED)
            cv2.circle(img, (x2, y2), 15, (255, 0, 255), cv2.FILLED)
            cv2.circle(img, (x3, y3), 8, (0, 255, 255), cv2.FILLED)
            cv2.line(img, (x1, y1), (x2, y2), (255, 255, 255, 3))
            cv2.line(img, (x2, y2), (x3, y3), (255, 255, 255, 3))
            #cv2.putText(img, str(angle), (x2-50, y2+50),cv2.FONT_HERSHEY_SIMPLEX, 2, (0, 255, 255), 2)

        return angle

