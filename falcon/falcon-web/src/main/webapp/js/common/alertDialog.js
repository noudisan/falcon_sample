//override dialog's title function to allow for HTML titles
$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
    _title: function (title) {
        var $title = this.options.title || '&nbsp;'
        if (("title_html" in this.options) && this.options.title_html == true)
            title.html($title);
        else title.text($title);
    }
}));

//提示弹窗
$.alertDialog = function (text) {
    var id = new Date().getTime();
    $("<div id='dialog-tips" + id + "' class='hide'><p style='color:#669fc7;font-size: 15px;padding-top: 15px;'>" + text + "</p></div>").appendTo('body');
    $("#dialog-tips" + id).removeClass('hide').dialog({
        modal: true,
        dialogClass: "alert-dialog",
        title_html: true,
        buttons: [
            {
                text: "确定",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}

//确认弹窗
$.confirmDialog = function (text, call) {
    var id = new Date().getTime();
    $("<div id='dialog-confirm" + id + "' class='hide'><p style='color:#669fc7;font-size: 15px;padding-top: 15px;'><i class='icon-ok'></i>&nbsp;&nbsp;" + text + "</p></div>").appendTo('body');
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

//警告确认弹窗
$.warnDialog = function (text, call) {
    var id = new Date().getTime();
    $("<div id='dialog-warn" + id + "' class='hide'><p style='color:#d15b47;font-size: 15px;padding-top: 15px;'><i class='icon-warning-sign red'></i>&nbsp;&nbsp;" + text + "</p></div>").appendTo('body');
    $("#dialog-warn" + id).removeClass('hide').dialog({
        resizable: false,
        dialogClass: "alert-dialog",
        modal: true,
        title_html: true,
        buttons: [
            {
                html: "确定",
                "class": "btn btn-danger btn-xs",
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

//错误弹窗
$.errorDialog = function (text, call) {
    var id = new Date().getTime();
    $("<div id='dialog-error" + id + "' class='hide'><p style='color:#d15b47;font-size: 15px;padding-top: 15px;'>" + text + "</p></div>").appendTo('body');
    $("#dialog-error" + id).removeClass('hide').dialog({
        resizable: false,
        dialogClass: "alert-dialog",
        modal: true,
        title_html: true,
        buttons: [
            {
                html: "确定",
                "class": "btn btn-danger btn-xs",
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



