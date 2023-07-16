//弹出新建笔记本对话框
function alertAddBookWindow() {
    //弹出新建笔记本对话框
    $("#can").load("alert/alert_notebook.html");
    //显示背景
    $(".opacity_bg").show();


};

//关闭对话框
function closeAlertWindow() {
    //清空对话框
    $("#can").html("");
    //隐藏背景色
    $(".opacity_bg").hide();

};

//弹出删除笔记对话框
function alertRecycleNoteWindow() {
    //弹出对话框
    $("#can").load("alert/alert_delete_note.html");
    //显示出对话框灰色背景
    $(".opacity_bg").show();

};

function alertAddBookNoteWindow() {
    var userId = getCookie("userId");
    var cn_notebook_name = $("#input_notebook").val();
    var title = $("#input_note").val();
    //数据格式检查
    var ok = true;
    if (title == "") {//判断是否为空
        ok = false;
        $("#title_span").html("标题不能为空！");
    }

    if (userId == null) {//检查是否失效
        ok = false;
        window.location.href = "log_in.html";

    }
    if (ok) {
        $.ajax({
                url: path + "/book/add.do",
                type: "post",
                data: {"userId": userId, "bookName": cn_notebook_name},
                dataType: "json",
                success: function (result) {
                    var note = result.data;
                    if (result.status == 0) {
                        createNoteLi(note.cn_note_id, note.cn_note_title);
                        alert(result.msg);
                    }
                    if (result.status == 1) {
                        alert(result.msg);
                    }
                }
                ,
                error: function () {
                    alert("创建笔记本失败！");
                }


            }
        )
        ;
    }

}
;

function sureAddNote() {
    //获取请求参数
    //获取笔记标题
    var title = $("#input_note").val().trim();
    //获取用户id
    var userId = getCookie("userId");
    //获取笔记本ID
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    //数据格式检查
    var ok = true;
    if (title == "") {//判断是否为空
        ok = false;
        $("#title_span").html("标题不能为空！");
    }

    if (userId == null) {//检查是否失效
        ok = false;
        window.location.href = "log_in.html";

    }
    if (ok) {
        $.ajax({
            url: path + "/note/add.do",
            type: "post",
            data: {"userId": userId, "bookId": bookId, "title": title},
            dataType: "json",
            success: function (result) {
                var note = result.data;
                if (result.status == 0) {
                    /*var id = note.cn_note_id;
                    var title = note.cn_note_title;*/
                    createNoteLi(bookId, title);
                    alert(result.msg);
                    //location.reload();
                }
            },
            error: function () {
                alert("创建笔记失败！")
            }
        });
    }


};


//弹出新建笔记对话框
function alertAddNoteWindow() {
    //判断是否有笔记本被选中
    var $li = $("#book_ul a.checked").parent();
    if ($li.length == 0) {
        alert("请选择笔记本");
    } else {//弹出创建笔记本对话框
        $("#can").load("alert/alert_note.html");
        //显示背景色
        $(".opacity_bg").show();
    }

};

