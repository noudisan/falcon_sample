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
      小区管理
      <span class="pull-right">
        <hero:button permission='${permissionList}' id="addMtBtn" className="btn btn-info btn-sm"
                     authorize="baseCommunity:add"/>
        <hero:button permission='${permissionList}' id="modifyMtBtn" className="btn btn-success btn-sm"
                     authorize="baseCommunity:update"/>
      </span>
    </h1>
  </div>
  <form id="community-search-form">
    <div class="row" style="height:40px;">
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">小区编号:</label>
        <input id="community-search-code" name="code" class="col-sm-6" type="text"/>
      </div>
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">小区名称:</label>
        <input id="community-search-name" name="name" class="col-sm-6" type="text"/>
      </div>
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">拼音:</label>
        <input id="community-search-pinyin" name="pinyin" class="col-sm-6" type="text"/>
      </div>
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">所属城市:</label>
        <%--<input type="text"/>--%>
        <select  id="community-search-city" name="city" class="col-sm-6" >
          <c:forEach items="${cityList}" var="city"><option value="${city}">${city}</option></c:forEach>
        </select>
      </div>
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">行政区域:</label>
        <input id="community-search-area" name="area" class="col-sm-6" type="text"/>
      </div>
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">行政板块:</label>
        <input id="community-search-section" name="section" class="col-sm-6" type="text"/>
      </div>
    </div>
    <div class="row" style="height:40px;">

      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">地址:</label>
        <input id="community-search-address" name="address" class="col-sm-6" type="text"/>
      </div>

      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">建筑类型:</label>
        <select style="line-height:41px;" class="col-sm-6 no-padding-right no-padding-left" name="buildType" id="community-search-buildType">
          <option value="">-请选择-</option>
          <option value="COMMUNITY_HOUSE">小区住宅</option>
          <option value="NON_COMMUNITY_HOUSE">非小区住宅</option>
          <option value="BUSINESS">商业</option>
          <option value="SCHOOL">学校</option>
        </select>
      </div>
      <div class="col-sm-2">
        <label class="col-sm-6 control-label  no-padding-right">用途/类型:</label>
        <input id="community-search-funType" name="funType" class="col-sm-6" type="text" />
      </div>
      <div class="col-sm-3" style="width:80px;">
        <button onclick="deliveryCommunitySearch()" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
        <i class="icon-edit"></i>查询
        </button>
      </div>
      <div class="col-sm-3" style="width:80px;margin-left: 10px;">
        <button onclick="deliveryCommunityClear()" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
        <i class="icon-undo"></i>清空
        </button>

    </div>
    </div>
  </form>

  <div class="row">
    <div class="col-xs-12">
      <div class="table-responsive">
        <div id="communityList-ftable_wrapper" class="dataTables_wrapper">
          <table id="communityList-table" class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="communityList-table_info" style="width: 100%;">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>


