package com.generator.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 14:14
 * @Description:
 */
@Setter
@Getter
public class Field {
    private String name;// 字段名
    private String comment;// 注释
    private String type;// 数据类型
    private String bigName; // 首字母大写
    private Integer length; // 字符型的长度
}
