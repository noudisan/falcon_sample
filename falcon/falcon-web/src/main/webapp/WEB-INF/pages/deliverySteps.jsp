<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body onload="pageOnLoad()"></body>
<div id="page-content" class="page-content">
    <%--菜单--%>
    <div class="page-header">
        <h1>
            步数查询
        </h1>
    </div>
    <%--检索--%>
    <form id="steps-search-form">

        <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">员工号:</label>
                <input id="delivery-steps-search-userId" name="userId" class="col-sm-7" type="text"/>
            </div>


            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">任务编号:</label>
                <input id="delivery-steps-search-taskId" name="taskId" class="col-sm-7" type="text"
                        />
            </div>

            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">开始时间:</label>
                <input type="text" class="col-sm-7 no-padding-right no-padding-left" id="delivery-steps-search-startTime" name="startTime"
                       onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" class="form-control"/>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-5 control-label  no-padding-right">结束时间:</label>
                <input type="text" class="col-sm-7 no-padding-right no-padding-left" id="delivery-steps-search-endTime" name="endTime"
                       onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" class="form-control"/>
            </div>

            <div class="col-sm-3" style="width:80px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button"
                        onclick="deliveryStepsSearch()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>
            <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button"
                        onclick="clearDeliverySteps(this)">
                    <i class="icon-edit"></i>清空
                </button>
            </div>
        </div>
    </form>
    <%--检索列表--%>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="delivery-steps-ftable_wrapper" class="dataTables_wrapper">
                    <table id="delivery-steps-table"
                           class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="delivery-steps-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliverySteps.js"></script>
