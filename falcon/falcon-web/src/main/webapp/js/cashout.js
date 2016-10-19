/**
 * Created by Adminstrator on 2015/6/26.
 */

var cashOutInfoTable = cashOutInfoTable || {}
$(function () {
    cashOutInfoTable = $('#cashOut-table').dataTable({
        "bDestroy": true,
        "bServerSide": true,
        "sServerMethod": "POST",
        "sAjaxSource": getValue('#contextPath') + "/cash/search",
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
            {"sWidth": "10%", "bSortable": true, "iDataSort": "ID", "sTitle": "编号", "mData": "id"},
            {"sWidth": "12%", "bSortable": true, "iDataSort": "NAME", "sTitle": "姓名", "mData": "name"},
            {"sWidth": "9%", "bSortable": true, "iDataSort": "CASH_AMOUNT", "sTitle": "提现金额", "mData": "cashAmount"},
            {"sWidth": "8%", "bSortable": true, "iDataSort": "OPEN_BANK", "sTitle": "开户行", "mData": "openBank"},
            {"sWidth": "8%", "bSortable": true, "iDataSort": "CASH_CARD", "sTitle": "提现卡号", "mData": "cashCard"},
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "DEAL_STATUS",
                "sTitle": "处理状态",
                "mData": "dealStatus",
                "fnRender": function (obj) {
                    var status = obj.aData.dealStatus;
                    if (status == 0) {
                        return "申请中";
                    } else if (status == 1) {
                        return "成功";
                    } else if (status == 2) {
                        return "失败";
                    } else {
                        return "未知";
                    }
                }
            },
            {"sWidth": "8%", "bSortable": true, "sTitle": "结果说明", "mData": "resultComent"},
            {
                "sWidth": "8%", "bSortable": true, "sTitle": "提现时间", "mData": "cashDate", "fnRender": function (obj) {
                return new Date(obj.aData.cashDate).Format("yyyy-MM-dd hh:mm:ss");
            }
            }
            //{"sWidth": "8%", "bSortable": false, "mData": ""}
        ],
        "bFilter": false,
        "iDisplayLength": 20,
        "aLengthMenu": [10, 20, 50],
        "fnServerParams": function (aoData) {
            aoData.push({"name": "startDate", "value": getValue('#cash-search-startDate')});
            aoData.push({"name": "endDate", "value": getValue('#cash-search-endDate')});
            aoData.push({"name": "name", "value": getValue('#cash-search-name')});
            aoData.push({"name": "dealStatus", "value": getValue('#cash-search-dealStatus')});
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


function cashSearch() {
    cashOutInfoTable.fnDraw();
}

function cashClear() {
    $("#cash-search-startDate").val("");
    $("#cash-search-endDate").val("");
    $("#cash-search-name").val("");
}

function getStatus(status) {
    if (status == 0) {
        return "申请中";
    } else if (status == 1) {
        return "成功";
    } else if (status == 2) {
        return "失败";
    } else {
        return "未知";
    }
}
$("#cash_success_Btn").click(function () {
    var cashIdArray = new Array();
    $("#cashOut-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked && element.value != 'on') {
            cashIdArray.push(element.value);
        }
    });
    if (cashIdArray.length == 0) {
        alert("请选择更新的数据!");
        return;
    }
    updateCash(cashIdArray, 1, "");
});

//点击打款失败按钮触发事件
$("#cash_fail_Btn").click(function () {
    var cashIdArray = new Array();
    $("#cashOut-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked && element.value != 'on') {
            cashIdArray.push(element.value);
        }
    });
    if (cashIdArray.length == 0) {
        alert("请选择更新的数据!");
        return;
    }

    showDialog();

});

//显示弹窗
function showDialog() {
    var table = "<table>" +
        "<tr><td>失败原因</td></tr>" +
        "<tr><td><textarea id='fail_reason' name='' rows='15' cols='20'></textarea></td></tr>" +
        "</table>";
    $("#cashOut_dialog").html(table);

    //绘制弹窗页面
    var dialog = $("#cashOut_dialog").removeClass('hide').dialog({
        modal: true,
        width: 309,
        height: 405,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>结算明细</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#base_city_form');
        },
        buttons: [
            {
                text: "确认",
                "class": "btn btxn-s",
                click: function () {
                    var fail_reason =  $("#fail_reason").val();
                    if( fail_reason == null || fail_reason == ""){
                        alert("失败原因不能为空！");
                        return false;
                    }
                    payFailed();
                    $(this).dialog("close");
                }
            }
        ]
    });
}

//执行支付失败
function payFailed() {
    var cashIdArray = new Array();
    $("#cashOut-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked && element.value != 'on') {
            cashIdArray.push(element.value);
        }
    });
    if (cashIdArray.length == 0) {
        alert("请选择更新的数据!");
        return;
    }
    var content = encodeURI($("#fail_reason").val());
    updateCash(cashIdArray, 2, content)
}


function updateCash(cashIdArray, status, content) {
    $.ajax({
        url: getValue('#contextPath') + "/cash/update?id=" + cashIdArray + "&dealStatus=" + status + "&content=" + content,
        type: "post",
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            alert(data.msg);

        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });
    cashOutInfoTable.fnDraw();
}


