<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body onload="pageOnLoad()"></body>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
            任务管理
            <span class="pull-right">
                <hero:button permission="${permissionList}" id="addMtBtn" className="btn btn-info btn-sm"
                             authorize="deliveryTask:add"/>
                <hero:button permission="${permissionList}" id="modifyMtBtn" className="btn btn-success btn-sm"
                             authorize="deliveryTask:update"/>
            </span>
        </h1>
    </div>

    <form id="task-search-form">
        <div class="row" style="height:40px;">
            <div class="col-sm-3">
                <label class="col-sm-6 ">派发数量:</label>
                <input id="deliveryTask_search_count" name="sendCount" class="col-sm-6" type="text"/>
            </div>
            <div class="col-sm-3">
                <label class="col-sm-6">集结地址:</label>
                <input id="deliveryTask_search_massAddress" name="massAddress" class="col-sm-6" type="text"/>
            </div>
            <div class="col-sm-3">
                <label class="col-sm-6">领队:</label>
                <input id="deliveryTask_search_leader" name="leader" class="col-sm-6" type="text"/>
            </div>
            <div class="col-sm-3">
                <label class="col-sm-6">司机:</label>
                <input id="deliveryTask_search_driver" name="driver" class="col-sm-6" type="text"/>
            </div>

        </div>
        <div class="row" style="height:40px;">
            <div class="col-sm-3">
                <label class="col-sm-6">作业区域:</label>
                <input id="deliveryTask_search_region" name="region" class="col-sm-6" type="text"/>
            </div>
            <div class="col-sm-3">
                <label class="col-sm-6">状态:</label>
                <select  class="col-sm-6" id="deliveryTask_search_status" name="status">
                    <option value="" selected >-请选择-</option>
                    <option value="TASKTOBEGIN">任务即将开始</option>
                    <option value="TASKBEGIN" >任务开始</option>
                    <option value="TASKEND">任务结束</option>
                </select>
            </div>

            <div class="col-sm-3">
                <label class="col-sm-6" >是否抽检:</label>
                <select class="col-sm-6" id="deliveryTask_search_isSampling" name="isSampling">
                    <option value="" selected>--请选择--</option>
                    <option value="SAMPLING">已抽检</option>
                    <option value="NOSAMPLING">未抽检</option>
                </select>
            </div>
            <div class="col-sm-3" style="width:80px;">
                <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="deliveryTaskSearch()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>
            <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="deliveryTaskClear()">
                    <i class="icon-edit"></i>清空
                </button>
            </div>

        </div>
    </form>

    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="deliveryTask-ftable_wrapper" class="dataTables_wrapper">
                    <table id="deliveryTask-table"
                           class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="deliveryTask-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delivery_task_dialog" class="hide" >
    <form action="${pageContext.request.contextPath}/deliverytask/saveOrUpdate" id="delivery_task_form" class="form-horizontal"
          role="form"  method="POST"  >
        <input type="hidden" id="delivery_task_id" name="id">
        <input type="hidden" id="delivery_task_code" name="code">
        <input type="hidden" id="delivery_task_check_status" >

        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_region">作业区域*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="region" id="delivery_task_dialog_region" maxlength="30"  />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_sendCount">派发数量*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="sendCount" id="delivery_task_dialog_sendCount" maxlength="30" />
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_section_sectionNameStr">任务板块*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="hidden" name="sectionIdStr" id="delivery_task_dialog_section_sectionIdStr"   />
                    <input type="text" name="sectionNameStr" id="delivery_task_dialog_section_sectionNameStr" readonly  />
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-2">
                <label class=" control-label col-xs-12  no-padding-right" for="delivery_task_dialog_section_employeeNameStr">派送员*:</label>
            </div>
            <div class="col-sm-9">
                <input type="hidden" name="employeeIdStr" id="delivery_task_dialog_section_employeeIdStr"   />
                <input type="text" name="employeeNameStr" id="delivery_task_dialog_section_employeeNameStr" readonly style="width:100%"  />
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_massTime">集结时间*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="massTime" id="delivery_task_dialog_massTime"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_massAddress">集结地址*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="massAddress" id="delivery_task_dialog_massAddress" maxlength="50"/>
                </div>
            </div>
        </div>

        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_leader">领队*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="leader" id="delivery_task_dialog_leader" maxlength="30" />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_leaderPhoneNum">领队手机号*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="leaderPhoneNum" id="delivery_task_dialog_leaderPhoneNum" maxlength="20"/>
                </div>
            </div>
        </div>

        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_driver">司机*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="driver" id="delivery_task_dialog_driver" maxlength="30" />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_driverPhoneNum">司机手机号*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="driverPhoneNum" id="delivery_task_dialog_driverPhoneNum" maxlength="20"/>
                </div>
            </div>
        </div>

        <div class="row" style="height:80px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_startTime">任务开始时间*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="startTime" id="delivery_task_dialog_startTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"  />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_taskDesc">备注:</label>
                <div class="col-xs-12 col-sm-6">
                    <textarea rows="3" cols="20" name="taskDesc" id="delivery_task_dialog_taskDesc" maxlength="100" ></textarea>
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;display:none"  id="delivery_task_status_row">
            <%--<div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_isSampling">是否抽检:</label>
                <div class="col-xs-12 col-sm-6">
                    <select id="delivery_task_dialog_isSampling" name="isSampling">
                        <option value="NOSAMPLING" selected>未抽检</option>
                        <option value="SAMPLING" >已抽检</option>
                    </select>
                </div>
            </div>--%>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="delivery_task_dialog_status">任务状态:</label>
                <div class="col-xs-12 col-sm-6">
                    <select id="delivery_task_dialog_status" name="status">
                        <option value="TASKTOBEGIN" selected >任务即将开始</option>
                        <option value="TASKBEGIN" >任务开始</option>
                        <option value="TASKEND">任务结束</option>
                    </select>
                </div>
            </div>
        </div>

    </form>
</div>

<jsp:include page="common/sectionDialog.jsp"></jsp:include>
<jsp:include page="common/employeeDialog.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliveryTask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/sectionDialog.js"></script>

