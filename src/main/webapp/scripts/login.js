function userLogin() {// 绑定事件
    //alert("堵哪儿了？");

    //获取参数
    var name = $("#count").val().trim();
    var password = $("#password").val().trim();
    //alert(name+","+password);
    //清空提示信息
    $("#count_span").html("");
    $("#password_span").html("");
    //格式检测
    var ok = true;
    if (name == "") {
        $("#count_span").html("用户名不能为空");
        ok = false;
    }
    if (password == "") {
        $("#password_span").html("密码不能为空");
        ok = false;
    }

    if (ok) {//检测格式通过
        //发送请求
        $.ajax({
            url: path + "/user/login.do",
            type: "post",
            data: {
                "name": name,
                "password": password
            },
            dataType: "json",
            success: function (result) {
                //result是服务器返回json结果
                if (result.status == 0) {
                    //将用户信息保存到cookie
                    var userId = result.data.cn_user_id;
                    addCookie("userId", userId, 2);
                    window.location.href = "edit.html";

                } else if (result.status == 1) {//用户名错误
                    $("#count_span").html(result.msg);
                } else if (result.status == 2) {//密码错误
                    $("#password_span").html(result.msg);
                }
            },
            error: function () {
                alert("登录失败！");
            }
        });
    }

};

//注册处理
function regist() {
    //清空提示信息
    $("#warning_1 span").html("");
    $("#warning_2 span").html("");
    $("#warning_3 span").html("");
    $("#birthday").html("");
    $("#warning_1").hide();
    $("#warning_2").hide();
    $("#warning_3").hide();

    //获取请求参数
    var name = $("#regist_username").val().trim();
    var nick = $("#nickname").val().trim();
    var password = $("#regist_password").val().trim();
    var final_password = $("#final_password").val().trim();
    var birthday = $("#birthday").val().trim();
    //检查格式
    var check = true;
    if (name == "") {
        var aaa = "用户名为空";
        console.log(aaa);
        $("#warning_1 span").html(aaa);
        $("#warning_1").show();
        check = false;
    }
    if (password == "") {
        $("#warning_2 span").html("密码为空");
        $("#warning_2").show();
        check = false;
    } else if (password.length < 6) {
        $("#warning_2 span").html("密码大于等于6位");
        $("#warning_2").show();
        check = false;
    }
    if (final_password == "") {
        $("#warning_3 span").html("确认密码为空");
        $("#warning_3").show();
        check = false;
    } else if (final_password != password) {
        $("#warning_3 span").html("与密码不一致");
        $("#warning_3").show();
        check = false;
    }
    const user = {
        "cn_user_name": name,
        "cn_user_password": password,
        "cn_user_nick": nick,
        "birthday": birthday
    };
    //发送Ajax请求
    if (check) {
        /* var data = $("#reg-form").serialize();*/
        $.ajax({
            url: path + "/user/regist.do",
            type: "post",
            data: JSON.stringify(user),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                if (result.status == 0) {//成功
                    //提示成功
                    alert(result.msg);
                    //切换到登录界面
                    $("#back").click();//触发单击操作
                } else if (result.status == 1) {
                    //提示用户名已占用
                    $("#warning_1 span").html(result.msg);
                    $("#warning_1").show();
                }
            },
            error: function () {
                alert("注册发生异常");
            }
        });
    }
};