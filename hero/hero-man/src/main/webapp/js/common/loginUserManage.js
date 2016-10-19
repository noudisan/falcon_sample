function changePwd() {
    var changeDialog = $("#edit-pwd-form").dialog({
        modal: true,
        /*title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-cloud-upload'></i>修改密码</h4></div>",*/
        title_html: true,
        width: 400,
        close: function (event, ui) {
            $('#edit-pwd-form')[0].reset();
            changeDialog.dialog("destroy");
        },
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
                    var $form = $('#edit-pwd-form');
                    if (!$form.valid()) return false;
                    var elementsId = new Array('password', 'newPassword', 'confirmNewPassword');
                    $.validatePlugin.bind(elementsId, true);
                    if ($.validatePlugin.validate()) {
                        $.ajax({
                            url: contextPath + "/sysUser/updatePwd",
                            type: $form.attr('method'),
                            data: $form.serialize(),
                            success: function (data) {
                                if (data.status == "SUCCESS") {
                                    changeDialog.dialog("close");
                                } else {
                                    $.errorDialog(data.message)
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert("服务器异常，请联系管理员！", "错误");
                            }
                        });
                    }
                }
            }
        ]
    });
}
