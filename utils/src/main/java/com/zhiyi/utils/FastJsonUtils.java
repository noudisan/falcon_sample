package com.zhiyi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * fastJson 工具类
 */
public class FastJsonUtils {
	private static Logger log = Logger.getLogger(FastJsonUtils.class);

	/**
	 * fastJson数据解析
	 *
	 * @param jsonStr json字符串
	 * @param t       类型
	 * @return
	 */
	public static <T> T getData(String jsonStr, Class<T> t) {
		T instance = null;
		try {
			instance = JSON.parseObject(jsonStr, t);
		} catch (Exception e) {
			log.error("解析数据出现异常,错误原因是" + e.getMessage());
		}
		return instance;
	}

	public static String parseData(Object obj) {
		String result = null;
		try {
			result = JSON.toJSONString(obj);
		} catch (Exception e) {
			log.error("转化数据出现异常,错误原因是" + e.getMessage());
		}
		return result;
	}
}
