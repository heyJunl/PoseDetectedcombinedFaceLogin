# -*- coding: UTF-8 -*-
'''
@File    ：main.py
@IDE     ：PyCharm 
@Author  ：LiJun
@Date    ：2022/12/5 9:37
'''
import os

import cv2
import numpy as np

from PoseDetector import PoseDetector


def main(video):
    # opencv打开一个视频
    basePath = os.path.dirname(video)
    cap = cv2.VideoCapture(video)
    # fourcc = cv2.VideoWriter_fourcc(*'XVID')
    fourcc = cv2.VideoWriter_fourcc('H', '2', '6', '4')
    fps = cap.get(cv2.CAP_PROP_FPS)
    size = (int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)), int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)))
    # out = cv2.VideoWriter(os.path.join(basePath, 'show.mp4'), fourcc, fps, size)
    out = cv2.VideoWriter(r"D:\JavaProject\uploadtest\src\main\resources\static\show.mp4", fourcc, fps, size)

    # 创建一个PoseDetector类的对象
    detector = PoseDetector()
    # 方向和完成次数的变量
    dir = 0
    count = 0

    print(basePath)
    while True:
        # 读取视频图片帧
        success, img = cap.read()
        if success:
            # 检测视频图片帧中人体姿势
            img = detector.find_pose(img, draw=True)
            # 获取人体姿势列表数据
            lmslist = detector.find_positions(img)
            if not lmslist:
                break
            # print(lmslist)
            # 右手肘的角度
            right_angle = detector.find_angle(img, 12, 14, 16)
            # 以170到20度检测右手肘弯曲的程度
            right_per = np.interp(right_angle, (20, 170), (100, 0))
            # 进度条高度数据
            right_bar = np.interp(right_angle, (20, 170), (200, 400))
            # 使用opencv画进度条和写右手肘弯曲的程度
            cv2.rectangle(img, (1200, 200), (1220, 400), (0, 255, 0), 3)
            cv2.rectangle(img, (1200, int(right_bar)), (1220, 400), (0, 255, 0), cv2.FILLED)
            cv2.putText(img, str(int(right_per)) + '%', (1190, 450), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 255), 2)

            # 左手肘的角度
            left_angle = detector.find_angle(img, 11, 13, 15)
            left_per = np.interp(left_angle, (20, 170), (100, 0))
            left_bar = np.interp(left_angle, (20, 170), (200, 400))
            cv2.rectangle(img, (500, 200), (520, 400), (0, 255, 0), 3)
            cv2.rectangle(img, (500, int(left_bar)), (520, 400), (0, 255, 0), cv2.FILLED)
            cv2.putText(img, str(int(left_per)) + '%', (490, 450), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 255), 2)

            # 检测个数，我这里设置的是从20%做到80%，就认为是一个
            if (left_per >= 80 and right_per >= 80):
                if dir == 0:
                    count = count + 0.5
                    dir = 1
            if (left_per <= 20 and right_per <= 20):
                if dir == 1:
                    count = count + 0.5
                    dir = 0

            # 在视频上显示完成个数
            cv2.putText(img, str(int(count)), (1000, 100), cv2.FONT_HERSHEY_SIMPLEX, 3, (0, 255, 255), 4)
            # print(str(int(count)))
            out.write(img)
            # cv2.imshow('Image', img)


        else:
            break
        k = cv2.waitKey(1)
        if k == ord('q'):
            break
    print(os.path.join(basePath, 'show.mp4'))
    # cv2.imwrite(os.path.join(basePath, 'show.mp4'), img)
    cap.release()
    cv2.destroyAllWindows()
    return str(int(count))
    # print(str(int(count)))

# if __name__ == '__main__':
#     main(r'D:\PythonProject\flaskProject2\static\images\886104537-1-208.mp4')
