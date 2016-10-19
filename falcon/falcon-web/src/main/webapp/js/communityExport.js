var communityExportMap = new BMap.Map('communitySection_map');
var locationPoint = null;
communityExportMap.centerAndZoom("上海");//默认是上海

initForbid();
initMapMenuContext();

/**
 *  初始化需要禁止的操作
 */
function initForbid() {
    if (communityExportMap == undefined || communityExportMap == null) {
        alert("地图没有初始化不能够初始化操作!");
        return;
    }
    communityExportMap.enableScrollWheelZoom();
    communityExportMap.disableDoubleClickZoom();
}
/**
 * 初始化需要右键绑定的操作
 */
function initMapMenuContext() {
    if (communityExportMap == undefined || communityExportMap == null) {
        alert("地图没有初始化不能够初始化右键菜单!");
        return;
    }
}

/**
 * 定位
 */
function locationPosition() {
    var cityName = $("select[name='city']").find("option:selected").val();
    if (cityName == null) {
        alert("请选择城市!");
        return;
    }
    communityExportMap.clearOverlays();
    var address = $("#communityExport-search-name").val();
    var realAddress = cityName + address;
    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上,并调整地图视野
    myGeo.getPoint(realAddress, function (point) {
        if (point) {
            locationPoint = point;
            var marker = new BMap.Marker(point);  // 创建中心点标注
            var label = new BMap.Label(realAddress, {offset: new BMap.Size(20, -10)}); //创建标签
            marker.setLabel(label);
            communityExportMap.centerAndZoom(point, 16);
            communityExportMap.addOverlay(marker);
        } else {
            alert("您选择地址没有解析到结果!");
        }
    }, cityName);
}

/**
 * 根据当前经纬度+范围查询小区
 */
function searchCommunity() {
    if (locationPoint == null) {
        alert("请选择定位的点!");
        return;
    }
    communityExportMap.clearOverlays();
    var distance = $("#ccommunityExport-radius-name").val();
    var longitude = locationPoint.lng;
    var latitude = locationPoint.lat;
    $.ajax({
        url: contextPath + '/communityExport/search',
        type: 'GET',
        data: {"distance": distance, "longitude": longitude, "latitude": latitude},
        success: function (data) {
            if (data == null || data.length == 0) {
                alert("查询不到该区域内的小区!");
            } else {
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    var point = new BMap.Point(data[i].longitude, data[i].latitude);
                    var marker = new BMap.Marker(point);  // 创建中心点标注
                    var label = new BMap.Label(data[i].communityName, {offset: new BMap.Size(20, -10)}); //创建标签
                    marker.setLabel(label);
                    communityExportMap.addOverlay(marker);
                }
            }
        }

    });
}
/**
 * 导出当前位置+范围的小区
 */
function exportCommunity() {
    if (locationPoint == null) {
        alert("请选择定位的点!");
        return;
    }
    var distance = $("#communityExport-radius-name").val();
    if (distance == null || distance == '') {
        alert("请选择范围!");
        return;
    }
    var longitude = locationPoint.lng;
    var latitude = locationPoint.lat;
    window.location.href = contextPath + "/communityExport/export?distance=" + distance + "&longitude=" + longitude + "&latitude=" + latitude;
}


function clearSearchCondition() {
    $("#communityExport-search-city option").eq(0).attr('selected', 'true');
    $("#communityExport-search-name").val("")
    $("#communityExport-area-name").val("");
}