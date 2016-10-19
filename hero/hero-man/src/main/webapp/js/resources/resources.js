var resourcesTable = {};
$(function () {
    resourcesTable = $('#resourcesList-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": contextPath + "/resources/search",
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
                {"sWidth": "5%", "bSortable": true, "iDataSort": "ID", "sTitle": "资源ID", "mData": "id"},
                {"sWidth": "6%", "bSortable": true, "iDataSort": "NAME", "sTitle": "资源名称", "mData": "name"},
                {"sWidth": "6%", "bSortable": false, "iDataSort": "AUTHORIZE", "sTitle": "授权标识", "mData": "authorize"},
                {"sWidth": "12%", "bSortable": false, "iDataSort": "NAME", "sTitle": "资源导航", "mData": "namesNav"},
                {"sWidth": "8%", "bSortable": false, "iDataSort": "LINK_URL", "sTitle": "资源URL", "mData": "linkUrl"},
                {"sWidth": "8%", "bSortable": true, "iDataSort": "PARENT_ID", "sTitle": "资源父类", "mData": "parentName"},
                {"sWidth": "4%", "bSortable": true, "iDataSort": "ORDERS", "sTitle": "次序", "mData": "orders"},
                {"sWidth": "6%", "bSortable": true, "iDataSort": "PLATFORM_NAME", "sTitle": "平台名称", "mData": "platformName"},
                {"sWidth": "10%", "bSortable": false, "iDataSort": "DESCRIBER", "sTitle": "资源描述", "mData": "describer"},
                {"sWidth": "5%", "bSortable": true, "iDataSort": "CREATE_USER", "sTitle": "创建人", "mData": "createUser"},
                {"sWidth": "8%", "bSortable": true, "iDataSort": "CREATE_DT", "sTitle": "创建时间", "mData": "createDtString"},
                {"sWidth": "5%", "bSortable": true, "iDataSort": "MODIFY_USER", "sTitle": "最后修改人", "mData": "modifyUser"},
                {"sWidth": "8%", "bSortable": true, "iDataSort": "MODIFY_DT", "sTitle": "最后修时间", "mData": "modifyDtString"}
            ],
            "bFilter": false,
            "iDisplayLength": 15,
            "aLengthMenu": [15, 30, 45],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "name", "value": elementVal('resources-search-name')});
                aoData.push({"name": "describer", "value": elementVal('resources-search-describer')});
                aoData.push({"name": "platformId", "value": elementVal('resources-search-platformId')});
                aoData.push({"name": "startDate", "value": elementVal('resources-search-startDate')});
                aoData.push({"name": "endDate", "value": elementVal('resources-search-endDate')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");
            }
        });

    $("#resources-search-query-button").click(function () {
        resourcesTable.fnDraw();
    });

    $("#resources-search-clear-button").click(function () {
        $("#resources-search-form")[0].reset();
    });

    $("#resources-add-platformId").change(function () {
        searchResources(this.value, 2, "resources-add-parentId");
    });

    $("#resources-update-platformId").change(function () {
        searchResources(this.value, 2, "resources-update-parentId");
    });

    $("#resources-add-btn").click(function () {
    	var that = $(this);
        var elementsId = new Array('resources-add-platformId', 'resources-add-name', 'resources-add-orders');
        $.validatePlugin.bind(elementsId, true);
        $.validatePlugin.clear(elementsId);
        $("#resources-add-form")[0].reset();
        searchPlatform("resources-add-platformId");
        var dialog = $("#resources-add-dialog").removeClass('hide').dialog({
            modal: true,
            width: 800,
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
                        	alert("验证失败");
                            return;
                        }
                        $.ajax({
                        	url:that.attr("path"),
                            type: "post",
                            data: {
                                "name": $("#resources-add-name").val(),
                                "describer": $("#resources-add-describer").val(),
                                "parentId": $("#resources-add-parentId").val(),
                                "linkUrl": $("#resources-add-linkUrl").val(),
                                "platformId": $("#resources-add-platformId").val(),
                                "orders": $("#resources-add-orders").val(),
                                "authorize":$("#resources-add-authorize").val()
                            },
                            success: function (data, textStatus, jqXHR) {
                                if (data.status == 'SUCCESS') {
                                    dialog.dialog("close");
                                    resourcesTable.fnDraw();
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


    $("#resources-edit-btn").click(function () {
    	var that = $(this);
        var items = $.dataTableCheckedOneItem("resourcesList-table", "请至少选择一个资源", "只能编辑一个资源");
        if (items) {
            searchPlatform("resources-update-platformId");
            $.ajax({
                url: contextPath + "/resources/get?id=" + items[0].value,
                success: function (data, textStatus, jqXHR) {
                    var parent = data.parent;
                    var level = 0;
                    if (parent != null) {
                        level = 1;
                    }
                    if (parent != null && parent.parent != null) {
                        level = 2;
                    }
                    searchResources(data.platformId, level, "resources-update-parentId", function () {
                        var elementsId = new Array('resources-update-id', 'resources-update-platformId', 'resources-update-name', 'resources-update-orders');
                        var values = {
                            "resources-update-id": data.id,
                            "resources-update-platformId": data.platformId,
                            "resources-update-name": data.name,
                            "resources-update-describer": data.describer,
                            "resources-update-linkUrl": data.linkUrl,
                            "resources-update-orders": data.orders,
                            "resources-update-parentId": data.parentId,
                            "resources-update-authorize":data.authorize
                        };
                        $.validatePlugin.rest(values);
                        $.validatePlugin.bind(elementsId, true);
                        var dialog = $("#resources-update-dialog").removeClass('hide').dialog({
                            modal: true,
                            width: 800,
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
                                                    "id": $("#resources-update-id").val(),
                                                    "name": $("#resources-update-name").val(),
                                                    "describer": $("#resources-update-describer").val(),
                                                    "parentId": $("#resources-update-parentId").val(),
                                                    "linkUrl": $("#resources-update-linkUrl").val(),
                                                    "platformId": $("#resources-update-platformId").val(),
                                                    "orders": $("#resources-update-orders").val(),
                                                    "authorize":$("#resources-update-authorize").val()
                                                },
                                                success: function (data, textStatus, jqXHR) {
                                                    if (data.status == 'SUCCESS') {
                                                        dialog.dialog("close");
                                                        $('#resourcesList-ftable_wrapper .active').trigger("click");
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
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("服务器异常，请联系管理员！", "错误");
                }
            })
        }
    })

});

$("#resources-del-btn").click(function () {
	var that = $(this);
    var checkedItems = $.dataTableCheckedItem("resourcesList-table", "请选择资源删除");
    if (checkedItems) {
        var ids = "";
        $.confirmDialog("确认删除", function () {
            for (var i = 0; i < checkedItems.length; i++) {
                ids = ids + "," + checkedItems[i].value;
            }
            $.ajax({
            	url:that.attr("path"),
                type: "GET",
                data: {
                    "ids": ids.substring(1, ids.length)
                },
                success: function (data, textStatus, jqXHR) {
                    if (data.status == 'SUCCESS') {
                        $('#resourcesList-ftable_wrapper .active').trigger("click");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("服务器异常，请联系管理员！", "错误");
                }
            })
        })
    }

});


var searchPlatform = function (platformCk) {
    $.ajax({
        url: contextPath + "/platform/search",
        data: {
            "pageSize": 10000
        },
        type: "POST",
        success: function (data, textStatus, jqXHR) {
            var options = '<option value="">-请选择-</option>';
            $(data.aaData).each(function () {
                options += '<option value="' + this.id + '">' + this.name + '</option>';
            });
            $("#" + platformCk).empty();
            $("#" + platformCk).append(options)
        }
    });
}

var searchResources = function (platformId, level, resourcesCk, func) {
    $.ajax({
        url: contextPath + "/resources/simpleSearch?platformId=" + platformId,
        data: {
            "pageSize": 10000,
            "level": level
        },
        type: "POST",
        success: function (data, textStatus, jqXHR) {
            var options = '<option value="0">-请选择-</option>';
            $(data.aaData).each(function () {
                options += '<option value="' + this.id + '">' + this.namesNav + '</option>';
            });
            $("#" + resourcesCk).empty();
            $("#" + resourcesCk).append(options)

            if (func) {
                func();
            }

        }
    });
}