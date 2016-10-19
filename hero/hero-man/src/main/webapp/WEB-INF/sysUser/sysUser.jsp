<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
            平台用户管理
            <span class="pull-right">
                <hero:button permission='${permissionList}'   id="sysUser-add-btn"  className="btn btn-sm" authorize="sysUser:add" />
                <hero:button permission='${permissionList}'   id="sysUser-edit-btn"  className="btn btn-primary btn-sm" authorize="sysUser:update" />
                <hero:button permission='${permissionList}'   id="sysUser-lock-btn"  className="btn btn-info btn-sm" authorize="sysUser:lock" />
                <hero:button permission='${permissionList}'   id="sysUser-unlock-btn"  className="btn btn-success btn-sm" authorize="sysUser:unlock" />
                <hero:button permission='${permissionList}'   id="sysUser-toAssign-btn"  className="btn btn-pink btn-sm" authorize="sysUser:assignRole" />
            </span>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form id="sysUser-search-form">
                <div class="row" style="height:40px;">
                    <div class="col-sm-2">
                        <label class="col-sm-4 control-label  no-padding-right">用户名:</label>
                        <input id="sysUser-search-userName" name="userName" class="col-sm-8" type="text"/>
                    </div>
                    <div class="col-sm-2">
                        <label class="col-sm-4 control-label  no-padding-right">开始日期:</label>
                        <input class="col-sm-8" type="text" id="sysUser-search-startDate" name="startDate"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </div>
                    <div class="col-sm-2">
                        <label class="col-sm-4 control-label  no-padding-right">结束时间:</label>
                        <input class="col-sm-8" type="text" id="sysUser-search-endDate" name="endDate"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </div>
                    <div class="col-sm-2" style="width:80px;">
                        <button id="sysUser-search-query-button" style="width:80px;" class="btn btn-warning btn-sm"
                                type="button"/>
                        <i class="icon-search"></i>查询
                        </button>
                    </div>
                    <div class="col-sm-2" style="width:80px;margin-left: 10px;">
                        <button id="sysUser-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm"
                                type="button"/>
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
                <div id="sysUserList-ftable_wrapper" class="dataTables_wrapper">
                    <table id="sysUserList-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="sysUserList-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- add -->
<div id="sysUser-add-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/sysUser/add" id="sysUser-add-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysUser-add-userName">用户名称*:</label>

            <div class="col-xs-12 col-sm-6">
                <input type="text" name="userName" id="sysUser-add-userName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysUser-add-password">密码*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="password" name="password" id="sysUser-add-password"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysUser-add-statusFlag">用户状态*:</label>
            <div class="col-xs-12 col-sm-6">
                <select style="line-height:41px;" class="col-sm-6 no-padding-right no-padding-left" name="statusFlag" id="sysUser-add-statusFlag" disabled="disabled">
                    <option value="">-请选择-</option>
                    <option value="1" selected="selected">正常</option>
                    <option value="2">用户锁定</option>
                </select>
            </div>
        </div>
    </form>
</div>

<!-- update -->
<div id="sysUser-update-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/sysUser/update" id="sysUser-update-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
          <input type="hidden" name="id" id="sysUser-update-id"/>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysUser-update-userName">用户名称*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="userName" id="sysUser-update-userName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysUser-update-password">密码*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="password" name="password" id="sysUser-update-password"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="sysUser-update-statusFlag">用户状态*:</label>
            <div class="col-xs-12 col-sm-6">
                <select style="line-height:41px;" class="col-sm-6 no-padding-right no-padding-left" name="statusFlag" id="sysUser-update-statusFlag" >
                    <option value="">-请选择-</option>
                    <option value="1">正常</option>
                    <option value="2">用户锁定</option>
                </select>
            </div>
        </div>
    </form>
</div>


<!--已分配角色列表dialog-->
<div id="sysUser-toAssign-dialog" class="hide">
    <div class="page-header">
        <h1>
            分配角色
            <span class="pull-right">
                <button id="sysUserRole-toAssign-btn" class="btn btn-success btn-sm">分配角色</button>
                <button id="sysUserRole-delete-btn" class="btn btn-danger btn-sm">删除角色</button>
            </span>
        </h1>
    </div>
    <div class="row hidden">
        <div class="col-xs-12">
            <form id="sysUserRole-search-form">
                <div class="row" style="height:40px;">
                    <div class="col-sm-2">
                        <label class="col-sm-4 control-label  no-padding-right">用户ID:</label>
                        <input id="sysUserRole-search-userId" name="userId" class="col-sm-8" type="text" value="1"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="sysUserRoleList-ftable_wrapper" class="dataTables_wrapper">
                    <table id="sysUserRoleList-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="sysUserRoleList-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--分配角色dialog-->
<div id="sysUser-assign-dialog" class="hide">
    <div class="row">
        <form id="sysRole-search-form">
            <div class="row" style="height:40px;">
                <div class="col-sm-3">
                    <label class="col-sm-4 control-label  no-padding-right">角色名称:</label>
                    <input id="sysRole-search-roleName" name="roleName" class="col-sm-8" type="text"/>
                </div>
                <div class="col-sm-3" style="width:80px;">
                    <button id="sysRole-search-query-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                    <i class="icon-search"></i>查询
                    </button>
                </div>
                <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                    <button id="sysRole-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                    <i class="icon-undo"></i>清空
                    </button>
                </div>
            </div>
        </form>
    </div>
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



<script type="text/javascript" src="${contextPath}/js/sysUser/sysUser.js"></script>

