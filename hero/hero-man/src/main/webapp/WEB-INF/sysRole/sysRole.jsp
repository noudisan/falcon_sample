<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
            平台角色管理
             <span class="pull-right">
	             <hero:button permission='${permissionList}' id="sysRole-add-btn"  className="btn btn-sm" authorize="sysRole:add" />
	             <hero:button permission='${permissionList}' id="sysRole-edit-btn"  className="btn btn-primary btn-sm" authorize="sysRole:update" />
	             <hero:button permission='${permissionList}' id="sysRole-toAssign-btn"  className="btn btn-pink btn-sm" authorize="sysRole:assignResources" />
            </span>
        </h1>
    </div>
    <form id="sysRole-search-form">
        <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">角色名称:</label>
                <input id="sysRole-search-roleName" name="roleName" class="col-sm-8" type="text"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">开始日期:</label>
                <input class="col-sm-8" type="text" id="sysRole-search-startDate" name="startDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">结束时间:</label>
                <input class="col-sm-8" type="text" id="sysRole-search-endDate" name="endDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="col-sm-2" style="width:80px;">
                <button id="sysRole-search-query-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                <i class="icon-search"></i>查询
                </button>
            </div>
            <div class="col-sm-2" style="width:80px;margin-left: 10px;">
                <button id="sysRole-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                <i class="icon-undo"></i>清空
                </button>
            </div>
    </div>
    </form>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="sysRoleList-ftable_wrapper" class="dataTables_wrapper">
                    <table id="sysRoleList-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="sysRoleList-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- add -->
<div id="sysRole-add-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/sysRole/add" id="sysRole-add-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysRole-add-roleName">角色名称*:</label>
            <div class="col-xs-12 col-sm-6" >
                <input type="text" name="roleName" id="sysRole-add-roleName" width="200px"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysRole-add-roleDesc">角色描述*:</label>
            <div class="col-xs-12 col-sm-6">
                <textarea rows="3" cols="30" name="roleDesc" id="sysRole-add-roleDesc"></textarea>
            </div>
        </div>
    </form>
</div>

<!-- update -->
<div id="sysRole-update-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/sysRole/update" id="sysRole-update-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
          <input type="hidden" name="id" id="sysRole-update-id"/>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysRole-update-roleName">角色名称*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="roleName" id="sysRole-update-roleName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysRole-update-roleDesc">角色描述*:</label>
            <div class="col-xs-12 col-sm-6">
                <textarea rows="3" cols="30" name="roleDesc" id="sysRole-update-roleDesc"></textarea>
            </div>
        </div>
    </form>
</div>


<!--资源列表dialog-->
<div id="sysRole-toAssign-dialog" class="hide">
    <div class="page-header">
        <h1>
            分配资源
            <span class="pull-right">
                <button id="sysUserRole-toAssign-btn" class="btn btn-success btn-sm">分配资源</button>
            </span>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form id="sysRoleResources-search-form">
                <div class="row" style="height:40px;">
                    <div class="col-sm-2">
                        <label class="col-sm-4 control-label  no-padding-right">平台:</label>
                        <select style="line-height:41px;" class="col-sm-8 no-padding-right no-padding-left" name="platformId" id="sysRoleResources-search-platformId">
                            <c:forEach items="${platforms}" var="platform">
                                <option value="${platform.id}">${platform.name}</option>
                            </c:forEach>
                        </select>
                        <input id="sysRoleResources-search-roleId" name="roleId" type="hidden"/>
                    </div>
                    <div class="col-sm-2" style="width:80px;">
                        <button id="sysRoleResources-search-query-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                        <i class="icon-search"></i>查询
                        </button>
                    </div>
                    <div class="col-sm-2" style="width:80px;margin-left: 10px;">
                        <button id="sysRoleResources-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                        <i class="icon-undo"></i>清空
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="sysRoleResourcesList-ftable_wrapper" class="dataTables_wrapper">
                    <table id="sysRoleResourcesList-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="sysRoleResourcesList-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${contextPath}/js/sysRole/sysRole.js"></script>

