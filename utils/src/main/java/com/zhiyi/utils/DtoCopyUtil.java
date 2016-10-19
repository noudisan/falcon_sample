package com.zhiyi.utils;

import java.util.List;

/**
 * Created by hrs on 2014/7/10.
 */
public class DtoCopyUtil {

    public static <S, T> List<S> copyListProperties(Class<S> clazz, List<T> itemList) {
        return PropertiesUtils.copyList(clazz,itemList);
    }

    public static <S, T> S copyProperties(Class<S> clazz, T item) {
        return PropertiesUtils.copy(clazz,item);
    }
}
