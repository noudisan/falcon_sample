<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
           平台管理
             <span class="pull-right">
                    <hero:button permission='${permissionList}'   id="platform-add-btn"  className="btn btn-sm" authorize="platform:add" />
		<hero:button permission='${permissionList}'   id="platform-edit-btn"  className="btn btn-primary btn-sm" authorize="platform:update" />
            </span>
        </h1>
    </div>
    <form id="platform-search-form">
        <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">平台名称:</label>
                <input id="platform-search-name" name="name" class="col-sm-8" type="text"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">平台URL:</label>
                <input id="platform-search-url" name="url" class="col-sm-8" type="text"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">开始日期:</label>
                <input class="col-sm-8" type="text" id="platform-search-startDate" name="startDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">结束时间:</label>
                <input class="col-sm-8" type="text" id="platform-search-endDate" name="endDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="col-sm-2" style="width:80px;">
                <button id="platform-search-query-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                <i class="icon-search"></i>查询
                </button>
            </div>
            <div class="col-sm-2" style="width:80px;margin-left: 10px;">
                <button id="platform-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                <i class="icon-undo"></i>清空
                </button>
            </div>
    </div>
    </form>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="platformList-ftable_wrapper" class="dataTables_wrapper">
                    <table id="platformList-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="platformList-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- add -->
<div id="platform-add-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/platform/add" id="platform-add-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="platform-add-name">平台名称*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="name" id="platform-add-name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="platform-add-url">平台访问地址*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="url" id="platform-add-url"/>
            </div>
        </div>
    </form>
</div>

<!-- update -->
<div id="platform-update-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/platform/update" id="platform-update-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
          <input type="hidden" name="id" id="platform-update-id"/>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="platform-update-name">平台名称*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="name" id="platform-update-name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="platform-update-url">平台URL*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="url" id="platform-update-url"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="platform-update-secretKey">平台密钥*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="secretKey" id="platform-update-secretKey"/>
            </div>
       </div>
    </form>
</div>
<script type="text/javascript" src="${contextPath}/js/platform/platform.js"></script>

