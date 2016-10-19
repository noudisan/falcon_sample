/**
 * Created by Adminstrator on 2015/6/30.
 */

var settlePriceTable = settlePriceTable || {}
$(function () {
    settlePriceTable = $('#data-table').dataTable({
        "bDestroy": true,
        "bServerSide": true,
        "sServerMethod": "POST",
        "sAjaxSource": getValue('#contextPath') + "/settlePrice/search",
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
            {
                "sWidth": "12%",
                "bSortable": true,
                "iDataSort": "CITY",
                "sTitle": "地区",
                "mData": "province",
                "fnRender": function (obj) {
                    var province = obj.aData.province;
                    var city = obj.aData.city;
                    return city;
                }
            },
            {
                "sWidth": "9%",
                "bSortable": true,
                "iDataSort": "SEND_STYLE",
                "sTitle": "结算方式",
                "mData": "sendStyle",
                "fnRender": function (obj) {
                    var sendStyle = obj.aData.sendStyle;
                    if (sendStyle == "INLETTER") {
                        return "内信箱";
                    } else if (sendStyle == "OUTLETTER") {
                        return "外信箱";
                    } else if (sendStyle == "STAIRS") {
                        return "步梯入户";
                    } else if (sendStyle == "LIFT") {
                        return "电梯入户";
                    } else if (sendStyle == "FOOD") {
                        return "饭补";
                    } else if (sendStyle == "BAD_WEATHER") {
                        return "恶劣天气补贴";
                    } else {
                        return "未知";
                    }

                }
            },
            {"sWidth": "8%", "bSortable": true, "iDataSort": "PRICE", "sTitle": "单价", "mData": "price"},
            {
                "sWidth": "8%",
                "bSortable": true,
                "iDataSort": "MODIFY_DT",
                "sTitle": "更新时间",
                "mData": "modifyDt",
                "fnRender": function (obj) {
                    return new Date(obj.aData.modifyDt).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {"sWidth": "8%", "bSortable": true, "iDataSort": "MODIFY_USER", "sTitle": "更新人", "mData": "modifyUser"}
        ],
        "bFilter": false,
        "iDisplayLength": 20,
        "aLengthMenu": [10, 20, 50],
        "fnServerParams": function (aoData) {
            //aoData.push({"name": "province", "value": getValue('#search_province')});
            aoData.push({"name": "city", "value": getValue('#search_city')});
            aoData.push({"name": "sendStyle", "value": getValue('#search_sendStyle')});
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
    settlePriceTable.fnDraw();
}

/**
 * 清空所有
 */
function cleanAll() {
    //$("#search_province").val("");
    $("#search_city").val("");
}

/**
 * 清空表单中的信息
 */
function cleanForm() {
    $("#saveOrUpdate_id").val("");
    //$("#saveOrUpdate_province").val("");
    $("#saveOrUpdate_city").val("");
    $("#saveOrUpdate_sendStyle").val("");
    $("#saveOrUpdate_price").val("");
}

/**
 * 编辑所选数据
 */
$("#update_settlePrice_Btn").click(function () {
    cleanForm();
    var settlePriceIdArray = new Array();
    $("#data-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked && element.value != 'on') {
            settlePriceIdArray.push(element.value);
        }
    });
    if (settlePriceIdArray.length == 0) {
        alert("请选择要编辑的城市!");
        return;
    }
    if (settlePriceIdArray.length > 1) {
        alert("请选择一个编辑的城市!");
        return;
    }
    var settlePriceId = settlePriceIdArray[0];

    sendRequest(settlePriceId);
});

/**
 * 创建新的结算单价
 */
$("#add_settlePrice_Btn").click(function () {
    cleanForm();
    showDialog();
});

/**
 * 发送请求数据
 */
function sendRequest(settlePriceId) {
    $.ajax({
        url: getValue('#contextPath') + "/settlePrice/detail?id=" + settlePriceId,
        type: "get",
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data) {
                $("#saveOrUpdate_id").val(data.id);
                //$("#saveOrUpdate_province").val(data.province);
                $("#city").val(data.city);
                $("#saveOrUpdate_sendStyle").val(data.sendStyle);
                $("#saveOrUpdate_price").val(data.price);
            }
        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showDialog();
}

/**
 * 数据校验
 */
function validateData() {
    //var province = $("#saveOrUpdate_province").val();
    var city = $("#saveOrUpdate_city").val();
    var sendStyle = $("#saveOrUpdate_sendStyle").val();
    var price = $("#saveOrUpdate_price").val();
    //if( province == ""){
    //    alert("请填写省份信息");
    //    return false;
    //}
    if (city == "") {
        alert("请填写城市信息");
        return false;
    }
    if (sendStyle == "") {
        alert("请选择投递方式");
        return false;
    }
    if (price == "") {
        alert("请填写价格信息");
        return false;
    }
    var reg = /^[0-9]+(.[0-9]{1,2})?$/;
    if (!reg.test(price)) {
        alert("请填写正确的单价");
        return false;
    }
    return true;
}

/**
 * 显示模式窗口
 */
function showDialog() {

    var dialog = $("#base_city_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height: 550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>结算单价管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#settle_price_form');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-settle-dialog",
                click: function () {

                    if (!validateData()) {
                        return;
                    }
                    //发送请求
                    $.ajax({
                        url: getValue('#contextPath') + "/settlePrice/saveOrUpdate",
                        data: $("#settle_price_form").serialize(),
                        type: "POST",
                        async: false,
                        dataType: 'json',
                        success: function (data, textStatus, jqXHR) {
                            if (data.CODE == "-1") {
                                alert(data.MSG);
                                return;
                            }
                            $("#base_city_dialog").dialog("close");
                            search();

                        },
                        error: function (data, textStatus, jqXHR) {
                            alert("服务器出现异常，请联系管理员！")
                        }
                    });

                }
            },
            {
                text: "取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}