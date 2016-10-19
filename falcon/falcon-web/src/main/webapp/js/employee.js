function pageOnLoad() {
    var oTable = $('#baseEmployee-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": getValue('#contextPath') + "/employee/search",
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
                {"sWidth": "12%", "bSortable": true, "sTitle": "员工姓名", "mData": "userName"},
                {"sWidth": "9%", "bSortable": true, "sTitle": "手机号", "mData": "phone"},
                {"sWidth": "8%", "bSortable": true, "sTitle": "角色", "mData": "role"},
                {"sWidth": "8%", "bSortable": true, "sTitle": "城市", "mData": "city"},
                {"sWidth": "8%", "bSortable": true, "sTitle": "状态", "mData": "isLocked"},
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "userName", "value": getValue('#employee-search-userName')});
                aoData.push({"name": "phone", "value": getValue('#employee-search-phone')});
                aoData.push({"name": "isLocked", "value": getValue('#employee-search-isLocked')});
                aoData.push({"name": "city", "value": getValue('#employee-search-city')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function () {
                    updateBaseEmployee(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                    "<span class='lbl'></span></label>");

                var role = aData["role"];
                $('td:eq(3)', nRow).html(getBaseEmployeeRole(role));

                var status = aData["isLocked"];
                $('td:eq(5)', nRow).html(getBaseEmployeeStatus(status));


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
function baseEmployeeSearch() {
    pageOnLoad();
}
/*清空检索项*/
function clearEmployee(dom) {
    $(dom).parents("form").find("input").each(function (index, element) {
        $(this).val("");
    });
    $(dom).parents("form").find("select").each(function (index, element) {
        $(this).val("");
    });
    baseEmployeeSearch();
}


/*添加员工*/
$("#add_employee_Btn").click(function (){
    cleanEmployeeForm();
    var dialog = $("#add_employee_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height: 550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>员工管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#add_base_employee_form');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-order-dialog",
                click: function () {
                    if (!validateAdd()) {
                        return;
                    }

                    var currentDialog = $(this),
                        addStoreFormOptions = {
                            beforeSubmit: function () {
                                $("button[class*='save-order-dialog']").attr("disabled", true);

                            },
                            success: function (data) {
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                if (data != 'SUCCESS') {
                                    alert("添加员工失败！");
                                    return;
                                } else {
                                    currentDialog.dialog("close");
                                    baseEmployeeSearch();
                                    alert("添加员工成功！");
                                }
                            },
                            error: function (ret) {
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert("服务器出现异常，请联系管理员！")
                            }
                        };
                    $('#add_base_employee_form').ajaxSubmit(addStoreFormOptions);
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
});
/*更新员工*/
$("#update_employee_Btn").click(function () {
    cleanEmployeeForm();
    var employeeIdArray = new Array();
    $("#baseEmployee-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked) {
            employeeIdArray.push(element.value);
        }
    });
    if (employeeIdArray.length == 0) {
        alert("请选择要编辑的员工!");
        return;
    }
    if (employeeIdArray.length > 1) {
        alert("请选择一个编辑的员工!");
        return;
    }
    var employeeId = employeeIdArray[0];

    updateBaseEmployee(employeeId);
});

function updateBaseEmployee(employeeId) {
    $.ajax({
        url: getValue('#contextPath') + "/employee/get?employeeId=" + employeeId,
        type: "get",
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data) {
                $("#update-base-employee_dialog_id").val(data.id);
                $("#update_employee_dialog_userName").val(data.userName);
                $("#update_employee_dialog_sex").val(data.sex);
                $("#update_employee_dialog_career").val(data.career);
                $("#update_employee_dialog_phone").val(data.phone);
                $("#update_employee_dialog_role").val(data.role);
                $("#update_employee_dialog_city").val(data.city);
                $("#update_employee_dialog_isLocked").val(data.isLocked);
            }
        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });

    var dialog = $("#update_employee_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height: 580,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>员工管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#update_base_employee_form');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-order-dialog",
                click: function () {
                    if (!validateUpdate()) {
                        return;
                    }

                    var currentDialog = $(this),
                        addStoreFormOptions = {
                            beforeSubmit: function () {
                                $("button[class*='save-order-dialog']").attr("disabled", true);
                            },
                            success: function (data) {
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                if (data != 'SUCCESS') {
                                    alert("更新失败。");
                                    return;
                                } else {
                                    currentDialog.dialog("close");
                                    baseEmployeeSearch();
                                    alert("更新成功。");
                                }
                            },
                            error: function (ret) {
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert("服务器出现异常，请联系管理员！")
                            }
                        };
                    //是否重置密码
                    var isRestPassword = $("#update_employee_dialog_password_reset").val();
                    if ("YES_PASSWORD_RESET" == isRestPassword) {
                        var myMessage = confirm("确认重置密码吗？");
                        if (myMessage == true) {
                            $('#update_base_employee_form').ajaxSubmit(addStoreFormOptions);//提交表单
                        }
                    } else {
                        $('#update_base_employee_form').ajaxSubmit(addStoreFormOptions);//提交表单
                    }
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
/*锁定*/
$("#batch_unlock_employee_Btn").click(function () {
    lockBaseEmployeeDialog("unlock");
});
$("#batch_lock_employee_Btn").click(function () {
    lockBaseEmployeeDialog("lock");
});
function lockBaseEmployeeDialog(event) {
    var employeeIdArray = new Array();
    $("#baseEmployee-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked) {
            employeeIdArray.push(element.value);
        }
    });
    if (employeeIdArray.length == 0) {
        alert("请选择要编辑的员工!");
        return;
    }

    if (employeeIdArray.length < 2) {
        alert("请选择2个以上的员工!");
        return;
    } else {
        if (event == "lock") {
            lockBaseEmployee(employeeIdArray, "LOCK");
            $("#lock_employee_lablel").html("确认锁定员工账号吗？");
        } else {
            lockBaseEmployee(employeeIdArray, "UNLOCK");
            $("#lock_employee_lablel").html("确认解锁员工账号吗？");
        }
    }
}

function lockBaseEmployee(employeeIdArray, status) {

    if (employeeIdArray[0] == 'on') {
        employeeIdArray.splice(0, 1);
    }

    var dialog = $('#lock_employee_dialog').removeClass('hide').dialog({
        modal: true,
        width: 240,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>锁定员工账户</h4></div>",
        title_html: true,
        buttons: [
            {
                text: "确定",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    $.ajax({
                        url: getValue('#contextPath') + "/employee/lockEmployee?employeeIdArray=" + employeeIdArray + "&status=" + status,
                        type: "POST",
                        success: function (data, textStatus, jqXHR) {
                            baseEmployeeSearch();
                            if (data == "SUCCESS") {
                                alert("操作成功！");
                            } else {
                                alert("操作失败！请重新操作")
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert("服务器异常，请联系管理员！");
                        }
                    });
                    $(this).dialog("close");
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
/*清空表单*/
function cleanEmployeeForm() {
    $(".form-horizontal").find("input").each(function (index, element) {
        $(this).val("");
        $(this).css("border", "");
    });
    $(".form-horizontal").find("select").each(function (index, element) {
        $(this).val("");
        $(this).css("border", "");
    });
}
/*更新表单非空校验*/
function validateUpdate() {
    var phone = $("#update_employee_dialog_phone").val();
    var flag = true;
    if (!myvalidate('#update_employee_dialog_userName')) {
        flag = false;
    }
    if (!myvalidate('#update_employee_dialog_sex')) {
        flag = false;
    }
    if (!myvalidate('#update_employee_dialog_career')) {
        flag = false;
    }
    if (phone.length != 11) {
        addBorder("#update_employee_dialog_phone");
        flag = false;
    }
    if (!myvalidate('#update_employee_dialog_password_reset')) {
        flag = false;
    }
    if (!myvalidate('#update_employee_dialog_role')) {
        flag = false;
    }
    if (!myvalidate('#update_employee_dialog_city')) {
        flag = false;
    }

    if (!myvalidate('#update_employee_dialog_isLocked')) {
        flag = false;
    }

    return flag;
}
/*添加表单非空校验*/
function validateAdd() {
    var phone = $("#add_employee_dialog_phone").val();
    var flag = true;
    if (!myvalidate('#add_employee_dialog_userName')) {
        flag = false;
    }
    if (!myvalidate('#add_employee_dialog_sex')) {
        flag = false;
    }
    if (!myvalidate('#add_employee_dialog_career')) {
        flag = false;
    }
    if (phone.length != 11) {
        addBorder("#add_employee_dialog_phone");
        flag = false;
    }
    if (!myvalidate('#add_employee_dialog_role')) {
        flag = false;
    }
    if (!myvalidate('#add_employee_dialog_city')) {
        flag = false;
    }

    return flag;
}
/*状态*/
function getBaseEmployeeStatus(status) {
    switch (status) {
        case "LOCK":
            return "锁定";
            break;
        case "UNLOCK":
            return "未锁定";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";
    }
}
/*角色*/
function getBaseEmployeeRole(role) {
    switch (role) {
        case "SENDEMPLOYEE":
            return "派送员";
            break;
        case "CHECKEMPLOYEE":
            return "抽检员";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";
    }
}

/*手机号校验*/
$(function () {
    $("#add_base_employee_form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            phone: {
                required: true,
                minlength: 11
            },

        },
        messages: {
            phone: {
                required: "请输入手机号",
                minlength: "请输入11位手机号"
            },

        },

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },

        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },

        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },

        submitHandler: function (form) {
            form.submit();
        }
    });

    $("#update_base_employee_form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            phone: {
                required: true,
                minlength: 11
            },

        },
        messages: {
            phone: {
                required: "请输入手机号",
                minlength: "请输入11位手机号"
            },

        },

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },

        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },

        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },

        submitHandler: function (form) {
            form.submit();
        }
    });

    $('.form-horizontal input').keypress(function (e) {
        if (e.which == 10) {
            if ($("#add_base_employee_form").validate().form()) {
                $("#add_base_employee_form").submit();
            }
            return false;
        }
    });
});

function phoneCheck(event) {
    var phone = event.target.value;
    if (phone.length == 11) {
        $.ajax({
            url: getValue('#contextPath') + "/employee/phoneCheck?phone=" + phone,
            type: "get",
            success: function (data, textStatus, jqXHR) {
                if (data == "FAIL") {
                    alert("该手机号已被注册！");
                    return;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("服务器异常，请联系管理员！");
            }
        });
    } else {
        return;
    }
}