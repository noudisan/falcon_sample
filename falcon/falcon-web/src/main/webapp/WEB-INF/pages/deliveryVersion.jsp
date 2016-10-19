<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body onload="pageOnLoad()"></body>
<div id="page-content" class="page-content">
    <%--菜单--%>
    <div class="page-header">
        <h1>
            版本管理
     <span class="pull-right">
        <hero:button permission='${permissionList}' id="add_version_Btn" className="btn btn-info btn-sm"
                     authorize="version:add"/>
        <hero:button permission='${permissionList}' id="update_version_Btn" className="btn btn-success btn-sm"
                     authorize="version:update"/>
     </span>
        </h1>
    </div>
    <%--检索--%>
    <form id="version-search-form">

        <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">版本号:</label>
                <input id="delivery-version-search-versionCode" name="versionCode" class="col-sm-7" type="text"/>
            </div>


            <div class="col-sm-3" style="width:80px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button"
                        onclick="deliveryVersionSearch()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>
            <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button"
                        onclick="clearDeliveryVersion(this)">
                    <i class="icon-edit"></i>清空
                </button>
            </div>
        </div>
    </form>
    <%--检索列表--%>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="delivery-version-ftable_wrapper" class="dataTables_wrapper">
                    <table id="delivery-version-table"
                           class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="delivery-version-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delivery_version_dialog" class="hide">
    <form action="${pageContext.request.contextPath}/deliveryversion/saveOrUpdate" id="delivery_version_form"
          class="form-horizontal"
          role="form" method="POST">
        <input type="hidden" name="id" id="delivery_version_dialog_id"/>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="delivery_version_dialog_versionCode">版本号:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="versionCode" id="delivery_version_dialog_versionCode"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="delivery_version_dialog_versionName">版本名称:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="versionName" id="delivery_version_dialog_versionName"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="delivery_version_dialog_systemType">系统类型:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="delivery_version_dialog_systemType" name="systemType">
                    <option value="" selected>-请选择-</option>
                    <option value="iPhone">iPhone</option>
                    <option value="Android">Android</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="delivery_version_dialog_updateContent">更新内容:</label>

            <div class="col-xs-12 col-sm-3">
                <textarea class="form-control" rows="5" type="text" name="updateContent"
                          id="delivery_version_dialog_updateContent"> </textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="delivery_version_dialog_isForceUpdate">强制更新:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="delivery_version_dialog_isForceUpdate" name="isForceUpdate">
                    <option value="" selected>-请选择-</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </div>
        </div>
    </form>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliveryVersion.js"></script>
