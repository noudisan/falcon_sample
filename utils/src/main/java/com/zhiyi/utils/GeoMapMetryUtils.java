package com.zhiyi.utils;

import com.jillesvangurp.geo.GeoGeometry;

/**
 * GEO 地图的工具类
 */
public class GeoMapMetryUtils {

	/**
	 *
	 * @param latitude  纬度
	 * @param longitude 经度
	 * @param polygonPoints
	 * @return
	 */
	public static boolean isContainsPoints(double latitude, double longitude, double[] ... polygonPoints){
		return GeoGeometry.polygonContains(latitude, longitude, polygonPoints);
	}

}
