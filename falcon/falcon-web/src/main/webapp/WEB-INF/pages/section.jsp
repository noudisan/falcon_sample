<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PUyKqeTm00DFdjuRCfVgCgdb"></script>
<!--加载鼠标绘制工具-->
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css"/>
<!--加载检索信息窗口-->
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery.json.min.js"></script>

<body>

<div class="page-header">
    <h1>
        板块管理
    </h1>
</div>
<form id="communitySection-search-form" style="margin-top: 15px;">
    <div class="row" style="height:40px;">
        <div class="col-sm-2">
            <label class="col-sm-6 control-label  no-padding-right">所属城市:</label>
            <select name="city" id="communitySection-search-city">
                <c:forEach items="${cityList}" var="city">
                    <option value="${city}">${city}</option>
                </c:forEach>
            </select>
            <%--<input id="communitySection-search-city" name="city" class="col-sm-6" type="text"/>--%>
        </div>
        <div class="col-sm-2">
            <label class="col-sm-6 control-label  no-padding-right">板块名称:</label>
            <input id="communitySection-search-name" name="name" class="col-sm-6" type="text"/>
        </div>

        <div class="col-sm-3" style="width:80px;">
            <button id="communitySection-search-query-button" style="width:80px;" class="btn btn-warning btn-sm"
                    type="button" onclick="searchSection()" />
            <i class="icon-edit"></i>查询
            </button>
        </div>

        <div class="col-sm-3" style="width:80px;margin-left: 10px;">
            <button id="communitySection-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm"
                    type="button" onclick="clearSearchCondition()"/>
            <i class="icon-edit"></i>清空
            </button>
        </div>
        <div class="col-sm-5" style="margin-left: 10px;">
            <label style="color: #d3d3d3">选择多边形添加板块，右键操作</label>
        </div>
    </div>
</form>
<!-- 地图显示区域 -->
<div class="row" style="margin-top: 25px;">
    <div class="col-xs-12 col-sm-12 widget-container-span">
            <div class="widget-body">
                <div class="widget-main">
                    <div id="allMap" style="overflow:hidden;zoom:1;position:relative;width: 100%">
                        <div id="communitySection_map" style="height:100%;width: 100%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
                    </div>
                </div>
            </div>
    </div>
</div>

<!-- 对象保存的弹出层 -->
<div id='sectionDialog' class='hide' style="width: auto;">
    <form class="form-horizontal" role="form">
        <input type="hidden" id="section_dialog_sectionId">
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="section_dialog_sectionName">名称：</label>
            <div class="col-sm-10">
                <input type="text" style="width: 220px;" id="section_dialog_sectionName" name="sectionName" placeholder="板块名称" />
            </div>
        </div>
        <div class="space-2"></div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="section_dialog_city">城市：</label>
            <div class="col-sm-10">
                <%--<input type="text" style="width: 220px;" id="section_dialog_city" name="city" placeholder="城市" />--%>
                <select style="width: 220px;" id="section_dialog_city" name="city">
                    <c:forEach items="${baseCityList}" var="city">
                        <option value="${city}">${city}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="space-2"></div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="section_dialog_address">地址：</label>
            <div class="col-sm-10">
                <input type="text" style="width: 220px;" id="section_dialog_address" name="address" placeholder="地址" />
            </div>
        </div>
    </form>
</div>

<!-- 对象保存的弹出层 -->
<div id='section_community_dialog' class='hide' style="width: auto;">
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="section_dialog_sectionName">板块列表</label>
            <div class="col-sm-10">
                <textarea id="section_community_List" readonly style="margin: 0px; height: 229px; width: 406px;"></textarea>
            </div>
        </div>
    </form>
</div>


<div id="dialog-confirm" class="hide">
    <div class="alert alert-info bigger-110">
        区域板块删除
    </div>
    <div class="space-6"></div>
    <p class="bigger-110 bolder center grey">
        <i class="icon-hand-right blue bigger-120"></i>
        你确定要删除该条区域板块信息么?
    </p>
</div>
</body>
<!-- #dialog-confirm aaa-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliverySection.js"></script>
