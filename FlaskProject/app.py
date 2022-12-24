import os
import sys
from datetime import timedelta

import cv2
import numpy as np
from flask import Flask, render_template, request, jsonify, Response

import main
from PoseDetector import PoseDetector

app = Flask(__name__)

app.config['SECRET_KEY'] = 'Jun'

# 文件上传后缀要求
ALLOWED_EXTENSIONS = set(['png', 'jpg', 'JPG', 'PNG', 'bmp', 'mp4'])
# 避免JSON中文乱码
app.config['JSON_AS_ASCII'] = False
# 静态文件缓存时间
app.send_file_max_age_default = timedelta(seconds=1)


def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS


@app.route('/upload', methods=['POST', 'GET'])
def upload():  # put application's code here
    if request.method == 'POST':
        f = request.files['file']
        userName = f.filename.split('.')[0]
        if not (f and allowed_file(f.filename)):
            return jsonify({"error": 1001, "msg": "请检查上传的图片类型，仅限于png、PNG、jpg、JPG、bmp、ts"})
        basepath = os.path.dirname(__file__)  # 当前文件所在路径
        upload_path = os.path.join(basepath, 'static\\images', 'test.mp4')
        f.save(upload_path)
        num = main.main(upload_path)
        json_dict = {
            "user_name": userName,
            "user_num": num
        }
        print(json_dict)
        return jsonify(json_dict)
        # return render_template('upload_ok.html', movie='static/images/show.mp4')
    # return render_template('upload.html')


if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000)
