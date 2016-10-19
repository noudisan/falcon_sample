$(function () {
    //页面加载成功之后查询覆盖物
    searchSection();
});

//var flag = false;  //定义全局的变量用来判断是否已经加载了覆盖
var sectionMap = new BMap.Map('communitySection_map');

sectionMap.centerAndZoom("上海");//默认是上海

initForbid();
initMapMenuContext();

var overlays = [];
var points = [];

sectionMap.addEventListener("zoomend", function (e) {
    var zoom = sectionMap.getZoom();
    var overlays_array = sectionMap.getOverlays();
    if (overlays_array == null || overlays_array.length == 0) {
        return;
    }
    var len = overlays_array.length;
    var polygonList = [];
    for (var i = 0; i < len; i++) {
        var overlayTemp = overlays_array[i];
        if (overlayTemp instanceof BMap.Polygon) {
            polygonList.push(overlayTemp);
        }
    }
});
var overlaycomplete = function (e) {
    overlays.push(e.overlay);
};

//定义绘制多边形
var polygoncomplete = function (polygon) {
    points = polygon.getPath();
    if(confirm("多边型板块绘制完成!是否保存?")){
        saveInfo(polygon);
    }else{
        cancelInfo(polygon);
    }

}


var styleOptions = {
    strokeColor: "red",    //边线颜色。
    fillColor: "none",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 1,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}
//实例化鼠标绘制工具
var drawingManager = new BMapLib.DrawingManager(sectionMap, {
    isOpen: false, //是否开启绘制模式
    enableDrawingTool: true, //是否显示工具栏
    drawingToolOptions: {
        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
        offset: new BMap.Size(1, 1), //偏离值
        scale: 0.5 //工具栏缩放比
    },
    //circleOptions: styleOptions, //圆的样式
    //polylineOptions: styleOptions, //线的样式
    polygonOptions: styleOptions //多边形的样式
    //rectangleOptions: styleOptions //矩形的样式
});
//添加鼠标绘制工具监听事件，用于获取绘制结果
drawingManager.addEventListener('overlaycomplete', overlaycomplete);
drawingManager.addEventListener('polygoncomplete', polygoncomplete);

/**
 *  初始化需要禁止的操作
 */
function initForbid() {
    if (sectionMap == undefined || sectionMap == null) {
        alert("地图没有初始化不能够初始化操作!");
        return;
    }
    sectionMap.enableScrollWheelZoom();
    sectionMap.disableDoubleClickZoom();
    //sectionMap.disableScrollWheelZoom();
}
/**
 * 初始化需要右键绑定的操作
 */
function initMapMenuContext() {
    if (sectionMap == undefined || sectionMap == null) {
        alert("地图没有初始化不能够初始化右键菜单!");
        return;
    }
    /*var menu = new BMap.ContextMenu();
    var txtMenuItem = [
        {
            text: '5',
            callback: function (e) {
                map.setZoom(5)
            }
        },
        {
            text: '16',
            callback: function (e) {
                map.setZoom(16)
            }
        }, {
            text: '19',
            callback: function (e) {
                map.setZoom(19)
            }
        }
    ];
    for (var i = 0; i < txtMenuItem.length; i++) {
        menu.addItem(new BMap.MenuItem(txtMenuItem[i].text, txtMenuItem[i].callback, 100));
    }
    map.addContextMenu(menu);*/
}


function inputArea(polygon) {
    $("#sectionDialog").find("input").each(function (index, element) {
        if (element.type != "checkbox") {
            $(this).val("");
        }
    });

    $("#sectionDialog").removeClass('hide').dialog({
        modal: true,
        width: 350,
        height:270,
        title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i> 保存设置区域参数</h4></div>",
        title_html: true,
        close:function(){
            cancelInfo(polygon);
        },
        buttons: [
            {
                text: "取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "保存",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    BlockWidget.block();

                    var jsonArrayFinal = JSON.stringify(points);
                    var bounds = polygon.getBounds();
                    var centerPoint = bounds.getCenter();
                    var json = {
                        "sectionName": $("#section_dialog_sectionName").val(),
                        "city": $("#section_dialog_city").val(),
                        "address": $("#section_dialog_address").val(),
                        "longitude": centerPoint.lng,
                        "latitude": centerPoint.lat,
                        "points": jsonArrayFinal
                    };
                    $.ajax({
                        method: 'post',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        //processData: false,
                        url: getValue('#contextPath') + "/deliverySection/save",
                        data: $.toJSON(json),
                        success: function (data) {
                            BlockWidget.unBlock();
                            if (data.code == 1) {
                                $("#sectionDialog").dialog("close");
                                //sectionMap.centerAndZoom(centerPoint, 15);
                                points = [];
                                if (data && data.data) {
                                    var section = data.data;
                                    var bMapList = [];
                                    var pointList = section.deliverySectionPoints;
                                    var len_t = pointList.length;
                                    for (var j = 0; j < len_t; j++) {
                                        bMapList.push(new BMap.Point(pointList[j].lng, pointList[j].lat));
                                    }
                                    addPolygon(bMapList, section);
                                }
                            }else{
                                alert(data.msg);
                            }
                        },
                        error: function (data, status) {
                            BlockWidget.unBlock();
                            $(this).dialog("close");
                            alert("服务器出错，请联系管理员");
                        }
                    });

                }
            }
        ]
    });
}

