//判断浏览器是否支持HTML5 Canvas
window.onload = function() {
    try {
        //动态创建一个canvas元 ，并获取他2Dcontext。如果出现异常则表示不支持
        document.createElement("canvas").getContext("2d");
    } catch(e) {
        document.getElementByIdx("support").innerHTML = "浏览器不支持HTML5 CANVAS";
    }
}


//这段代 主要是获取摄像头的视频流并显示在Video 签中
window.addEventListener("DOMContentLoaded", function() {
    var canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d"),
        video = document.getElementById("video"),
        videoObj = {
            "video": true
        },
        errBack = function(error) {
            console.log("Video capture error: ", error.code);
            alert("不支持");
        };

    canvas.width = 450;
    canvas.height = 600;

    //请求摄像头权限
    this.navigator.mediaDevices.getUserMedia(videoObj)
        .then(function(stream){
            video.srcObject = stream;
            video.play();
            //拍照按钮，每0.5秒canvas从video中画到画布，转成base64发给后台
            var login = setInterval(function() {
                context.drawImage(video, 0, 0, 450, 600);
                // 把画布的内容转换为base64编码格式的图片
                var data = canvas.toDataURL( 'image/png', 1 );  //1表示质量(无损压缩)

                $.ajax({
                    url: 'http://172.16.2.207:8080/face/login',
                    cache:false,
                    type: 'POST',
                    data: {
                        base: data
                    },
                    dataType: 'json',
                    success : function(rs){
                        if(rs!=null){
                            document.getElementById("msg").innerText = "用户"+rs.user_id+"登录成功";
                            //隐藏控件
                            $("#video").hide();
                            $("#canvas").hide();

                            //停止定时发送
                            clearInterval(login);
                        }
                    },
                    error: function(e){
                    }
                });

            }, 500);
        })
        .catch(errBack);


}, false);