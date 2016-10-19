/**
 * 简单封装datatable
 */
var opMgr = {};
(function ($) {
    var defaults = {
        bDestroy: true,
        bServerSide: true,
        sServerMethod: "POST",
        bProcessing: true,
        bSort: true,
        oLanguage: {"sProcessing": PROCESS_LOADING},
        bFilter: false,
        iDisplayLength: 15,
        aLengthMenu: [15, 30, 45],
        opBtns: {
            add: true,
            update: true,
            remove: true
        },
        createExtBar: "",
        dialog: {
            width: 600
        },
        validateIds: [],
        addForm: '#add-form',
        addDialog: '#add-dialog',
        updateForm: '#update-form',
        updateDialog: '#update-dialog',
        cusRowCallBack: false, //自定义fnRowCallback,默认为false,即默认有更新删除
        cusServerParams: false, //自定义fnServerParams,默认为false,
        primaryKey: 'id' //对应数据库主键,默认是id
    };
    var oTable = {};

    var drawTable = function (options) {
        var fnRowCallback = function (nRow, aData, iDisplayIndex) {
            var id = aData[options.primaryKey];
            var datas = aData;
            var btnHtml = '<div class="action-buttons">';
            if (options.opBtns.update) {
                btnHtml += '<button class="btn btn-xs btn-info" onclick="opMgr.doUpdate(' + id + ',this);"><i class="icon-edit bigger-120"></i>' + BUTTON_EDIT + '</button>';
            }
            if (options.opBtns.remove) {
                btnHtml += '&nbsp;&nbsp;<button class="btn btn-xs btn-info" onclick="opMgr.doRemove(' + id + ', this);"><i class="icon-cut bigger-120"></i>' + BUTTON_REMOVE + '</button>';
            }
            if (options.createExtBar.length > 0) {//扩展btn
                btnHtml += options.createExtBar;
            }
            btnHtml += ('</div>');
            $('td:eq(' + options.btnIndex + ')', nRow).html(btnHtml);
        }

        var fnServerParams = function (aoData) {
            var searchVals = $.wyutil.getValues($('#search-form'));
            $.each(searchVals, function (name, value) {
                aoData.push({"name": name, "value": value});
            })
        }

        oTable = $(options.id).dataTable({
            "bDestroy": options.bDestroy,
            "bServerSide": options.bServerSide,
            "sServerMethod": options.sServerMethod,
            "sAjaxSource": options.urls.listUrl,
            "bProcessing": options.bProcessing,
            "bSort": options.bSort,
            "aaSorting": options.aaSorting,
            "oLanguage": options.oLanguage,
            "bFilter": options.bFilter,
            "iDisplayLength": options.iDisplayLength,
            "aLengthMenu": options.aLengthMenu,
            "aoColumns": options.aoColumns,
            "fnRowCallback": options.cusRowCallBack ? options.fnRowCallback : fnRowCallback,
            "fnServerParams": options.cusServerParams ? options.fnServerParams : fnServerParams
        });
    };

    opMgr = {
        options: '',
        doAdd: function () {
            var that = this;
            $(that.options.addForm)[0].reset();
            var dialog = $(that.options.addDialog).removeClass('hide').dialog({
                modal: true,
                width: that.options.dialog.width,
                //title: $.wyutil.format($.wyutil.dialog_title, BUTTON_CREATE + that.options.title),
                title_html: true,
                buttons: [
                    {
                        text: BUTTON_CANCEL,
                        "class": "btn btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    },
                    {
                        text: BUTTON_SAVE,
                        "class": "btn btn-primary btn-xs",
                        click: function () {
                            var values = $.wyutil.getValues($(that.options.addForm));
                            if (!validForm(values, that.options.validateIds)) {
                                return;
                            }
                            $.ajax({
                                url: that.options.urls.addUrl,
                                type: "POST",
                                dataType: "json",
                                data: values,
                                success: function (data) {
                                    if (data.status == 'SUCCESS') {
                                        dialog.dialog("close");
                                        oTable.fnDraw();
                                    } else {
                                        alert(data.message);
                                    }
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    alert(ERRORS_SERVER, MSG_ERRORS);
                                }
                            })
                        }
                    }
                ]
            });
        },

        doUpdate: function (id, dom) {
            var that = this;
            $(that.options.updateForm)[0].reset();
            $.ajax({
                url: that.options.urls.getUrl,
                type: "GET",
                data: {"id": id},
                success: function (data, textStatus, jqXHR) {
                    $.wyutil.setValues($(that.options.updateForm), data);
                    var dialog = $(that.options.updateDialog).removeClass('hide').dialog({
                        modal: true,
                        width: that.options.dialog.width,
                        //title: $.wyutil.format($.wyutil.dialog_title, BUTTON_EDIT + that.options.title),
                        title_html: true,
                        buttons: [
                            {
                                text: BUTTON_CANCEL,
                                "class": "btn btn-xs",
                                click: function () {
                                    $(this).dialog("close");
                                }
                            },
                            {
                                text: BUTTON_SAVE,
                                "class": "btn btn-primary btn-xs",
                                click: function () {
                                    var values = $.wyutil.getValues($(that.options.updateForm));
                                    if (!validForm(values, that.options.validateIds)) {
                                        return;
                                    }
                                    $.ajax({
                                        url: that.options.urls.updateUrl,
                                        type: "POST",
                                        dataType: "json",
                                        data: values,
                                        success: function (data, textStatus, jqXHR) {
                                            if (data.status == 'SUCCESS') {
                                                dialog.dialog("close");
                                                var row = $(dom).parent().parent().parent().children();
                                                row.each(function (i, field) {
                                                    var dataVal = $(field).attr('data-val');
                                                    if ($(field).attr('data-val')) {
                                                        $(field).text(values[dataVal]);
                                                    }
                                                });
                                            } else {
                                                alert(data.message);
                                            }
                                        },
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            alert(ERRORS_SERVER, MSG_ERRORS);
                                        }
                                    })
                                }
                            }
                        ]
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(ERRORS_SERVER, MSG_ERRORS);
                }
            });
        },

        doRemove: function (id) {
            var that = this;
            if (id == undefined) {
                alert($.wyutil.format(ERRORS_MUST_CHOOSE, that.options.title));
                return false;
            }
            if (confirm($.wyutil.format(CONFIRM_DO, BUTTON_REMOVE))) {
                $.ajax({
                    url: that.options.urls.removeUrl,
                    data: {
                        'id': id
                    },
                    type: "POST",
                    success: function (data, textStatus, jqXHR) {
                        if (data.status == 'SUCCESS') {
                            location.reload();
                        } else {
                            alert(data.message);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(ERRORS_SERVER, MSG_ERRORS);
                    }
                });
            }

        },

        doBatchRemove: function (ids) {
            var that = this;
            if (ids == undefined || ids.length == 0) {
                alert($.wyutil.format(ERRORS_MUST_CHOOSE, that.options.title));
                return false;
            }
            if (confirm($.wyutil.format(CONFIRM_DO_CHOOSE, BUTTON_REMOVE))) {
                $.ajax({
                    url: that.options.urls.removeUrl,
                    data: {
                        'ids': ids
                    },
                    type: "POST",
                    success: function (data, textStatus, jqXHR) {
                        if (data.status == 'SUCCESS') {
                            //location.reload();
                            drawTable.fnDraw();
                        } else {
                            alert(data.message);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(ERRORS_SERVER, MSG_ERRORS);
                    }
                });
            }
        }
    }

    var validForm = function (formValues, validItems) {
        for (var i = 0; i < validItems.length; i++) {
            var field = validItems[i];
            var name = field.name;
            var fieldVal = formValues[name];

            if (!field.allowBlank) {
                if ($.validatePlugin.isNull(fieldVal)) {
                    alert(field.blankText);
                    return false;
                }
            }
            if (field.maxLength) {
                var charLength = $.wyutil.countCharacters(fieldVal);
                if (charLength > field.maxLength) {
                    alert(field.maxLengthText);
                    return false;
                }
            }
            if (field.minLength) {
                var charLength = $.wyutil.countCharacters(fieldVal);
                if (charLength < field.minLength) {
                    alert(field.minLengthText);
                    return false;
                }
            }
            if (field.regex) {
                if (!field.regex.test(fieldVal)) {
                    alert(field.regexText);
                    return false;
                }
            }
//			if (field.vtype){
//				switch (field.vtype){  
//				case "url": 
//					//TODO url check
//					break;
//				case "email":
//					//TODO email check
//					break;   
//				case "mobile":
//					//TODO mobile check
//					break;
//				case "alpha":   
//					//TODO 字母 check
//					break;
//				case "alphanum":
//					//TODO 数字check
//					break;
//				default:
//					//TODO 
//				}
//			}
        }
        return true;
    }

    //TODO 改写掉这部分写死的代码
    $("#add-btn").click(function () {
        opMgr.doAdd();
    });

    $("#search-btn").click(function () {
        oTable.fnDraw();
    });

    $("#clear-btn").click(function () {
        $("#search-form")[0].reset();
    });

    $.fn.listTable = function (option) {
        var options = $.extend(defaults, option);
        opMgr.options = options;
        drawTable(options);
    }

})(jQuery)
