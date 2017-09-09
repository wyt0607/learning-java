package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WTON on 2017/5/21.
 */
public class CommonUtil {

    public static Boolean isEmpty(Object object) {
        return object != null && !"".equals(object) ? true : false;
    }

    public static Map entityToMap(Object object) {
        Class<?> c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        Map map = new HashMap();
        Arrays.asList(fields).forEach((field) -> {
            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Object fieldValue = c.getDeclaredMethod(methodName).invoke(object);
                map.put(fieldName, fieldValue);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return map;
    }
}
