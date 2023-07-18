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

function moreSearchShare() {
    //获取参数
    var shareTitle = $("#search_note").val().trim();
    page = page + 1;
    //发送ajax请求加载列表
    loadPageShare(shareTitle, page);

};

function loadPageShare(shareTitle, page) {
    $.ajax({
        url: path + "/share/search.do",
        type: "post",
        data: {"shareTitle": shareTitle, "page": page},
        dataType: "json",
        success: function (result) {
            //获取数据
            var sharesList = result.data;
            //
            for (var i = 0; i < sharesList.length; i++) {
                //获取shareid
                var shareId = sharesList[i].shareId;
                //获取sharetitle
                var shareTitle = sharesList[i].shareTitle;
                //获取li对象
                var sli = "";
                sli += '<li class="online">';
                sli += '<a>';
                sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                sli += shareTitle;
                sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                sli += '</a>';
                sli += '</li>';
                //转换对象
                var $li = $(sli);
                //绑定shareId
                $li.data("shareId", shareId);
                //将li对象添加到ul当中
                $("#search_ul").append($li);
                //切换显示区
                $("#pc_part_2").hide();//隐藏
                $("#pc_part_6").show();


            }


        },
        error: function () {
            alert("搜索失败");
        }
    });
};

function sureSearchShare(event) {
    var code = event.keyCode;
    if (code == 13) {
        $("#search_ul dl").remove();
        //获取请求参数
        var shareTitle = $("#search_note").val().trim();
        page = 1;
        loadPageShare(shareTitle, page);
    }
};