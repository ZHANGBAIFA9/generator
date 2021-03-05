package com.generator.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 14:16
 * @Description:
 */
@Setter
@Getter
public class Table {
    private String t_name;// 表名
    private String comment;// 表名注释
    private String cls_upp;// 类名(首字母大写)
    private String c_name;// 类中文名
}
