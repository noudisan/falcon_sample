<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PUyKqeTm00DFdjuRCfVgCgdb"></script>
<!--加载鼠标绘制工具-->
<script type="text/javascript"
        src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css"/>
<!--加载检索信息窗口-->
<script type="text/javascript"
        src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery.json.min.js"></script>

<body>

<div class="page-header">
    <h1>
        小区附近查询
    </h1>
</div>
<form id="communityNearby-search-form" style="margin-top: 15px;">
    <div class="row" style="height:40px;">
        <div class="col-sm-3">
            <label class="col-sm-6 control-label  no-padding-right">所属城市:</label>
            <select name="city" id="communityNearby-search-city">
                <option value="0">请选择城市</option>
                <c:forEach items="${cityList}" var="city">
                    <option value="${city}">${city}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-sm-2">
            <label class="col-sm-6 control-label  no-padding-right">具体地址:</label>
            <input id="communityNearby-search-name" name="address" class="col-sm-6" type="text"/>
        </div>

        <div class="col-sm-2">
            <label class="col-sm-6 control-label  no-padding-right">关键字:</label>
            <input id="communityNearby-keyword-name" name="keyword" class="col-sm-6" type="text"/>
        </div>

        <div class="col-sm-2">
            <label class="col-sm-6 control-label  no-padding-right">范围(米):</label>
            <input id="communityNearby-area-name" name="keyword" class="col-sm-6" type="text"/>
        </div>

        <div class="col-sm-3">
            <label class="col-sm-6 control-label  no-padding-right">页数(从1开始):</label>
            <input id="communityNearby-pageNum-name" name="keyword" class="col-sm-2" type="text" value="1"/>
        </div>
    </div>

    <div class="row" style="height:40px;">
        <div class="col-sm-3" style="width:80px;margin-left: 10px;">
            <button id="communityNearby-position-button" style="width:80px;" class="btn btn-warning btn-sm"
                    type="button" onclick="locationPosition()"/>
            <i class="icon-edit"></i>定位
            </button>
        </div>

        <div class="col-sm-3" style="width:80px;margin-left: 10px;">
            <button id="communityNearby-search-button" style="width:80px;" class="btn btn-warning btn-sm"
                    type="button" onclick="search()"/>
            <i class="icon-edit"></i>查询
            </button>
        </div>

        <div class="col-sm-3" style="width:80px;margin-left: 10px;">
            <button id="communityNearby-export-button" style="width:80px;" class="btn btn-warning btn-sm"
                    type="button" onclick="exportCommunityNearby()"/>
            <i class="icon-edit"></i>导出
            </button>
        </div>

        <div class="col-sm-3" style="width:80px;margin-left: 10px;">
            <button id="communitySection-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm"
                    type="button" onclick="clearSearchCondition()"/>
            <i class="icon-edit"></i>清空
            </button>
        </div>
    </div>
</form>
<!-- 地图显示区域 -->
<div class="row" style="margin-top: 25px;">
    <div class="col-xs-12 col-sm-12 widget-container-span">
        <div class="widget-body">
            <div class="widget-main">
                <div id="allMap" style="overflow:hidden;zoom:1;position:relative;width: 100%">
                    <div id="communitySection_map"
                         style="height:100%;width: 100%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- #dialog-confirm aaa-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/communityNearby.js"></script>
