//更新笔记信息
function updataNote() {
    //获取参数
    var $li = $("#note_ul a.checked").parent();

    //获取noteId
    var noteId = $li.data("noteId");

    //获取笔记的标题和内容
    var noteTitle = $("#input_note_title").val().trim();

    var noteBody = um.getContent();
    $.ajax({
        url: path + "/note/updata.do",
        type: "post",
        data: {"noteId": noteId, "title": noteTitle, "body": noteBody},
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                var str = "";
                str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                str += noteTitle;
                str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                //将str替换到lid的a元素里
                $li.find("a").html(str);
                //提示成功
                alert(result.msg);

            }

        },
        error: function () {
            alert("保存笔记失败！");

        }
    });
};


//显示笔记信息
function loadnote() {
    //设置选中效果
    $("#note_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取请求参数
    var noteId = $(this).data("noteId");
    //  alert(noteId);
    //发送ajax请求
    $.ajax({
        url: path + "/note/load.do",
        type: "post",
        data: {"noteId": noteId},
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                //获取返回的标题
                var title = result.data.cn_note_title;
                //获取返回的笔记内容
                var body = result.data.cn_note_body;
                //设置笔记标题
                $("#input_note_title").val(title);
                //设置笔记内容
                um.setContent(body);
            }


        },
        error: function () {
            alert("笔记加载信息失败")
        }

    });

};


//加载笔记本笔记
function loadBookNotes() {
    // alert("绑定成功！");
    $("#book_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取参数
    var bookId = $(this).data("bookId");
    // alert(bookId);
    //发送ajax请求
    $.ajax({
        url: path + "/note/loadnotes.do",
        type: "post",
        data: {"bookId": bookId},
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                //获取笔记信息
                var notes = result.data;
                //清楚源列表信息
                $("#note_ul").empty();
                //循环添加li
                for (var i = 0; i < notes.length; i++) {
                    //获取笔记id
                    var noteId = notes[i].cn_note_id;
                    var noteTitle = notes[i].cn_note_title;
                    //生成笔记li
                    createNoteLi(noteId, noteTitle);
                }
            }
        },
        error: function () {
            alert("笔记加载失败！");
        }
    });

};


function createNoteLi(noteId, noteTitle) {
    var sli = "";
    sli += '<li class="online">';
    sli += '<a>';
    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
    sli += noteTitle;
    sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += '</a>';
    sli += '<div class="note_menu" tabindex="-1">';
    sli += '<dl>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli += '</dl>';
    sli += '</div>';
    sli += '</li>';
    var $li = $(sli);
    $li.data("noteId", noteId);//给li元素绑定笔记id值
    //添加到笔记列表ul元素中
    $("#note_ul").append($li);

};