/**
 * 保存多边形
 * @param polygon
 */
function saveInfo(polygon) {
    if (points == null || points.length == 0) {
        return;
    }
    inputArea(polygon);
}

function addPolygon(bMapList, sendSection) {
    var sectionId= sendSection.id;
    var name = sendSection.sectionName;
    var longitude = sendSection.longitude;
    var latitude = sendSection.latitude;
    var address = sendSection.address;
    var point = new BMap.Point(longitude, latitude);

    var polygon = new BMap.Polygon(bMapList, {
        strokeColor: "blue",
        fillColor: "none",
        strokeWeight: 3,
        strokeOpacity: 0,
        fillOpacity: 0
    });

    var opts = {
        width: 200,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title: name, // 信息窗口标题
        enableMessage: true,//设置允许信息窗发送短息
        message: "小区板块详情信息"
    }
    var infoHtml="地址:" + address+"<br><br><button id=bindCommunity_"+name+">查看小区</button>" +
        "&nbsp;&nbsp;<button id=updatePolygon_"+name+">修改</button>&nbsp;&nbsp;<button id=deletePolygon_"+name+">删除</button>"
    var infoWindow = new BMap.InfoWindow(infoHtml, opts);  // 创建信息窗口对象
    var marker = new BMap.Marker(point);  // 创建中心点标注
    var label = new BMap.Label(name, {offset: new BMap.Size(20, -10)}); //创建标签
    marker.setLabel(label);
    sectionMap.addOverlay(marker);              // 将标注添加到地图中

    polygon.addEventListener("mouseover", function (e) {
        polygon.setStrokeColor("red");
    });
    polygon.addEventListener("mouseout", function (e) {
        polygon.setStrokeColor("blue");
    });

    marker.addEventListener("rightclick", function (e) {
        sectionMap.openInfoWindow(infoWindow, point);
        $("#bindCommunity_"+name).on("click",function(){
            getSectionCommunityList(sectionId,infoWindow);
        });
        $("#updatePolygon_"+name).on("click",function(){
            updateSection(sectionId,infoWindow,polygon,marker);
        });
        $("#deletePolygon_"+name).on("click",function(){
            var result =window.confirm("是否删除选中板块?");
            if(result){
                deletePolygon(polygon, sectionId, marker,infoWindow);
            }

        });
    });

    sectionMap.addOverlay(polygon);
}

/**
 * 取消
 * @param polygon
 */
function cancelInfo(polygon) {
    sectionMap.removeOverlay(polygon);
}

/**
 * 删除板块
 * @param polygon
 * @param sectionId
 * @param marker
 * @param infoWindow
 */
function deletePolygon(polygon, sectionId, marker,infoWindow) {
    $.ajax({
        url: getValue('#contextPath') +'/deliverySection/deleteSection',
        dataType: "json",
        method: 'post',
        data: {"sectionId": sectionId},
        success: function (data) {
            infoWindow.close();
            sectionMap.removeOverlay(polygon);
            sectionMap.removeOverlay(marker);
        },
        error: function (data) {
            alert("删除区域失败!错误原因是" + data);
        }
    });
}



