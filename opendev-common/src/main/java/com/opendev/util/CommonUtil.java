package com.opendev.util;

import java.util.List;
import java.util.Map;

/**
 * 公共工具类
 * @author hungkuei
 */
public class CommonUtil {

    /**
     * 是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || str.isEmpty() || str.replaceAll(" ", "").isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            if (isBlank(strs[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法描述 如果对象为空返回 true 否则返回false
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        if(obj != null) {
            return false;
        }
        return true;
    }

    /**
     * 方法描述 判断Map集合是null或者空 返回true 否则返回false
     * @param map
     * @return
     */
    public static boolean isNull(Map<Object, Object> map){
        if(map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 将List转数组
     * @param list
     * @return
     */
    public static String[] listToArray(List<String> list) {
        String[] strs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strs[i] = list.get(i);
        }
        return strs;
    }

    /**
     * 将数组转字符串
     * @param objs
     * @return
     */
    public static String[] arrayToString(Object[] objs) {
        String[] strs = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            strs[i] = String.valueOf(objs[i]);
        }
        return strs;
    }

}

