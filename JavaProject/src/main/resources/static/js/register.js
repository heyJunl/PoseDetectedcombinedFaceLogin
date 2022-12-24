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
        img = document.getElementById("img"),
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

    var button = document.getElementById("ok");
    button.onclick = function(){
        context.drawImage(video, 0, 0, 450, 600);
        // 把画布的内容转换为base64编码格式的图片
        var data = canvas.toDataURL( 'image/png', 1 );  //1表示质量(无损压缩)
        var user_id = document.getElementById("user_id").value;
        document.getElementById("msg").innerText = "注册中……";

        $.ajax({
            url: 'http://172.16.2.207:8080/face/add',
            cache:false,
            type: 'POST',
            data: {
                base : data,
                user_id : user_id
            },
            dataType: 'json',
            success : function(rs){
                document.getElementById("msg").innerText = rs.msg;
            },
            error: function(e){
                console.log(e);
            }

        });
    }

    //请求摄像头权限
    navigator.mediaDevices.getUserMedia(videoObj)
        .then(function(stream){
            //成功回调函数，把流给video标签
            video.srcObject = stream;
            video.play();
        })
        .catch(errBack);

}, false);