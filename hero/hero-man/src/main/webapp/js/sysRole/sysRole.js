var sysRoleTable = {};
var sysRoleResourcesTable = {};
var currentCheckedRoleId = "";
var currentRoleResources = {};
var dataTableSelectItem = {};
$(function () {
    sysRoleTable = $('#sysRoleList-table').dataTable({
        "bDestroy": true,
        "bServerSide": true,
        "sServerMethod": "POST",
        "sAjaxSource": contextPath + "/sysRole/search",
        "bProcessing": true,
        "bSort": true,
        "aaSorting": [
            [1, 'asc']
        ],
        "oLanguage": {
            "sProcessing": "正在获取数据，请稍候..."
        },
        "aoColumns": [
            {"sWidth": "3%", "bSortable": false, "sTitle": '<input type="checkbox" class="ace" onclick="$.dataTableSelectAll(this)"/><span class="lbl"></span>', "mData": "id"},
            {"sWidth": "5%", "bSortable": true, "iDataSort": "ROLE_ID", "sTitle": "角色ID", "mData": "id"},
            {"sWidth": "10%", "bSortable": true, "iDataSort": "ROLE_NAME", "sTitle": "角色名", "mData": "roleName"},
            {"sWidth": "15%", "bSortable": false, "iDataSort": "ROLE_DESC", "sTitle": "角色描述", "mData": "roleDesc"},
            {"sWidth": "10%", "bSortable": true, "iDataSort": "CREATE_USER", "sTitle": "创建人", "mData": "createUser"},
            {"sWidth": "15%", "bSortable": true, "iDataSort": "CREATE_DT", "sTitle": "创建时间", "mData": "createDtString"},
            {"sWidth": "10%", "bSortable": true, "iDataSort": "MODIFY_USER", "sTitle": "最后修改人", "mData": "modifyUser"},
            {"sWidth": "15%", "bSortable": true, "iDataSort": "MODIFY_TIME", "sTitle": "最后修时间", "mData": "modifyDtString"}
        ],
        "bFilter": false,
        "iDisplayLength": 15,
        "aLengthMenu": [15, 30, 45],
        "fnServerParams": function (aoData) {
            aoData.push({"name": "roleName", "value": elementVal('sysRole-search-roleName')});
            aoData.push({"name": "startDate", "value": elementVal('sysRole-search-startDate')});
            aoData.push({"name": "endDate", "value": elementVal('sysRole-search-endDate')});
        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            var id = aData["id"];
            $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");
        }
    });

    $("#sysRole-search-query-button").click(function () {
        sysRoleTable.fnDraw();
    });

    $("#sysRole-search-clear-button").click(function () {
        $("#sysRole-search-form")[0].reset();
    });

    $("#sysRole-add-btn").click(function () {
    	var that = $(this);
        var elementsId = new Array('sysRole-add-roleName', 'sysRole-add-roleDesc');
        $.validatePlugin.bind(elementsId, true);
        $.validatePlugin.clear(elementsId);
        $("#sysRole-add-form")[0].reset();
        var dialog = $("#sysRole-add-dialog").removeClass('hide').dialog({
            modal: true,
            width: 600,
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
                                "roleName": $("#sysRole-add-roleName").val(),
                                "roleDesc": $("#sysRole-add-roleDesc").val()
                            },
                            success: function (data, textStatus, jqXHR) {
                                if (data.status == 'SUCCESS') {
                                    dialog.dialog("close");
                                    sysRoleTable.fnDraw();
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


    $("#sysRole-edit-btn").click(function () {
    	var that = $(this);
        var items = $.dataTableCheckedOneItem("sysRoleList-table", "请至少选择一个角色", "只能编辑一个角色");
        if (items) {
            $.ajax({
                url: contextPath + "/sysRole/get?id=" + items[0].value,
                success: function (data, textStatus, jqXHR) {
                    var elementsId = new Array('sysRole-update-id', 'sysRole-update-roleName', 'sysRole-update-roleDesc');
                    var values = {
                        "sysRole-update-id": data.id,
                        "sysRole-update-roleName": data.roleName,
                        "sysRole-update-roleDesc": data.roleDesc
                    };
                    $.validatePlugin.rest(values);
                    $.validatePlugin.bind(elementsId, true);
                    var dialog = $("#sysRole-update-dialog").removeClass('hide').dialog({
                        modal: true,
                        width: 600,
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
                                                "id": $("#sysRole-update-id").val(),
                                                "roleName": $("#sysRole-update-roleName").val(),
                                                "roleDesc": $("#sysRole-update-roleDesc").val()
                                            },
                                            success: function (data, textStatus, jqXHR) {
                                                if (data.status == 'SUCCESS') {
                                                    dialog.dialog("close");
                                                    $('#sysRoleList-ftable_wrapper .active').trigger("click");
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
    })


    dataTableSelectItem = function (o) {
        var parentValue = $(o).attr("parentValue");
        var value = $(o).attr("value");
        var checked = $(o).is(':checked');
        var allCheckBox = $.dataTableAllCheckBox("sysRoleResourcesList-table");
        allCheckBox.each(function () {
            if ($(this).val() == parentValue && checked) {
                $(this).prop("checked", checked);
                var ppValue = $(this).attr("parentValue");
                allCheckBox.each(function () {
                    if ($(this).val() == ppValue && checked) {
                        $(this).prop("checked", checked);
                    }
                })
            }
            if ($(this).attr("parentValue") == value && (checked == false)) {
                $(this).prop("checked", checked);
                var cValue = $(this).attr("value");
                allCheckBox.each(function () {
                    if ($(this).attr("parentValue") == cValue && (checked == false)) {
                        $(this).prop("checked", checked);
                    }
                })
            }
        })
    }


    var roleResources = function () {
        //查询当前角色的权限
        $.ajax({
            url: contextPath + "/sysRole/searchResources",
            type: "get",
            data: {
                "roleId": currentCheckedRoleId,
                "platformId": $('#sysRoleResources-search-platformId').val()
            },
            success: function (data, textStatus, jqXHR) {
                currentRoleResources = data;
                var dialog = $("#sysRole-toAssign-dialog").removeClass('hide').dialog({
                    modal: true,
                    width: 1200,
                    height:800,
                    title_html: true
                });
                sysRoleResourcesTable = $('#sysRoleResourcesList-table').dataTable(
                    {
                        "bDestroy": true,
                        "bServerSide": true,
                        "sServerMethod": "POST",
                        "sAjaxSource": contextPath + "/resources/simpleSearch",
                        "bProcessing": false,
                        "bSort": false,
                        "bPaginate": false,
                        "oLanguage": {
                            "sProcessing": "正在获取数据，请稍候..."
                        },
                        "aoColumns": [
                            {"sWidth": "3%", "bSortable": false, "sTitle": '<input type="checkbox" class="ace" onclick="$.dataTableSelectAll(this)"/><span class="lbl"></span>', "mData": "id"},
                            {"sWidth": "20%", "bSortable": false, "iDataSort": "NAME", "sTitle": "名称", "mData": "namesNav"},
                            {"sWidth": "15%", "bSortable": false, "iDataSort": "AUTHORIZE", "sTitle": "授权标识", "mData": "authorize"},
                            {"sWidth": "15%", "bSortable": false, "iDataSort": "LINK_URL", "sTitle": "URL", "mData": "linkUrl"},
                            {"sWidth": "5%", "bSortable": false, "iDataSort": "ORDERS", "sTitle": "次序", "mData": "orders"},
                            {"sWidth": "10%", "bSortable": false, "iDataSort": "PLATFORM_NAME", "sTitle": "平台", "mData": "platformName"},
                            {"sWidth": "20%", "bSortable": false, "iDataSort": "DESCRIBER", "sTitle": "描述", "mData": "describer"}
                        ],
                        "bFilter": false,
                        "fnServerParams": function (aoData) {
                            aoData.push({"name": "platformId", "value": $('#sysRoleResources-search-platformId').val()});
                        },
                        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                            var checked = "";
                            var id = aData["id"];
                            var parentId = aData["parentId"];
                            $(currentRoleResources).each(function (i, value) {
                                var resourcesId = value.resources.id;
                                if (resourcesId == id) {
                                    checked = "checked";
                                }
                            })
                            $('td:eq(0)', nRow).html("<label><input onclick='dataTableSelectItem(this)' type='checkbox' class='ace'  " + checked + " value='" + id + "' parentValue='" + parentId + "'/>" +
                                "<span class='lbl'></span></label>");
                        }
                    })
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("服务器异常，请联系管理员！", "错误");
            }
        })
    }


    $("#sysRole-toAssign-btn").click(function () {
        var items = $.dataTableCheckedOneItem("sysRoleList-table", "请至少选择一个角色分配资源", "一次只能给一个角色分配资源");
        currentCheckedRoleId = items[0].value;
        $("#sysRoleResources-search-form")[0].reset();
        if (items) {
            roleResources();
        }

    })

    $("#sysRoleResources-search-query-button").click(function () {
        roleResources();
    });


    $("#sysRoleResources-search-clear-button").click(function () {
        $("#sysRoleResources-search-form")[0].reset();
    });

    $("#sysUserRole-toAssign-btn").click(function () {
        $.confirmDialog("确定分配", function () {
            var checkedItems = $.dataTableSelectedItem("sysRoleResourcesList-table");
            var resourcesIds = "";
            for (var i = 0; i < checkedItems.length; i++) {
                resourcesIds = resourcesIds + "," + checkedItems[i].value;
            }
            $.ajax({
                url: contextPath + "/sysRole/assignResources",
                type: "post",
                data: {
                    "resourcesIds": resourcesIds.substring(1, resourcesIds.length),
                    "platformId": $('#sysRoleResources-search-platformId').val(),
                    "roleId": currentCheckedRoleId
                },
                success: function (data, textStatus, jqXHR) {
                    if (data.status == 'SUCCESS') {
                        //sysRoleResourcesTable.fnDraw();
                        $.alertDialog("当前平台分配成功");
                        //roleResources();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("服务器异常，请联系管理员！", "错误");
                }
            })
        });
    })

});


