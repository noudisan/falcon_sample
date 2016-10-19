<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
#deliveryData_communityUnit_pictures td.deliveryData_communityUnit_picture{
	width:510px;
	height:510px;
	border:1px solid #666;
}
</style>
<body onload="pageOnLoad()"></body>
<div id="page-content" class="page-content">
  <div class="page-header">
    <h1>
      单元派送数据查询
        <span class="pull-right">
        <hero:button permission='${permissionList}' id="modifyMtBtn" className="btn btn-success btn-sm" authorize="dataCommunityUnit:update"/>
        <hero:button permission='${permissionList}' id="taskSamping" className="btn btn-pink btn-sm" authorize="dataCommunityUnit:sampling"/>
      </span>
    </h1>
  </div>
  <form id="deliverydata-unit-search-form">

    <div class="row" style="height:40px;">
        <div class="col-sm-3">
          <label class="col-sm-4 control-label  no-padding-right">楼栋名称:</label>
          <input id="communityUnit_search_buildingName" name="buildingName" class="col-sm-8" type="text"/>
      </div>
      <div class="col-sm-3">
        <label class="col-sm-4 control-label  no-padding-right">单元名称:</label>
        <input id="communityUnit_search_communityUnitName" name="communityUnitName" class="col-sm-8" type="text"/>
      </div>
      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">小区名称:</label>
          <input id="communityUnit_search_communityName" name="communityName" class="col-sm-8" type="text"/>
      </div>
      <div class="col-sm-3">
        <label class="col-sm-4 control-label no-padding-right">派发数量:</label>
        <input id="communityUnit_search_deliveryNum" name="deliveryNum" class="col-sm-8" type="text"/>
      </div>
      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">派发人员:</label>
          <input id="communityUnit_search_userName" name="userName" class="col-sm-8" type="text"/>
      </div>
      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">抽查状态:</label>
          <select id="communityUnit_search_taskSampling" name="taskSampling" class="col-sm-8">
              <option value="" selected>-请选择-</option>
              <option value="SAMPLING">已抽检</option>
              <option value="NOSAMPLING">未抽检</option>
          </select>
      </div>
      </div>

      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">派送方式:</label>
          <select id="communityUnit_search_deliveryType" name="deliveryType" class="col-sm-8">
              <option value="" selected>-请选择-</option>
              <option value="LIFT">电梯入户</option>
              <option value="STAIRS">步梯入户</option>
              <option value="INLETTER">内信箱</option>
              <option value="OUTLETTER">外信箱</option>
          </select>
      </div>
      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">派送结果:</label>
          <select id="communityUnit_search_deliveryResult" name="deliveryResult" class="col-sm-8">
              <option value="" selected>-请选择-</option>
              <option value="NOTDELIVERY">无法派送</option>
              <option value="CANDELIVERY">可以派送</option>
          </select>
      </div>
      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">结算结果:</label>
          <select id="communityUnit_search_settleStatus" name="settleStatus" class="col-sm-8">
              <option value="" selected>-请选择-</option>
              <option value="UNBALANCED">未结算</option>
              <option value="SUCCESS">结算成功</option>
              <option value="FAIL">结算失败</option>
          </select>
      </div>
      <div class="col-sm-3">
          <label class="col-sm-4 control-label no-padding-right">派发时间：</label>
          <input type="text" class="col-sm-3 no-padding-right no-padding-left" id="search_startDate"
                 name="startDate"
                 onfocus="WdatePicker({startDate:this.value,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                 class="form-control"/>
            <label class="col-sm-1 control-label no-padding-right" style="font-size: 20px;padding-left:12px;">-</label>
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
      <div class="col-sm-3">
	        <button  class="btn btn-warning btn-sm" type="button" onclick="deliveryDataUnitSearch()">
	          <i class="icon-edit"></i>查询
	        </button>
	      <button  class="btn btn-warning btn-sm" type="button" onclick="deliveryDataUnitClear()">
	          <i class="icon-edit"></i>清空
	        </button>
	  </div>


  </form>

  <div class="row">
    <div class="col-xs-12">
      <div class="table-responsive">
        <div id="deliveryData-unit-ftable_wrapper" class="dataTables_wrapper">
          <table id="deliveryData-unit-table"
                 class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="deliveryData-unit-table_info" style="width: 100%;">
          </table>
        </div>
      </div>
    </div>
  </div>

</div>

