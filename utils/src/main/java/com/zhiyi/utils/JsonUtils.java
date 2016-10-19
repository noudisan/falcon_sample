package com.zhiyi.utils;

import net.sf.json.JSONObject;

/**
 * json 工具
 *  <p>普通类型、List、Collection等都是用JSONArray解析 
 *  Map、自定义类型是用JSONObject解析 
 *  </p>
 */  
public class JsonUtils {  
      
	/**
	 * beanToJson
	 * @param <T>
	 */
    public static <T> String beanToJson(T bean) {
    	if (null == bean) return "";
        JSONObject jsonObject = JSONObject.fromObject(bean);  
        return jsonObject.toString(); 
    }

} 
