jQuery(function ($) {
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));
});

$(document).ajaxError(function (event, request, settings) {
    userValidate(request);
});

$(document).ajaxComplete(function (event, request, settings) {
    userValidate(request);
});


//$(document).ready(
//    function () {
//        if ($("#hidden_error_message").val().length > 0) {
//            alert($("#hidden_error_message").val());
//        }
//
//    }
//);

function userValidate(request) {
    if (/登录窗口/.test(request.responseText)) {
        alert("登录超时，系统将会跳转到登录界面。请重新登录！");
        window.location.href = '/' + (window.location.pathname.split('/').length > 2 ? window.location.pathname.split('/')[1] + '/' : '') + 'login.jsp';
    }
}

function showSexStatus(status) {
    switch (status) {
        case "0":
            return "女";//'<span class="label label-success arrowed-in">女</span>';
            break;
        case "1":
            return "男";//'<span class="label  label-success arrowed-right">男</span>';
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";//'<span class="label  label-warning">未知</span>';
    }
}
function showDeliveryPersonCurStatus(status) {
    switch (status) {
        case 0:
            return "休息中";
            break;
        case 1:
            return "空闲中";
            break;
        case 2:
            return "已接单";
            break;
        case 3:
            return "配送中";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";//'<span class="label  label-warning">未知</span>';
    }
}
function showDeliveryPersonFullTimeFlag(status) {
    switch (status) {
        case 0:
            return "兼职";
            break;
        case 1:
            return "全职";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";//'<span class="label  label-warning">未知</span>';
    }
}
function showDeviceId(status){
    if(null==status || status ==''){
        return "未绑定";
    }else{
        return "已绑定";
    }
}
function showDeliveryPersonSource(status){
    switch (status) {
        case "STORE":
            return "商家无忧哥";
            break;
        case "SELF" :
            return "自营无忧哥";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";//'<span class="label  label-warning">未知</span>';
    }
}
function showDeliveryPersonStatusFlag(status) {
    switch (status) {
        case 1:
            return "正常";
            break;
        case 2:
            return "锁定";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";
    }
}
function showUserStoreStatusFlag(status) {
    switch (status) {
        case 1:
            return "正常";
            break;
        case 2:
            return "锁定";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";
    }
}
function getValue(id) {
    return $(id).val();
}
//must选项控制，是否用户可以删减该功能
var featuresAll = {"all": [
    {"id": "VISIBLE-STORE", "name": "可见商户管理", "icon": "icon-envelope", view: "store.action", href: "visiblestore"},
    {"id": "INVISIBLE-STORE", "name": "不可见商户管理", "icon": "icon-bullhorn", view: "invisiblestore.action", href: "invisiblestore"},
    {"id": "ORDER", "name": "订单管理", "icon": "icon-calendar", view: "order.action", href: "order"},
    {"id": "CUSTOMER", "name": "客户管理", "icon": "icon-folder-open", view: "customer.action", href: "customer"},
    {"id": "DELIVERY-PERSON", "name": "无忧哥用户管理", "icon": "icon-phone", view: "deliveryperson.action", href: "deliveryperson"},
    {"id": "USER-STORE", "name": "商家用户管理", "icon": "icon-check", view: "userstore.action", href: "userstore"},
    {"id": "SECTION", "name": "板块管理", "icon": "icon-group", view: "section.action.action", href: "section"},
    {"id": "SECTION-MAP", "name": "板块分布图", "icon": "icon-picture", view: "sectionmap.action", href: "sectionmap"}
]
};


$.FormatDateTime = function (obj, IsMi) {
    var myDate = new Date(obj);
    var year = myDate.getFullYear();
    var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
    var day = ("0" + myDate.getDate()).slice(-2);
    var h = ("0" + myDate.getHours()).slice(-2);
    var m = ("0" + myDate.getMinutes()).slice(-2);
    var s = ("0" + myDate.getSeconds()).slice(-2);
    var mi = ("00" + myDate.getMilliseconds()).slice(-3);
    if (IsMi == true) {
        return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
    }
    else {
        return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s + "." + mi;
    }
};

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}



function showWidget(id) {
    $(id).show();
}
function hideWidget(id) {
    $(id).hide();
}

