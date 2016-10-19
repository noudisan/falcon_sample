<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.xiaoquwuyou.com/zhiyi/tags" prefix="hero" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" scope="request"/>
<!DOCTYPE html>
<html lang="zh-CN">
<link rel="shortcut icon" href="${contextPath}/images/favicon.ico"/>
<head>
    <%--<meta charset="utf-8"/>--%>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>小区无忧-猎鹰管理平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="${contextPath}/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/assets/css/bootstrap-timepicker.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="${contextPath}/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${contextPath}/assets/css/jquery-ui-1.10.3.full.min.css"/>

    <link rel="stylesheet" href="${contextPath}/assets/css/chosen.css"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/datepicker.css"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/bootstrap-timepicker.css"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/daterangepicker-bs3.css"/>
    <!-- fonts -->
    <%--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />--%>

    <!-- ace styles -->

    <link rel="stylesheet" href="${contextPath}/assets/css/ace.css"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${contextPath}/assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${contextPath}/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->
    <link rel="stylesheet" href="${contextPath}/css/common/alertDialog.css"/>
    <!-- ace settings handler -->

    <script type="text/javascript">
        var contextPath = "${contextPath}";
        var success = "SUCCESS", error = "ERROR";
    </script>

    <script src="${contextPath}/assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${contextPath}/assets/js/html5shiv.js"></script>
    <script src="${contextPath}/assets/js/respond.min.js"></script>
    <![endif]-->


    <!--[if !IE]> -->

    <script src="${contextPath}/assets/js/jquery-2.0.3.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script src="${contextPath}/assets/js/jquery-1.10.2.min.js"></script>
    <![endif]-->

    <!--[if !IE]> -->

    <script type="text/javascript">
        window.jQuery || document.write("<script src='${contextPath}/assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
    </script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='${contextPath}/assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
    </script>
    <![endif]-->

    <script type="text/javascript">
        if ("ontouchend" in document) document.write("<script src='${contextPath}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
    </script>
    <script src="${contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${contextPath}/assets/js/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
    <script src="${contextPath}/assets/js/excanvas.min.js"></script>
    <![endif]-->

    <!-- page specific plugin scripts -->
    <script src="${contextPath}/assets/js/jquery.dataTables.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="${contextPath}/assets/js/json2.js"></script>
    <script src="${contextPath}/assets/js/jquery.nestable.min.js"></script>
    <script src="${contextPath}/assets/js/jquery-ui-1.10.3.full.min.js"></script>

    <!-- form elements scripts -->
    <script src="${contextPath}/assets/js/bootbox.min.js"></script>
    <script src="${contextPath}/assets/js/chosen.jquery.min.js"></script>
    <script src="${contextPath}/assets/js/fuelux/fuelux.spinner.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.validate.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.validate.custom.js"></script>
    <script src="${contextPath}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${contextPath}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${contextPath}/assets/js/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${contextPath}/assets/js/date-time/moment.min.js"></script>
    <script src="${contextPath}/assets/js/date-time/moment-zh.js"></script>
    <script src="${contextPath}/assets/js/date-time/daterangepicker.js"></script>
    <script src="${contextPath}/assets/js/jquery.knob.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.autosize.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.maskedinput.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.form.js"></script>
    <script src="${contextPath}/assets/js/bootstrap-tag.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.hotkeys.min.js"></script>
    <script src="${contextPath}/assets/js/bootstrap-wysiwyg.min.js"></script>
    <script src="${contextPath}/assets/js/jquery.blockUI.js"></script>
    <!-- ace scripts -->
    <script src="${contextPath}/assets/js/ace-elements.min.js"></script>
    <script src="${contextPath}/assets/js/ace.min.js"></script>
    <script src="${contextPath}/assets/js/global.js"></script>



    <!--validate-->
    <script type="text/javascript" src="${contextPath}/js/common/validatePlugIn.js"></script>
    <script type="text/javascript" src="${contextPath}/assets/js/jquery.toJson.js"></script>

    <script type="text/javascript" src="${contextPath}/js/common/config.js"></script>
    <script type="text/javascript" src="${contextPath}/js/common/utils.js"></script>
    <script type="text/javascript" src="${contextPath}/js/common/listTablePlugin.js"></script>
    <script type="text/javascript" src="${contextPath}/js/common/alertDialog.js"></script>
    <script type="text/javascript" src="${contextPath}/js/common/dataTableHelper.js"></script>

    <script type="text/javascript" src="${contextPath}/assets/datepick/WdatePicker.js"></script>
    <script type="text/javascript" src="${contextPath}/js/template.js"></script>
    <script type="text/javascript" src="${contextPath}/js/common/BlockWidget.js"></script>

    <script type="text/javascript">
        String.prototype.endsWith = function (pattern) {
            var d = this.length - pattern.length;
            return d >= 0 && this.lastIndexOf(pattern) === d;
        };
        $(function () {
            var url = window.location.href;
            $("#menu-nav-list .submenu > li").each(function () {
                var hrefSrc = $(this).children().first().attr('href');
                if (url.endsWith(hrefSrc)) {
                    $(this).addClass('active');
                    $(this).parent().parent().addClass('active open');
                }
            });
        })
    </script>
    <style>
        .show {
            display: block;
        }
        .my-dialog .ui-dialog-title, .my-dialog .ui-dialog-titlebar-close {
            display: none;
        }
    </style>
</head>
<body class="navbar-fixed">
<div class="navbar navbar-default navbar-fixed-top" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left" style="height:32px;">
            <a href="#" class="navbar-brand"><img src="${contextPath}/images/logo1.png" style="margin-top:-5px"/>
                <small>&nbsp;&nbsp;猎鹰管理平台</small>
            </a>
        </div>
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <%--<li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i id="icon-bell" class="icon-bell-alt"></i>
                    </a>
                    <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <div class="clearfix">
                                        <span class="pull-left">
                                            <i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>新订单
                                        </span>
                                        <span id="template_new_order_number" class="pull-right badge badge-success">+0</span>
                                </div>
                            </a>
                        </li>
                    </ul>
                </li>--%>
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${contextPath}/assets/avatars/user7.png"/>
						<span class="user-info">
							<small>欢迎光临,</small>
							<strong>${sysUser.userName}</strong>
						</span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="javascript:void(0)" onclick="editMyPwd();">
                                <i class="icon-cog"></i>修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="javascript:void(0)" onclick="logoutFun();">
                                <i class="icon-off"></i>退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <a class="menu-toggler" id="menu-toggler" href="#">
        <span class="menu-text"></span>
    </a>

    <div class="sidebar sidebar-fixed" id="sidebar">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>
        <hero:menu permission='${permissionList}'/>
        <div class="sidebar-collapse" id="sidebar-collapse">
            <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
               data-icon2="icon-double-angle-right"></i>
        </div>

        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>

    </div>

    <div class="main-content">
        <tiles:insertAttribute name="content"/>
    </div>
    <!-- /.main-content -->

</div>
<!-- /.main-container -->

<!-- basic scripts -->


<!-- inline scripts related to this page -->



<div style="display: none">
    <form id="logoutForm" action="${contextPath}/index/logout"></form>
</div>

<div id="dialog-edit-pwd" class="hide">
    <form id="edit-pwd-form" class="form-horizontal" role="form"
          method="post" style="width: auto; min-height: 34px; max-height: none; height: auto;">
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-right">用户名:</label>

            <div class="col-xs-12 col-sm-6">
                <div style="margin-top: 5px;">
                    ${sysUser.userName}
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="oldPassword">原密码:</label>
            <div class="col-xs-12 col-sm-8">
                <div class="clearfix">
                    <input id="oldPassword" type="password" name="oldPassword" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="newPassword">新密码:</label>
            <div class="col-xs-12 col-sm-8">
                <div class="clearfix">
                    <input id="newPassword" type="password" name="newPassword" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="renewPassword">再次输入新密码:</label>
            <div class="col-xs-12 col-sm-8">
                <div class="clearfix">
                    <input id="renewPassword" type="password" name="renewPassword" />
                </div>
            </div>
        </div>
    </form>
</div>

<input type="hidden" id="hidden_error_message" value="${errormessage}"/>
<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>
</body>

</html>
