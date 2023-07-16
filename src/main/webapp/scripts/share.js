//笔记分享操作
function shareNote() {
    //获取参数
    $li = $(this).parents("li");
    var noteId = $li.data("noteId");

    //获取参数
    /* var $li = $("#note_ul a.checked").parent();
     //获取noteId
     var noteId = $li.data("noteId");*/
    $.ajax({
        url: path + "/share/add.do",
        type: "post",
        data: {"noteId": noteId},
        dataType: "json",
        success: function (result) {
            var noteTitle = $li.text();
            var sli = "";
            sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
            sli += noteTitle;
            sli += '<i class="fa fa-sitemap"></i>'
            sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
            //将笔记li元素的a标记内容替换
            $li.find("a").html(sli);
            alert("笔记分享成功");
        },
        error: function () {
            alert("分享笔记失败")
        }

    });

};