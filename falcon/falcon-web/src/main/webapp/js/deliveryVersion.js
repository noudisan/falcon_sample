function pageOnLoad() {
    var oTable = $('#delivery-version-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": getValue('#contextPath') + "/deliveryversion/search",
            "bProcessing": true,
            "aaSorting": [[1, 'asc']],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns": [
                {
                    "sWidth": "4%",
                    "bSortable": false,
                    "sTitle": '<input type="checkbox" class="ace" onclick="headBoxOnclick(this)"  /><span class="lbl"></span>',
                    "mData": "id"
                },
                {"sWidth": "10%", "bSortable": true, "sTitle": "版本号", "mData": "versionCode"},
                {"sWidth": "12%", "bSortable": true, "sTitle": "版本名称", "mData": "versionName"},
                {"sWidth": "9%", "bSortable": true, "sTitle": "系统类型", "mData": "systemType"},
                {"sWidth": "8%", "bSortable": true, "sTitle": "更新内容", "mData": "updateContent"},
                {"sWidth": "8%", "bSortable": true, "sTitle": "是否强制更新", "mData": "isForceUpdate"},
                {
                    "sWidth": "8%", "bSortable": true, "sTitle": "更新时间", "mData": "modifyDt",
                    "fnRender": function (obj) {
                        return new Date(obj.aData.modifyDt).Format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            ],
            "bFilter": false,
            "iDisplayLength": 10,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "versionCode", "value": getValue('#delivery-version-search-versionCode')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function () {
                    updateDeliveryVersion(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                    "<span class='lbl'></span></label>");
                var status = aData["isForceUpdate"];
                $('td:eq(5)', nRow).html(getIsForceUpdate(status));
            }
        });

}
/*全选*/
function headBoxOnclick(dom) {
    var checked = $(dom).is(':checked');
    $('label input:checkbox').each(function () {
        $(this).prop("checked", checked);
    });
}

/*检索*/
function deliveryVersionSearch() {
    pageOnLoad();
}
/*清空检索项*/
function clearDeliveryVersion(dom) {
    $(dom).parents("form").find("input").each(function (index, element) {
        $(this).val("");
    });
    $(dom).parents("form").find("select").each(function (index, element) {
        $(this).val("");
    });
}
/*编辑版本*/
$("#update_version_Btn").click(function () {
    var versionCodeArray = new Array();
    $("#delivery-version-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked) {
            versionCodeArray.push(element.value);
        }
    });
    if (versionCodeArray.length == 0) {
        alert("请选择要编辑的版本!");
        return;
    }
    if (versionCodeArray.length > 1) {
        alert("请选择一个编辑的版本!");
        return;
    }
    var versionCode = versionCodeArray[0];

    updateDeliveryVersion(versionCode);
});

function updateDeliveryVersion(versionCode) {
    cleanVersionForm();
    $.ajax({
        url: getValue('#contextPath') + "/deliveryversion/get?versionCode=" + versionCode,
        type: "get",
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data) {
                $("#delivery_version_dialog_id").val(data.id);
                $("#delivery_version_dialog_versionCode").val(data.versionCode);
                $("#delivery_version_dialog_versionName").val(data.versionName);
                $("#delivery_version_dialog_systemType").val(data.systemType);
                $("#delivery_version_dialog_updateContent").val(data.updateContent);
                $("#delivery_version_dialog_isForceUpdate").val(data.isForceUpdate);
            }
        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });
    showDeliveryVersionDialog();
}

/*判断是否新增版本*/
$(function () {
    isAddVersion();
});

function isAddVersion() {
    $.ajax({
        url: getValue('#contextPath') + "/deliveryversion/countSearch",
        type: "get",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            if (data) {
                if (data != 'SUCCESS') {
                    // 不能添加版本！新增按钮变灰
                    $("#add_version_Btn").attr("disabled", "true");
                    return;
                }
            }
        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });
}

/*新增版本*/
$("#add_version_Btn").click(function () {
    cleanVersionForm();
    showDeliveryVersionDialog();
});

/*显示版本窗口*/
function showDeliveryVersionDialog() {
    var dialog = $("#delivery_version_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height: 550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>版本管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#delivery_version_form');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-order-dialog",
                click: function () {
                    if (!validateVersionForm()) {
                        return;
                    }

                    var currentDialog = $(this),
                        addStoreFormOptions = {
                            beforeSubmit: function () {
                                $("button[class*='save-order-dialog']").attr("disabled", true);
                            },
                            success: function (data) {
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                currentDialog.dialog("close");
                                if (data != 'SUCCESS') {
                                    alert("操作失败！");
                                    return;
                                } else {
                                    alert("操作成功！");
                                    deliveryVersionSearch();
                                    isAddVersion();
                                }

                            },
                            error: function (ret) {
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert("服务器出现异常，请联系管理员！")
                            }
                        };
                    $('#delivery_version_form').ajaxSubmit(addStoreFormOptions);
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
/*清空版本窗口*/
function cleanVersionForm() {
    $("#delivery_version_form").find("input").each(function (index, element) {
        $(this).val("");
    });
    $("#delivery_version_form").find("select").each(function (index, element) {
        $(this).val("");
    });
    $("#delivery_version_dialog_updateContent").val("");
}
/*版本非空校验*/
function validateVersionForm() {
    var flag = true;
    if (!myvalidate('#delivery_version_dialog_versionCode')) {
        flag = false;
    }
    if (!myvalidate('#delivery_version_dialog_versionName')) {
        flag = false;
    }
    if (!myvalidate('#delivery_version_dialog_systemType')) {
        flag = false;
    }
    if (!myvalidate('#delivery_version_dialog_updateContent')) {
        flag = false;
    }
    if (!myvalidate('#delivery_version_dialog_isForceUpdate')) {
        flag = false;
    }
    return flag;
}

/*是否强制更新*/
function getIsForceUpdate(status) {
    switch (status) {
        case "1":
            return "是";
            break;
        case "0":
            return "否";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";
    }
}