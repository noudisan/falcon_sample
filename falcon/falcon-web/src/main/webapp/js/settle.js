/**
 * Created by Adminstrator on 2015/6/29.
 */
var settleTable = settleTable || {}
$(function () {
    settleTable = $('#data-table').dataTable({
        "bDestroy": true,
        "bServerSide": true,
        "sServerMethod": "POST",
        "sAjaxSource": getValue('#contextPath') + "/settle/search",
        "bProcessing": true,
        "aaSorting": [
            [1, 'asc']
        ],
        "oLanguage": {
            "sProcessing": "正在获取数据，请稍候..."
        },
        "aoColumns": [
            {
                "sWidth": "4%",
                "bSortable": false,
                "iDataSort": "ID",
                "sTitle": '<input type="checkbox" class="ace" onclick="headBoxOnclick(this)"  /><span class="lbl"></span>',
                "mData": 'id'

            },
            {"sWidth": "7%", "bSortable": true, "iDataSort": "ID", "sTitle": "编号", "mData": "id"},
            {"sWidth": "7%", "bSortable": true, "iDataSort": "NAME", "sTitle": "姓名", "mData": "name"},
            {"sWidth": "9%", "bSortable": true, "iDataSort": "SEND_NUM", "sTitle": "派送数量", "mData": "sendNum"},
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "SETTLE_AMOUNT",
                "sTitle": "结算金额",
                "mData": "settleAmount"
            },
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "SETTLE_DATE",
                "sTitle": "结算日期",
                "mData": "settleDate",
                "fnRender": function (obj) {
                    return new Date(obj.aData.settleDate).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "DELIVERY_CITY",
                "sTitle": "城市",
                "mData": "deliveryCity"
            },
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "START_TIME",
                "sTitle": "派发开始时间",
                "mData": "startTime",
                "fnRender": function (obj) {
                    var startTime = obj.aData.startTime;
                    console.log("开始时间" + startTime);
                    if (startTime == null || startTime == '') {
                        return "";
                    }
                    return new Date(startTime).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "END_TIME",
                "sTitle": "结束时间",
                "mData": "endTime",
                "fnRender": function (obj) {
                    var endTime = obj.aData.endTime;
                    console.log("结束时间" + endTime);
                    if (endTime == null || endTime == '') {
                        return "";
                    }
                    return new Date(endTime).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {"sWidth": "8%", "bSortable": true, "iDataSort": "TOTAL_TIME", "sTitle": "用时", "mData": "totalTime"},
            {"sWidth": "8%", "bSortable": true, "iDataSort": "COMMUNITY_NUM", "sTitle": "小区数", "mData": "communityNum"},
            {"sWidth": "8%", "bSortable": true, "iDataSort": "BUILDING_NUM", "sTitle": "楼栋数", "mData": "buildingNum"},
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "COMMUNITY_UNIT_NUM",
                "sTitle": "单元数",
                "mData": "communityUnitNum"
            }


        ],
        "bFilter": false,
        "iDisplayLength": 20,
        "aLengthMenu": [10, 20, 50],
        "fnServerParams": function (aoData) {
            aoData.push({"name": "startDate", "value": getValue('#search-startDate')});
            aoData.push({"name": "endDate", "value": getValue('#search-endDate')});
            aoData.push({"name": "deliveryCity", "value": getValue('#search-deliveryCity')});
            aoData.push({"name": "name", "value": getValue('#settle-search-name')});
        },
        "fnInitComplete": function (oSettings, json) {

        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            var id = aData["id"];
            //$(nRow).dblclick(function () {
            //    updateBaseCity(id);
            //});
            $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");

        }
    });

});

/**
 * 搜索
 */
function search() {
    settleTable.fnDraw();
}

/**
 * 清空所有
 */
function cleanAll() {
    $("#search-startDate").val("");
    $("#search-deliveryCity").val("");
    $("#settle-search-name").val("");
}

/**
 * 获取派送的方式
 * @param sendStyle
 * @returns {*}
 */
function getSendStyle(sendStyle) {
    if (sendStyle == "INLETTER") {
        return "内信箱";
    } else if (sendStyle == "OUTLETTER") {
        return "外信箱";
    } else if (sendStyle == "STAIRS") {
        return "步梯入户";
    } else if (sendStyle == "LIFT") {
        return "电梯入户";
    } else if( sendStyle == "FOOD"){
        return "饭补";
    }else if( sendStyle == "BAD_WEATHER"){
        return "恶劣天气补贴";
    }else {
        return "未知";
    }
}




/**
 * 查看详情
 */
$("#settle_detail_Btn").click(function () {

    //获取数据
    var settleIdArray = new Array();
    $("#data-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked && element.value != 'on') {
            settleIdArray.push(element.value);
        }
    });
    if (settleIdArray.length == 0) {
        alert("请选择查看的数据!");
        return;
    }
    if (settleIdArray.length > 1) {
        alert("只能选择一条数据");
        return;
    }

    //发送请求
    var settleId = settleIdArray[0];
    $.ajax({
        url: getValue('#contextPath') + "/settle/detail?settleId=" + settleId,
        type: "get",
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {

            var head = "<table id='settleDetail_table' class='table table-striped table-bordered table-hover dataTable'  width='100%' aria-describedby='settleDetail_tables_info'  style='width: 100%; font-size: 12px;'>" +
                "<tr>" +
                "<td>编号</td>" +
                "<td>小区名称</td>" +
                    //"<td>楼栋数</td>" +
                    //"<td>单元数</td>" +
                "<td>投递方式</td>" +
                "<td>投递册数</td>" +
                "<td>单价</td>" +
                "<td>金额</td>" +
                "</tr>";
            var content = "";

            $.each(data, function (index, item) {
                content += "<tr>" +
                    "<td>" + item.id + "</td>" +
                    "<td>" + item.communityName + "</td>" +
                        //"<td>"+item.floorNum+"</td>" +
                        //"<td>"+item.unitNum+"</td>" +
                    "<td>" + getSendStyle(item.sendStyle) + "</td>" +
                    "<td>" + item.bookNum + "</td>" +
                    "<td>" + item.price + "</td>" +
                    "<td>" + item.settleAmount + "</td>" +
                    "</tr>";
            });
            content += "</table>"
            $("#settleDetail_dialog").html(head + content);

        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });

//绘制弹窗页面
    var dialog = $("#settleDetail_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height: 550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>结算明细</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#base_city_form');
        },
        buttons: [
            {
                text: "关闭",
                "class": "btn btxn-s",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
});





//添加补贴信息
$("#settle_addAllowance_Btn").click(function(){
    //获取数据
    var settleIdArray = new Array();
    $("#data-table").find("input[type='checkbox']").each(function(index,element){
        if (element.checked && element.value != 'on') {
            settleIdArray.push(element.value);
        }
    });

    if(settleIdArray.length ==0){
        alert("请选择补贴数据!");
        return;
    }

    $.ajax({
        url:  getValue('#contextPath')+"/settle/addAllowance?settleIds="+settleIdArray,
        type: "POST",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            alert(data.msg);
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

});