<div id="deliveryData_communityUnit_dialog" class="hide" >
    <form action="${pageContext.request.contextPath}/deliverydatacommunityunit/updateData" id="deliveryData_communityUnit_form" class="form-horizontal"
          role="form"  method="POST"  >
        <input type="hidden" id="deliveryData_communityUnit_id" name="id">
        <input type="hidden" id="deliveryData_communityUnit_communityUnitId" name="communityUnitId">
        <input type="hidden" id="deliveryData_communityUnit_buildId" name="buildId">
        <input type="hidden" id="deliveryData_communityUnit_communityId" name="communityId">
        <input type="hidden" id="deliveryData_communityUnit_deliveryEmployeeId" name="deliveryEmployeeId">
        <input type="hidden" id="deliveryData_communityUnit_deliveryTaskId" name="deliveryTaskId">
        <input type="hidden" id="deliveryData_communityUnit_taskSampling" name="taskSampling">
        
        <div class="form-group">
	        <div class="col-xs-4">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dialog_userName">派发人员:</label>
	            <div class="col-xs-6">
	                <input type="text" name="userName" id="deliveryData_communityUnit_dialog_userName" readonly/>
	            </div>
	        </div>
	        <div class="col-xs-4">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dialog_buildingName">楼栋名称:</label>
	            <div class="col-xs-6">
	                <input type="text" name="buildingName" id="deliveryData_communityUnit_dialog_buildingName" readonly/>
	            </div>
	        </div>
	        <div class="col-xs-4">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dialog_communityName">小区名称:</label>
	            <div class="col-xs-6 col-sm-3">
	                <input type="text" name="communityName" id="deliveryData_communityUnit_dialog_communityName" readonly/>
	            </div>
	        </div>
		</div>
		<div class="form-group">
	        <div class="col-xs-4">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dialog_communityUnitName">单元名称:</label>
	            <div class="col-xs-6 col-sm-3">
	                <input type="text" name="communityUnitName" id="deliveryData_communityUnit_dialog_communityUnitName" readonly/>
	            </div>
	        </div>
	        <div class="col-xs-4">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dialog_num">派发数量:</label>
	            <div class="col-xs-6 col-sm-3">
	                <input type="text" name="deliveryNum" id="deliveryData_communityUnit_dialog_num"  />
	            </div>
	        </div>
	        <div class="col-xs-4">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dt">开始派发时间:</label>
	            <div class="col-xs-6 col-sm-3">
	                <input type="text" name="deliveryDt" id="deliveryData_communityUnit_dt" readonly/>
	            </div>
	        </div>
        </div>
        <div class="form-group">
	        <div class="col-xs-4" id="delivery_type">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_dialog_type">派发方式:</label>
	            <div class="col-xs-6 col-sm-3">
	                <select id="deliveryData_communityUnit_dialog_type" name="deliveryType">
	                    <option value="LIFT" selected>电梯入户</option>
	                    <option value="STAIRS">步梯入户</option>
	                    <option value="INLETTER">内信箱</option>
	                    <option value="OUTLETTER">外信箱</option>
	                </select>
	            </div>
	        </div>
	        <div class="col-xs-4" id="delivery_result">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_result">派发结果:</label>
	            <div class="col-xs-6 col-sm-3">
	                <select id="deliveryData_communityUnit_result" name="deliveryResult">
	                    <option value="NOTDELIVERY" selected >无法派送</option>
	                    <option value="CANDELIVERY">可以派送</option>
	                </select>
	            </div>
	        </div>
	        <div class="col-xs-4" id="settle_status">
	            <label class="col-xs-4 text-right" for="deliveryData_communityUnit_settleStatus">结算结果:</label>
	            <div class="col-xs-6 col-sm-3">
	                <select id="deliveryData_communityUnit_settleStatus" name="settleStatus" disabled="true"/>
	                    <option value="UNBALANCED" selected>未结算</option>
	                    <option value="SUCCESS">结算成功</option>
	                    <option value="FAIL">结算失败</option>
	                </select>
	            </div>
	        </div>
	

        </div>
        <div class="form-group">
        	<div class="col-xs-12">
	            <label class="col-xs-1 text-right" for="deliveryData_communityUnit_remark">备注:</label>
	            <div class="col-xs-11 col-sm-3">
	                <input type="text" name="remark" id="deliveryData_communityUnit_remark"/>
	            </div>
	        </div>
        </div>
        <div class="form-group">
        	
	        <table id="deliveryData_communityUnit_pictures">
	        	<tr>
	        		<td>大门入口（图中清晰可见所投楼栋的楼号及单元号）</td>
	        		<td>楼内手册1（手册按规定摆放后拍照）</td>
	        	</tr>
	        	<tr>
	        		<td class="deliveryData_communityUnit_picture"></td>
	        		<td class="deliveryData_communityUnit_picture"></td>
	        	</tr>
	        	<tr>
	        		<td>楼内手册2</td>
	        		<td>楼内手册3</td>
	        	</tr>
	        	<tr>
	        		<td class="deliveryData_communityUnit_picture"></td>
	        		<td class="deliveryData_communityUnit_picture"></td>
	        	</tr>
	        </table>
        </div>      
    </form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliveryDataCommunityUnit.js"></script>
