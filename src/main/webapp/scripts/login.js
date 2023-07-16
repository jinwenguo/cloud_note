function userLogin() {// ���¼�
    //alert("���Ķ��ˣ�");

    //��ȡ����
    var name = $("#count").val().trim();
    var password = $("#password").val().trim();
    //alert(name+","+password);
    //�����ʾ��Ϣ
    $("#count_span").html("");
    $("#password_span").html("");
    //��ʽ���
    var ok = true;
    if (name == "") {
        $("#count_span").html("�û�������Ϊ��");
        ok = false;
    }
    if (password == "") {
        $("#password_span").html("���벻��Ϊ��");
        ok = false;
    }

    if (ok) {//����ʽͨ��
        //��������
        $.ajax({
            url: path + "/user/login.do",
            type: "post",
            data: {
                "name": name,
                "password": password
            },
            dataType: "json",
            success: function (result) {
                //result�Ƿ���������json���
                if (result.status == 0) {
                    //���û���Ϣ���浽cookie
                    var userId = result.data.cn_user_id;
                    addCookie("userId", userId, 2);
                    window.location.href = "edit.html";

                } else if (result.status == 1) {//�û�������
                    $("#count_span").html(result.msg);
                } else if (result.status == 2) {//�������
                    $("#password_span").html(result.msg);
                }
            },
            error: function () {
                alert("��¼ʧ�ܣ�");
            }
        });
    }

};

//ע�ᴦ��
function regist() {
    //�����ʾ��Ϣ
    $("#warning_1 span").html("");
    $("#warning_2 span").html("");
    $("#warning_3 span").html("");
    $("#birthday").html("");
    $("#warning_1").hide();
    $("#warning_2").hide();
    $("#warning_3").hide();

    //��ȡ�������
    var name = $("#regist_username").val().trim();
    var nick = $("#nickname").val().trim();
    var password = $("#regist_password").val().trim();
    var final_password = $("#final_password").val().trim();
    var birthday = $("#birthday").val().trim();
    //����ʽ
    var check = true;
    if (name == "") {
        var aaa = "�û���Ϊ��";
        console.log(aaa);
        $("#warning_1 span").html(aaa);
        $("#warning_1").show();
        check = false;
    }
    if (password == "") {
        $("#warning_2 span").html("����Ϊ��");
        $("#warning_2").show();
        check = false;
    } else if (password.length < 6) {
        $("#warning_2 span").html("������ڵ���6λ");
        $("#warning_2").show();
        check = false;
    }
    if (final_password == "") {
        $("#warning_3 span").html("ȷ������Ϊ��");
        $("#warning_3").show();
        check = false;
    } else if (final_password != password) {
        $("#warning_3 span").html("�����벻һ��");
        $("#warning_3").show();
        check = false;
    }
    const user = {
        "cn_user_name": name,
        "cn_user_password": password,
        "cn_user_nick": nick,
        "birthday": birthday
    };
    //����Ajax����
    if (check) {
        /* var data = $("#reg-form").serialize();*/
        $.ajax({
            url: path + "/user/regist.do",
            type: "post",
            data: JSON.stringify(user),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                if (result.status == 0) {//�ɹ�
                    //��ʾ�ɹ�
                    alert(result.msg);
                    //�л�����¼����
                    $("#back").click();//������������
                } else if (result.status == 1) {
                    //��ʾ�û�����ռ��
                    $("#warning_1 span").html(result.msg);
                    $("#warning_1").show();
                }
            },
            error: function () {
                alert("ע�ᷢ���쳣");
            }
        });
    }
};