function updateSection(sectionId,_infoWindow,_polygon,_marker){
    _infoWindow.close();
    $("#sectionDialog").find("input").each(function (index, element) {
        if (element.type != "checkbox") {
            $(this).val("");
        }
    });
    $.ajax({
        method: 'get',
        dataType: "json",
        async:false,
        url: getValue('#contextPath') + "/deliverySection/get?sectionId="+sectionId,
        success: function (data) {
            if(data && data.code ==1){
                    var section= data.data;
                    $("#section_dialog_sectionId").val(section.id);
                    $("#section_dialog_sectionName").val(section.sectionName);
                    $("#section_dialog_city").val(section.city);
                    $("#section_dialog_address").val(section.address);
            }else{
                alert("板块为空，请查询后重新操作");
            }
        },
        error: function (data, status) {
            alert("服务器出错，请联系管理员");
        }
    });

    $("#sectionDialog").removeClass('hide').dialog({
        modal: true,
        width: 350,
        height:270,
        title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i> 保存设置区域参数</h4></div>",
        title_html: true,
        buttons: [
            {
                text: "取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "保存",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    BlockWidget.block();
                    $.ajax({
                        method: 'post',
                        dataType: "json",
                        url: getValue('#contextPath') + "/deliverySection/update",
                        data:{
                            "id": $("#section_dialog_sectionId").val(),
                            "sectionName": $("#section_dialog_sectionName").val(),
                            "city": $("#section_dialog_city").val(),
                            "address": $("#section_dialog_address").val()
                        },
                        success: function (data) {
                            BlockWidget.unBlock();
                            if(data && data.data){
                                sectionMap.removeOverlay(_polygon);
                                sectionMap.removeOverlay(_marker);

                                $("#sectionDialog").dialog("close");
                                var section = data.data;
                                var bMapList = [];
                                var pointList = section.deliverySectionPoints;
                                var len_t = pointList.length;
                                for (var j = 0; j < len_t; j++) {
                                    bMapList.push(new BMap.Point(pointList[j].lng, pointList[j].lat));
                                }
                                addPolygon(bMapList, section);
                            }else{
                                alert(data.msg);
                            }
                        },
                        error: function (data, status) {
                            BlockWidget.unBlock();
                            alert("服务器出错，请联系管理员");
                        }
                    });

                }
            }
        ]
    });

}

/**
 * 获取小区的板块信息
 * @param name
 */
function getSectionCommunityList(sectionId,infoWindow) {
    infoWindow.close();
    $.ajax({
        url: getValue('#contextPath') +'/deliverySection/getSectionCommunityList',
        dataType: 'json',
        method: 'post',
        data: {
            "sectionId": sectionId
        },
        success: function (data) {
            if (data && data.data) {
                $("#section_community_List").val("");
                $("#section_community_List").val(data.data);
                $("#section_community_dialog").removeClass('hide').dialog({
                    resizable: false,
                    modal: true,
                    width: 550,
                    height:370,
                    title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\">" +
                    "   <i class=\"icon-ok\"></i>小区列表</h4></div>",
                    title_html: true,
                    buttons: [
                        {
                            text: "关闭",
                            "class": "btn btn-primary btn-xs save-order-dialog",
                            click: function () {
                                $(this).dialog("close");
                            }
                        }
                    ]
                });

            }else{
                alert("该板块内无小区")
            }
        },
        error: function (data) {
            alert("服务器出错，请联系管理员");
        }
    });
}



function searchSection(){
    sectionMap.clearOverlays();
    $.ajax({
        url: getValue('#contextPath') + "/deliverySection/queryListSectionByCity",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        method: 'get',
        data: {"cityName": getValue("#communitySection-search-city"),
            "sectionName": getValue("#communitySection-search-name")},
        success: function (data) {
            if (data && data.data) {
                if(getValue("#communitySection-search-city")){
                    sectionMap.centerAndZoom(getValue("#communitySection-search-city"));
                }
                var sectionList = data.data;
                var len = sectionList.length;
                for (var i = 0; i < len; i++) {
                    var bMapList = [];
                    var pointList = sectionList[i].deliverySectionPoints;
                    var len_t = pointList.length;
                    for (var j = 0; j < len_t; j++) {
                        bMapList.push(new BMap.Point(pointList[j].lng, pointList[j].lat));
                    }
                    addPolygon(bMapList, sectionList[i]);
                }
            }
        },
        error: function (data, status) {
            alert("请求出错了,错误原因是" + status);
        }
    });
}

function clearSearchCondition(){
    $("#communitySection-search-city option").eq(0).attr('selected', 'true');
    $("#communitySection-search-name").val("")
}