package com.generator.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 14:19
 * @Description:
 */
@Data
@AllArgsConstructor
public class Page<T> {
    private List<T> dataList; /* 数据列表 */
    private int pageSize = 10; /* 页大小(每页记录条) */
    private long rowCount;/* 记录总数 */
    private int curpage = 0;/* 当前页码 */
    private int pageCount;/* 当前页码 */
}
