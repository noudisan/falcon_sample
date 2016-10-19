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
      楼栋派送数据查询
        <span class="pull-right">
            <span class="pull-right">
                <hero:button permission='${permissionList}' id="modifyMtBtn" className="btn btn-success btn-sm" authorize="dataBuilding:update"/>
            </span>
        </span>
    </h1>
  </div>
  <form id="deliverydata-unit-search-form">
      <div class="row" style="height:40px;">
        <div class="col-sm-3">
          <label class="col-sm-4 control-label  no-padding-right">楼栋名称:</label>
          <input id="building_search_name" name="name" class="col-sm-8" type="text"/>
        </div>
        <div class="col-sm-3">
          <label class="col-sm-4 control-label  no-padding-right">小区名称:</label>
          <input id="building_search_communityName" name="communityName" class="col-sm-8" type="text"/>
        </div>
        <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">派送人:</label>
          <input id="building_search_userName" name="userName" class="col-sm-8" type="text"/>
        </div>
        <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">派发数量:</label>
          <input id="building_search_deliveryNum" name="deliveryNum" class="col-sm-8" type="text"/>
        </div>
     </div>
      <div class="row" style="height:40px;">
          <div class="col-sm-3">
              <label class="col-sm-4">派发状态:</label>
              <select id="building_search_deliveryStatus" name="deliveryStatus" class="col-sm-8">
                  <option value="" selected>-请选择-</option>
                  <option value="TO_DELIVERY">待派送</option>
                  <option value="IN_DELIVERY">正在派送</option>
                  <option value="COMPLETE_DELIVERY">已完成</option>
                  <option value="CANT_DELIVERY">无法派送</option>
              </select>
          </div>
          <div class="col-sm-3">
              <label class="col-sm-4 control-label no-padding-right">派发时间：</label>
              <input type="text" class="col-sm-3 no-padding-right no-padding-left" id="search_startDate"
                     name="startDate"
                     onfocus="WdatePicker({startDate:this.value,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                     class="form-control"/>
              <label class="col-sm-1 control-label no-padding-left no-padding-right" style="font-size: 20px;">-</label>
              <input type="text" class="col-sm-3 no-padding-right no-padding-left" id="search_endDate"
                     name="endDate"
                     onfocus="WdatePicker({startDate:this.value,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                     class="form-control"/>
          </div>
          <div class="col-sm-3">
              <label class="col-sm-4 control-label no-padding-right">派发城市：</label>
              <input type="text" class="col-sm-8 no-padding-right no-padding-left" id="search_deliveryCity"
                     name="deliveryCity"
                     class="form-control"/>
          </div>
        <div class="col-sm-3" style="width:80px;">
            <button style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="deliveryDataBuildingSearch()">
                <i class="icon-edit"></i>查询
            </button>
        </div>
        <div class="col-sm-3" style="width:80px;margin-left: 10px;">
            <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="deliveryDataBuildingClear()">
                <i class="icon-edit"></i>清空
            </button>
        </div>
      </div>
  </form>

  <div class="row">
    <div class="col-xs-12">
      <div class="table-responsive">
        <div id="deliveryData-building-ftable_wrapper" class="dataTables_wrapper">
          <table id="deliveryData-building-table"
                 class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="deliveryData-building-table_info" style="width: 100%;">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

<div id="deliveryData_building_dialog" class="hide" >
    <form action="${pageContext.request.contextPath}/deliverydatabuilding/updateData" id="deliveryData_building_form" class="form-horizontal"
          role="form"  method="POST"  >
        <input type="hidden" id="deliveryData_building_id" name="id">
        <input type="hidden" id="deliveryData_building_buildId" name="buildId">
        <input type="hidden" id="deliveryData_building_communityId" name="communityId">
        <input type="hidden" id="deliveryData_building_deliveryEmployeeId" name="deliveryEmployeeId">
        <input type="hidden" id="deliveryData_building_deliveryTaskId" name="deliveryTaskId">
        <input type="hidden" id="deliveryData_building_beginDt" name="beginDt">
        <input type="hidden" id="deliveryData_building_endDt" name="endDt">
        <div class="form-group">
            <label class="col-xs-2 text-right" for="deliveryData_building_dialog_name">楼栋名称:</label>
            <div class="col-xs-12 col-sm-3">
                <input type="text" name="name" id="deliveryData_building_dialog_name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="deliveryData_building_dialog_communityName">小区名称:</label>
            <div class="col-xs-12 col-sm-3">
                <input type="text" name="communityName" id="deliveryData_building_dialog_communityName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="deliveryData_building_dialog_userName">派送人员:</label>
            <div class="col-xs-12 col-sm-3">
                <input type="text" name="userName" id="deliveryData_building_dialog_userName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="deliveryData_building_dialog_num">派发数量:</label>
            <div class="col-xs-12 col-sm-3">
                <input type="text" name="deliveryNum" id="deliveryData_building_dialog_num"  />
            </div>
        </div>
        <div class="form-group" id="delivery_status">
            <label class="col-xs-2 text-right" for="deliveryData_building_dialog_status">派发状态:</label>
            <div class="col-xs-12 col-sm-3">
                <select id="deliveryData_building_dialog_status" name="deliveryStatus">
                    <option value="TO_DELIVERY" selected>待派送</option>
                    <option value="IN_DELIVERY">正在派送</option>
                    <option value="COMPLETE_DELIVERY">已完成</option>
                    <option value="CANT_DELIVERY">无法派送</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="deliveryData_building_dt">开始派发时间:</label>
            <div class="col-xs-12 col-sm-3">
                <input type="text" name="deliveryDt" id="deliveryData_building_dt"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="deliveryData_building_remark">备注:</label>
            <div class="col-xs-12 col-sm-3">
                <input type="text" name="remark" id="deliveryData_building_remark"/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliveryDataBuilding.js"></script>