function addBlurLinstener(id) {
    $(id).blur(function () {
        if (judgeNull($(id).val())) {
            addBorder(id);
        } else {
            removeBorder(id);
        }
    });
}
function addBorder(id) {
    $(id).css("border", "1px solid red");
}
function removeBorder(id) {
    $(id).css("border", "");
}

function removeFormInputBorders(formId) {
    $(formId).find("input").each(function(index,element){
        $(this).css("border", "");
    });

}

function trim(str) { //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function judgeNull(str) {
    if (null == str || "" == trim(str) || '' == trim(str)) {
        return true;
    }
    return false;
}
function clearValue(id) {
    $(id).val("");
}
function setValue(id, value) {
    $(id).val(value);
}
function myvalidate(id) {
    var value = $(id).val();
    if (judgeNull(value)) {
        addBorder(id);
        return false;
    }
    removeBorder(id);
    return true;
}

function movtip(e) {
    var ele, x, y;
    if (e.target) {
        ele = e.target;
        x = e.layerX;
        y = e.layerY;
    }
    else {
        ele = event.srcElement;
        x = event.x;
        y = event.y;
    }
    if (ele.tagName != "A") {
        document.title = ele.tagName;
        return;
    }
    ele = ele.getElementsByTagName("span")[0];
    with (ele.style) {
        left = x + 3 + "px";
        top = y + 3 + "px";
    }
}