<!--添加小区-->
<div id="deliveryCommunity_dialog" class="hide">
  <form class="form-horizontal"  style="min-height: 34px; max-height: none; height: auto;"
        action="${pageContext.request.contextPath}/deliveryCommunity/saveOrUpdate" id="delivery_community_form">
    <input type="hidden" id="deliveryCommunity_dialog_id" name="id">
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_name">小区名称*:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="communityName" id="deliveryCommunity_dialog_name" maxlength="30" />
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_postcode">邮编:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="postcode" id="deliveryCommunity_dialog_postcode" maxlength="30" />
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_city">城市*:</label>
        <div class="col-xs-12 col-sm-6">
          <%--<input type="text" name="city" id="deliveryCommunity_dialog_city" maxlength="30"  />--%>
          <select  name="city" id="deliveryCommunity_dialog_city"  >
            <c:forEach items="${cityList}" var="city"><option value="${city}">${city}</option></c:forEach>
          </select>
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_address">小区地址*:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="address" id="deliveryCommunity_dialog_address"/>
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_section">行政板块*:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="section" id="deliveryCommunity_dialog_section" maxlength="100" readonly />
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_area">行政区域*:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="area" id="deliveryCommunity_dialog_area" maxlength="30" readonly />
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_longitude">经度*:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="longitude" id="deliveryCommunity_dialog_longitude"  onclick="selectCommunityLngLat()"  readonly/>
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_latitude">纬度*:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="latitude" id="deliveryCommunity_dialog_latitude" onclick="selectCommunityLngLat()"  readonly/>
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_households">户数:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="households" id="deliveryCommunity_dialog_households" maxlength="100"/>
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_prices">房价:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="prices" id="deliveryCommunity_dialog_prices" maxlength="100"/>
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_modifyYears">年限:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="modifyYears" id="deliveryCommunity_dialog_modifyYears" maxlength="100"/>
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_elevatorFlag">电梯标志:</label>
        <div class="col-xs-12 col-sm-6">
          <select id="deliveryCommunity_dialog_elevatorFlag" name="elevatorFlag" style="width: 165px">
            <option value="">-请选择-</option>
            <option value="UNKNOWN">未知</option>
            <option value="NO_ELEVATOR">无电梯</option>
            <option value="ELEVATOR">有电梯</option>
          </select>
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_communityType">小区类型:</label>
        <div class="col-xs-12 col-sm-6">
          <select id="deliveryCommunity_dialog_communityType" name="communityType" style="width: 165px">
            <option value="">-请选择-</option>
            <option value="UNKNOWN">未知</option>
            <option value="LOW_DENSITY">低密度豪宅</option>
            <option value="HIGH_DENSITY">高密度成熟小区</option>
            <option value="APARTMENT_HOUSE">公寓/老公房</option>
          </select>
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_buildType">建筑类型:</label>
        <div class="col-xs-12 col-sm-6">
          <select id="deliveryCommunity_dialog_buildType" name="buildType" style="width: 165px">
            <option value="">-请选择-</option>
            <option value="COMMUNITY_HOUSE">小区住宅</option>
            <option value="NON_COMMUNITY_HOUSE">非小区住宅</option>
            <option value="BUSINESS">商业</option>
            <option value="SCHOOL">学校</option>
          </select>
        </div>
      </div>
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_remarks">备注:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" name="remarks" id="deliveryCommunity_dialog_remarks" maxlength="100"/>
        </div>
      </div>
    </div>
    <div class="row" style="height:40px;margin-top:10px;">
      <div class="col-sm-6">
        <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunity_dialog_remarks">板块:</label>
        <div class="col-xs-12 col-sm-6">
          <input type="text" id="deliveryCommunity_dialog_delivery_section_name" readonly/>
          <input type="hidden" name="deliverySectionId" id="deliveryCommunity_dialog_delivery_section_id" readonly/>
        </div>
      </div>
    </div>
    <div class="row" style="height:20px;margin-top:20px;">
      <label class="col-sm-2 control-label col-xs-12  no-padding-right" >用途/类型:</label>
      <div class="col-xs-12 col-sm-10">
        <input type="checkbox" name="funTypeArray" value="别墅"/>&nbsp;别墅&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="老公房"/>&nbsp;老公房&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="新里洋房"/>&nbsp;新里洋房&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="酒店公寓"/>&nbsp;酒店公寓&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="人才公寓"/>&nbsp;人才公寓&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="宿舍"/>&nbsp;宿舍&nbsp;&nbsp;
      </div>
    </div>
    <div class="row" style="height:20px;margin-top:5px;">
      <label class="col-sm-2 control-label col-xs-12  no-padding-right" ></label>
      <div class="col-xs-12 col-sm-10">
        <input type="checkbox" name="funTypeArray" value="棚户区"/>&nbsp;棚户区&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="写字楼"/>&nbsp;写字楼&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="厂房"/>&nbsp;厂房&nbsp;&nbsp;
        <input type="checkbox" name="funTypeArray" value="商场"/>&nbsp;商场&nbsp;&nbsp;
      </div>
    </div>
  </form>
</div>



<div id="community-lnglat-select-dialog" class="hide no-padding">
  <div style="margin-top: 10px;margin-left: 10px">
    城市:<input type="text" id="community-lnglat-select-city"/>
    地址:<input type="text" id="community-lnglat-select-address"/>
    <input type="hidden" id="community-lnglat-select-longitude"/>
    <input type="hidden" id="community-lnglat-select-latitude"/>
    <a href="#" style="margin-left: 15px;" height="40" class="btn btn-info btn-sm"
       onclick="searchCommunityLngLat()">查找</a>
  </div>
  <iframe id="map-community-lnglat-container" name="map-community-lnglat-container"
          src="${pageContext.request.contextPath}/assets/html/MapCommunitySendSectionPoint.html" scrolling="no"
          frameborder="0"
          style="width: 100%; height: 450px;margin-top: 10px;"></iframe>
</div>

<%@ include file="common/adminSectionDialog.jsp"%>
<%@ include file="common/sectionDialog.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/community/deliveryCommunity.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/adminSectionDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/sectionDialog.js"></script>
