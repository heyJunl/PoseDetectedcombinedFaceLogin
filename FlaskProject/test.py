# -*- coding: UTF-8 -*-
'''
@File    ：test.py
@IDE     ：PyCharm 
@Author  ：LiJun
@Date    ：2022/12/6 15:32 
'''
import cv2
img = cv2.imread('test.png')
cv2.imshow('te', img)
# cv2.imwrite('./new.mp4', img)
cv2.waitKey()
cv2.destroyAllWindows()