function selectSection(pageSize, valueElementId, nameElementId) {//

    if(!(valueElementId && nameElementId)) {
        valueElementId = $('#onSiteFlagQuery').data("valueElementId");
        nameElementId = $('#onSiteFlagQuery').data("nameElementId");
    } else {
        $('#onSiteFlagQuery').data("valueElementId", valueElementId);
        $('#onSiteFlagQuery').data("nameElementId", nameElementId);
    }

    var currentPage;
    if (pageSize == null) {
        currentPage = 1;
    } else {
        currentPage = pageSize;
    }
    $("#section_table_list tbody").html("");
    $("#sectionPagingator").html("");

    $.ajax({
        url: getValue('#xqwy_cxt') + "/section/list",
        type: "post",
        data: {
            currentPage: pageSize,
            name: getValue('#onSiteFlagQuery')
        },
        success: function (data, textStatus, jqXHR) {
            var columnTemplate = "<tr>" +
                "<td class='center'><input type='radio' name='onSiteFlag' style='opacity:0.5;' class='ace' value='${onSiteFlag}_${sectionName}_${city}_${address}'/></td>" +
                "<td>${sectionName}</td><td>${address}</td></tr> ";

            var innerHtml = "";
            for (var i = 0; i < data.sectionDtoList.length; i++) {
                var sectionDto = data.sectionDtoList[i],
                    innerElementHtml = columnTemplate,
                    innerElementHtml = innerElementHtml.replace("${onSiteFlag}", sectionDto.onSiteFlag);
                innerElementHtml = innerElementHtml.replace("${sectionName}", sectionDto.sectionName);
                innerElementHtml = innerElementHtml.replace("${city}", sectionDto.city);
                innerElementHtml = innerElementHtml.replace("${address}", sectionDto.address);
                innerElementHtml = innerElementHtml.replace("${sectionName}", sectionDto.sectionName);
                innerElementHtml = innerElementHtml.replace("${address}", sectionDto.address);
                innerHtml = innerHtml + innerElementHtml;
            }
            $("#section_table_list tbody").append(innerHtml);
            $("#section_table_list tr:gt(0)").dblclick(function(){
                var radioValue =$(this).children("td").eq(0).children("input").val();
                var indexArray = radioValue.split("_");
                var value = indexArray[0];
                var name = indexArray[1];
                var city = indexArray[2];
                var address = indexArray[3];

                $("#"+valueElementId).val(value);
                $("#"+nameElementId).val(name);

                $('#section-distributor-on-site-flag').val(value);
                $('#section-distributor-section-name').val(name);
                $('#section-distributor-city').val(city);
                $('#section-distributor-address').val(address);
                $("#section_table_list tbody").html("");
                $("#searchSectionPagingator").html("");
                $("#section-dialog").dialog("close");
            });

            var pagingatorHtml = "<div class='row'><div class='col-sm-12'>" +
                "<div class='dataTables_paginate paging_bootstrap'><ul class='pagination'>${leftArrow}${pagingatorCell}${rightArrow}" +
                "</ul></div></div></div>";
            if (currentPage <= 1) {
                pagingatorHtml = pagingatorHtml.replace("${leftArrow}", "<li class='prev disabled'><a href='#'>" +
                    "<i class='icon-double-angle-left'></i></a></li>");
            } else {
                pagingatorHtml = pagingatorHtml.replace("${leftArrow}", "<li class='prev disabled'><a href='#' onclick=\"selectSection(" + (currentPage - 1) + ", '"+valueElementId+"','"+nameElementId+"')\">" +
                    "<i class='icon-double-angle-left'></i></a></li>");
            }
            var cellHtml = '';
            for (var i = 0; i < data.totalPageSize; i++) {
                if (i + 1 == currentPage) {
                    cellHtml += "<li class='active'><a href='#'>" + (i + 1) + "</a></li>";
                } else {
                    cellHtml += "<li onclick=\"selectSection(" + (i + 1) + ", '"+valueElementId+"','"+nameElementId+"')\"><a href='#'>" + (i + 1) + "</a></li>";
                }
            }
            pagingatorHtml = pagingatorHtml.replace("${pagingatorCell}", cellHtml);

            if (data.totalPageSize == currentPage) {
                pagingatorHtml = pagingatorHtml.replace("${rightArrow}", "<li class='next disabled'><a href='#'><i class='icon-double-angle-right'></i></a></li>");
            } else {
                pagingatorHtml = pagingatorHtml.replace("${rightArrow}", "<li class='next'><a href='#' onclick=\"selectSection(" + (currentPage + 1) + ", '"+valueElementId+"','"+nameElementId+"')\" <i class='icon-double-angle-right'></i></a></li>");
            }
            $("#sectionPagingator").append(pagingatorHtml);

            var dialog = $("#section-dialog").removeClass('hide').dialog({
                modal: true,
                width: 600,
                height: 590,
                title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>板块列表</h4></div>",
                title_html: true,
                buttons: [
                    {
                        text: "取消",
                        "class": "btn btn-xs",
                        click: function () {
                            $("#visible-store-search- tbody").html("")
                            $("#sectionPagingator").html("");
                            $(this).dialog("close");
                        }
                    },
                    {
                        text: "确认",
                        "class": "btn btn-primary btn-xs",
                        click: function () {
                            var radiobuttonValue = $("input[type='radio']:checked").val();
                            var indexArray = radiobuttonValue.split("_");
                            var value = indexArray[0];
                            var name = indexArray[1];
                            var city = indexArray[2];
                            var address = indexArray[3];
//                            $('#order-search-section').val(value);
                            
                            $("#"+valueElementId).val(value);
                            $("#"+nameElementId).val(name);

                            $('#section-distributor-on-site-flag').val(value);
                            $('#section-distributor-section-name').val(name);
                            $('#section-distributor-city').val(city);
                            $('#section-distributor-address').val(address);
                            $("#section_table_list tbody").html("");
                            $("#searchSectionPagingator").html("");
                            $(this).dialog("close");
                        }
                    }
                ]
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("服务器异常，请联系管理员！", "错误");
        }
    });
}
function clearSelectSection() {
    clearValue('#onSiteFlagQuery');
}

function judgetPhone(phone) {
    var filter = /^([\d]{1,4}-?[\d]{1,10})$/;
    if (filter.test(phone))
        return true;
    else {
        return false;
    }
}
function selectSectionAndClearOnSiteFlagQuery(pageSize, valueId, nameId){
    $("#onSiteFlagQuery").val("");
    selectSection(pageSize, valueId, nameId);

}

//确认弹窗
$.confirmDialog = function (text, call) {
    var id = new Date().getTime();
    $("<div id='dialog-confirm" + id + "' class='hide'><p style='color:#669fc7;font-size: 15px;padding-top: 15px;'><i ></i>&nbsp;&nbsp;" + text + "</p></div>").appendTo('body');
    $("#dialog-confirm" + id).removeClass('hide').dialog({
        modal: true,
        dialogClass: "alert-dialog",
        title_html: true,
        buttons: [
            {
                text: "取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "确定",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    $(this).dialog("close");
                    if (call) {
                        call();
                    }
                }
            }
        ]
    });
}

function headBoxOnclick(obj) {
    var checked = $(obj).is(':checked');
    $('label input:checkbox').each(function () {
        $(this).prop("checked", checked);
    });
}