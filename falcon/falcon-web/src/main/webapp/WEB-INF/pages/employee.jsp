<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>
<%@page import="com.zhiyi.falcon.api.enumType.EmployeeLockType" %>
<%@page import="com.zhiyi.falcon.api.enumType.EmployeeRoleType" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body onload="pageOnLoad()"></body>
<div id="page-content" class="page-content">
    <%--菜单--%>
    <div class="page-header">
        <h1>
            员工管理
     <span class="pull-right">
        <hero:button permission='${permissionList}' id="add_employee_Btn" className="btn btn-info btn-sm"
                     authorize="employee:add"/>
        <hero:button permission='${permissionList}' id="update_employee_Btn" className="btn btn-success btn-sm"
                     authorize="employee:update"/>
        <hero:button permission='${permissionList}' id="batch_lock_employee_Btn" className="btn btn-pink btn-sm"
                     authorize="employee:lock"/>
        <hero:button permission='${permissionList}' id="batch_unlock_employee_Btn" className="btn btn-primary btn-sm"
                     authorize="employee:unlock"/>
      </span>
        </h1>
    </div>
    <%--检索--%>
    <form id="employee-search-form">

        <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">员工姓名:</label>
                <input id="employee-search-userName" name="userName" class="col-sm-7" type="text"/>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">手机号:</label>
                <input id="employee-search-phone" name="phone" class="col-sm-7" type="text"/>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">城市:</label>
                <input id="employee-search-city" name="city" class="col-sm-7" type="text"/>
            </div>


            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">状态:</label>
                <select id="employee-search-isLocked" name="isLocked" class="col-sm-7">
                    <option value="" selected>--请选择--</option>
                    <c:forEach var="lockType" items="<%=EmployeeLockType.values()%>">

                        <option value="${lockType}">${lockType.status}</option>
                    </c:forEach>
                </select>
            </div>


            <div class="col-sm-3" style="width:80px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="baseEmployeeSearch()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>
            <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="clearEmployee(this)">
                    <i class="icon-edit"></i>清空
                </button>
            </div>
        </div>
    </form>
    <%--检索列表--%>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="baseEmployee-ftable_wrapper" class="dataTables_wrapper">
                    <table id="baseEmployee-table"
                           class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="baseEmployee-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%--更新--%>
<div id="update_employee_dialog" class="hide">
    <form action="${pageContext.request.contextPath}/employee/saveOrUpdate" id="update_base_employee_form"
          class="form-horizontal"
          role="form" method="POST">
        <input type="hidden" id="update-base-employee_dialog_id" name="id">

        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_userName">姓名:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="userName" id="update_employee_dialog_userName"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_sex">性别:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="update_employee_dialog_sex" name="sex">
                    <option value="">--请选择--</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_career">职业:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="career" id="update_employee_dialog_career"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_phone">手机号:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="phone" id="update_employee_dialog_phone" maxlength="11"
                       onkeyup="value=value.replace(/[^\d{11}$]/ig,'')" oninput="phoneCheck(event)"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_password_reset">密码重置:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="update_employee_dialog_password_reset" name="password">
                    <option value="NO_PASSWORD_RESET" selected>否</option>
                    <option value="YES_PASSWORD_RESET">是</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_role">角色:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="update_employee_dialog_role" name="role">
                    <option value="" selected>--请选择--</option>
                    <c:forEach var="entryRole" items="<%=EmployeeRoleType.values()%>">
                        <option value="${entryRole}">${entryRole.role}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_city">城市:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="update_employee_dialog_city" name="city">
                    <c:forEach var="city" items="${cityList}">
                        <option value="${city}">${city}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-2 text-right" for="update_employee_dialog_isLocked">状态:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="update_employee_dialog_isLocked" name="isLocked">
                    <option value="" selected>--请选择--</option>
                    <c:forEach var="lockType" items="<%=EmployeeLockType.values()%>">

                        <option value="${lockType}">${lockType.status}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>
</div>

<%--新增--%>
<div id="add_employee_dialog" class="hide">
    <form action="${pageContext.request.contextPath}/employee/saveOrUpdate" id="add_base_employee_form"
          class="form-horizontal"
          role="form" method="POST">

        <div class="form-group">
            <label class="col-xs-2 text-right" for="add_employee_dialog_userName">姓名:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="userName" id="add_employee_dialog_userName" placeholder="请输入姓名"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="add_employee_dialog_sex">性别:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="add_employee_dialog_sex" name="sex">
                    <option value="">--请选择--</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="add_employee_dialog_career">职业:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="career" id="add_employee_dialog_career" placeholder="请输入职业"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="add_employee_dialog_phone">手机号:</label>

            <div class="col-xs-12 col-sm-3">
                <input type="text" name="phone" id="add_employee_dialog_phone" placeholder="请输入11位手机号" maxlength="11"
                       onkeyup="value=value.replace(/[^\d{11}$]/ig,'')" oninput="phoneCheck(event)"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="add_employee_dialog_city">城市:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="add_employee_dialog_city" name="city">
                    <c:forEach var="city" items="${cityList}">
                        <option value="${city}">${city}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right" for="add_employee_dialog_role">角色:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="add_employee_dialog_role" name="role">
                    <option value="" selected>--请选择--</option>
                    <c:forEach var="entryRole" items="<%=EmployeeRoleType.values()%>">
                        <option value="${entryRole}">${entryRole.role}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

    </form>
</div>

<%--锁定--%>
<div id="lock_employee_dialog" class="hide">
    <label style="color:red;" id="lock_employee_lablel"></label>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/employee.js"></script>
