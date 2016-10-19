var sysUserTable = {};
var sysUserRoleTable = {};
var sysRoleTable = {};
$(function () {
    sysUserTable = $('#sysUserList-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": contextPath + "/sysUser/search",
            "bProcessing": true,
            "bSort": true,
            "aaSorting": [
                [1, 'desc']
            ],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns": [
                {"sWidth": "3%", "bSortable": false, "sTitle": '<input type="checkbox" class="ace" onclick="$.dataTableSelectAll(this)"/><span class="lbl"></span>', "mData": "id"},
                {"sWidth": "5%", "bSortable": true, "iDataSort": "USER_ID", "sTitle": "用户ID", "mData": "id"},
                {"sWidth": "10%", "bSortable": true, "iDataSort": "USER_NAME", "sTitle": "用户名", "mData": "userName"},
                {"sWidth": "10%", "bSortable": true, "iDataSort": "STATUS_FLAG", "sTitle": "用户状态", "mData": "statusFlagString"},
                {"sWidth": "10%", "bSortable": true, "iDataSort": "CREATE_USER", "sTitle": "创建人", "mData": "createUser"},
                {"sWidth": "15%", "bSortable": true, "iDataSort": "CREATE_DT", "sTitle": "创建时间", "mData": "createDtString"},
                {"sWidth": "10%", "bSortable": true, "iDataSort": "MODIFY_USER", "sTitle": "最后修改人", "mData": "modifyUser"},
                {"sWidth": "15%", "bSortable": true, "iDataSort": "MODIFY_TIME", "sTitle": "最后修时间", "mData": "modifyDtString"}
            ],
            "bFilter": false,
            "iDisplayLength": 15,
            "aLengthMenu": [15, 30, 45],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "userName", "value": elementVal('sysUser-search-userName')});
                aoData.push({"name": "startDate", "value": elementVal('sysUser-search-startDate')});
                aoData.push({"name": "endDate", "value": elementVal('sysUser-search-endDate')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");
            }
        });


    $("#sysUser-search-query-button").click(function () {
        sysUserTable.fnDraw();
    });

    $("#sysUser-search-clear-button").click(function () {
        $("#sysUser-search-form")[0].reset();
    });


    $("#sysUser-add-btn").click(function () {
    	var that = $(this);
        var elementsId = new Array('sysUser-add-userName', 'sysUser-add-password', 'sysUser-add-statusFlag');
        $.validatePlugin.bind(elementsId, true);
        $.validatePlugin.clear(elementsId);
        $("#sysUser-add-form")[0].reset();
        var dialog = $("#sysUser-add-dialog").removeClass('hide').dialog({
            modal: true,
            width: 400,
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
                    text: "保存",
                    "class": "btn btn-primary btn-xs",
                    click: function () {
                        if (!$.validatePlugin.validate()) {
                            return;
                        }
                        $.ajax({
                        	url:that.attr("path"),
                            type: "post",
                            data: {
                                "userName": $("#sysUser-add-userName").val(),
                                "password": $("#sysUser-add-password").val(),
                                "statusFlag": $("#sysUser-add-statusFlag").val()
                            },
                            success: function (data, textStatus, jqXHR) {
                                if (data.status == 'SUCCESS') {
                                    dialog.dialog("close");
                                    sysUserTable.fnDraw();
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert("服务器异常，请联系管理员！", "错误");
                            }
                        })
                    }
                }
            ]
        });
    });


    $("#sysUser-edit-btn").click(function () {
    	var that = $(this);
        var items = $.dataTableCheckedOneItem("sysUserList-table", "请至少选择一个用户", "只能编辑一个用户");
        if (items) {
            $.ajax({
                url: contextPath + "/sysUser/get?id=" + items[0].value,
                success: function (data, textStatus, jqXHR) {
                    var elementsId = new Array('sysUser-update-id', 'sysUser-update-userName', 'sysUser-update-statusFlag');
                    var values = {
                        "sysUser-update-id": data.id,
                        "sysUser-update-userName": data.userName,
                        "sysUser-update-password": "",
                        "sysUser-update-statusFlag": data.statusFlag
                    };
                    $.validatePlugin.rest(values);
                    $.validatePlugin.bind(elementsId, true);
                    var dialog = $("#sysUser-update-dialog").removeClass('hide').dialog({
                        modal: true,
                        width: 400,
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
                                text: "修改",
                                "class": "btn btn-primary btn-xs",
                                click: function () {
                                    if (!$.validatePlugin.validate()) {
                                        return;
                                    }
                                    $.confirmDialog("确认修改？", function () {
                                        $.ajax({
                                        	url:that.attr("path"),
                                            type: "post",
                                            data: {
                                                "id": $("#sysUser-update-id").val(),
                                                "userName": $("#sysUser-update-userName").val(),
                                                "password": $("#sysUser-update-password").val(),
                                                "statusFlag": $("#sysUser-update-statusFlag").val()
                                            },
                                            success: function (data, textStatus, jqXHR) {
                                                if (data.status == 'SUCCESS') {
                                                    dialog.dialog("close");
                                                    $('#sysUserList-ftable_wrapper .active').trigger("click");
                                                }
                                            },
                                            error: function (jqXHR, textStatus, errorThrown) {
                                                alert("服务器异常，请联系管理员！", "错误");
                                            }
                                        })

                                    });
                                }
                            }
                        ]
                    });

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("服务器异常，请联系管理员！", "错误");
                }
            })
        }
    });

    $("#sysUser-lock-btn").click(function () {
    	var that = $(this);
        var items = $.dataTableCheckedOneItem("sysUserList-table", "请至少选择一个用户", "只能锁定一个用户");
        if (items) {
            $.confirmDialog("确认锁定？", function () {
                $.ajax({
                	url:that.attr("path"),
                    type: "post",
                    data: {
                        "id": items[0].value,
                        "statusFlag": 2
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (data.status == 'SUCCESS') {
                            $('#sysUserList-ftable_wrapper .active').trigger("click");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("服务器异常，请联系管理员！", "错误");
                    }
                })
            });
        }
    });


    $("#sysUser-unlock-btn").click(function () {
    	var that = $(this);
        var items = $.dataTableCheckedOneItem("sysUserList-table", "请至少选择一个用户", "只能解锁一个用户");
        if (items) {
            $.confirmDialog("确认解锁？", function () {
                $.ajax({
                	url:that.attr("path"),
                    type: "post",
                    data: {
                        "id": items[0].value,
                        "statusFlag": 1
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (data.status == 'SUCCESS') {
                            $('#sysUserList-ftable_wrapper .active').trigger("click");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("服务器异常，请联系管理员！", "错误");
                    }
                })
            });
        }
    });


    $("#sysUser-toAssign-btn").click(function () {
        var items = $.dataTableCheckedOneItem("sysUserList-table", "请至少选择一个用户", "只能分配一个用户");
        if (items) {
            $("#sysUserRole-search-userId").val(items[0].value);
            sysUserRoleTable.fnDraw();
            var dialog = $("#sysUser-toAssign-dialog").removeClass('hide').dialog({
                modal: true,
                width: 1000,
                title_html: true
            });
        }
    });
    sysUserRoleTable = $('#sysUserRoleList-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": contextPath + "/sysUser/searchRole",
            "bProcessing": true,
            "bSort": true,
            "aaSorting": [
                [1, 'desc']
            ],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns": [
                {"sWidth": "5%", "bSortable": false, "sTitle": '<input type="checkbox" class="ace" onclick="$.dataTableSelectAll(this)"/><span class="lbl"></span>', "mData": "key"},
                {"sWidth": "10%", "bSortable": false, "iDataSort": "UR.ROLE_ID", "sTitle": "角色ID", "mData": "role.id"},
                {"sWidth": "10%", "bSortable": false, "iDataSort": "R.ROLE_NAME", "sTitle": "角色名", "mData": "role.roleName"},
                {"sWidth": "35%", "bSortable": false, "iDataSort": "R.ROLE_DESC", "sTitle": "角色描述", "mData": "role.roleDesc"},
                {"sWidth": "20%", "bSortable": false, "iDataSort": "UR.MODIFY_USER", "sTitle": "分配用户", "mData": "assignUser"},
                {"sWidth": "20%", "bSortable": false, "iDataSort": "UR.MODIFY_DT", "sTitle": "分配时间", "mData": "assignDts"}
            ],
            "bFilter": false,
            "iDisplayLength": 5,
            "aLengthMenu": [5, 10, 15],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "userId", "value": $('#sysUserRole-search-userId').val()})
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                var id = aData["key"];
                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");
            }
        })


    $("#sysUserRole-toAssign-btn").click(function () {
        $("#sysRole-search-form")[0].reset();
        sysRoleTable.fnDraw();
        var dialog = $("#sysUser-assign-dialog").removeClass('hide').dialog({
            modal: true,
            width: 900,
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
                    text: "确定分配",
                    "class": "btn btn-primary btn-xs",
                    click: function () {
                        var checkedItems = $.dataTableCheckedItem("sysRoleList-table", "请选择权限分配");
                        if (checkedItems) {
                            var roleIds = "";
                            $.confirmDialog("确认分配", function () {
                                for (var i = 0; i < checkedItems.length; i++) {
                                    roleIds = roleIds + "," + checkedItems[i].value;
                                }
                                $.ajax({
                                    url: contextPath + "/sysUser/assignRole",
                                    type: "post",
                                    data: {
                                        "userId": $("#sysUserRole-search-userId").val(),
                                        "roleIds": roleIds.substring(1, roleIds.length)
                                    },
                                    success: function (data, textStatus, jqXHR) {
                                        if (data.status == 'SUCCESS') {
                                            sysUserRoleTable.fnDraw();
                                            $(dialog).dialog("close");
                                        }
                                    },
                                    error: function (jqXHR, textStatus, errorThrown) {
                                        alert("服务器异常，请联系管理员！", "错误");
                                    }
                                })
                            })
                        }

                    }
                }
            ]
        });
    });


    $("#sysUserRole-delete-btn").click(function () {
        var checkedItems = $.dataTableCheckedItem("sysUserRoleList-table", "请选择权限删除");
        if (checkedItems) {
            var roleIds = "";
            $.confirmDialog("确认删除", function () {
                for (var i = 0; i < checkedItems.length; i++) {
                    roleIds = roleIds + "," + checkedItems[i].value;
                }
                $.ajax({
                    url: contextPath + "/sysUser/deleteRole",
                    type: "post",
                    data: {
                        "userId": $("#sysUserRole-search-userId").val(),
                        "roleIds": roleIds.substring(1, roleIds.length)
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (data.status == 'SUCCESS') {
                            sysUserRoleTable.fnDraw();
                            $(dialog).dialog("close");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("服务器异常，请联系管理员！", "错误");
                    }
                })
            })
        }


    });

    $("#sysRole-search-query-button").click(function () {
        sysRoleTable.fnDraw();
    });

    $("#sysRole-search-clear-button").click(function () {
        $("#sysRole-search-form")[0].reset();
    });

    sysRoleTable = $('#sysRoleList-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": contextPath + "/sysRole/search",
            "bProcessing": true,
            "bSort": true,
            "aaSorting": [
                [1, 'desc']
            ],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns": [
                {"sWidth": "5%", "bSortable": false, "sTitle": '<input type="checkbox" class="ace" onclick="$.dataTableSelectAll(this)"/><span class="lbl"></span>', "mData": "id"},
                {"sWidth": "10%", "bSortable": true, "iDataSort": "ROLE_ID", "sTitle": "角色ID", "mData": "id"},
                {"sWidth": "20%", "bSortable": true, "iDataSort": "ROLE_NAME", "sTitle": "角色名", "mData": "roleName"},
                {"sWidth": "60%", "bSortable": false, "iDataSort": "ROLE_DESC", "sTitle": "角色描述", "mData": "roleDesc"}
            ],
            "bFilter": false,
            "iDisplayLength": 10,
            "aLengthMenu": [10, 20, 30],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "roleName", "value": elementVal('sysRole-search-roleName')});
                aoData.push({"name": "startDate", "value": elementVal('sysRole-search-startDate')});
                aoData.push({"name": "endDate", "value": elementVal('sysRole-search-endDate')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");
            }
        })

});

