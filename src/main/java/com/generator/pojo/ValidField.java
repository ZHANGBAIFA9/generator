package com.generator.pojo;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 14:16
 * @Description:
 */
public class ValidField {
    private static final Map<String, String> map = new HashMap<>();

    ValidField() {
        map.put("String", "\t//@Length(max = #) //字段长度");
        map.put("Long", "\t//@Max(9223372036854775807L) // bigint");
        map.put("Integer", "\t//@Max(2147483647) // int");
        map.put("Byte", "\t//@Max(127) // tinyint");
        map.put("Short", "\t//@Max(32767) // smallint");
    }

    public static String getValid(Field field) {
        String name = field.getName();
        String value = map.get(name);
        if (!StringUtils.isEmpty(value))
            return name.equals("String") ? value.replace("#", field.getLength().toString()) : value;
        return "";
    }
}
