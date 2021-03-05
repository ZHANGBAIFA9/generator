package com.generator.service;

import com.generator.dao.CodeDao;
import com.generator.pojo.CodeCond;
import com.generator.pojo.Field;
import com.generator.pojo.Table;
import com.generator.utils.FreemarkerUtils;
import com.generator.utils.Utils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 14:31
 * @Description:
 */
@Service
public class CodeService {

        @Autowired
        private CodeDao dao;

        @Autowired
        private FreemarkerUtils utils;

        /**
         * @功能描述 生成代码
         */
        public void create(CodeCond cond) {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String auth = cond.getAuth();// 作者

            for (Table table : cond.getC_list()) {
                cond.setT_name_eq(table.getT_name());// 表名
                List<Field> fList = this.queryFields(cond);// 字段列表
                String upp = table.getCls_upp();// 驼峰类名(首字母大写)
                String low = upp.toLowerCase();// 类名小写(只用包路径)
                Map<String, Object> params = Maps.newHashMap();
                params.put("fList", fList);
                params.put("auth", auth);
                params.put("cName", table.getC_name());// 表注释中文名
                params.put("upp", upp);
                params.put("lowUpp", Utils.firstLower(upp));// 驼峰变量类名(首字母小写)
                params.put("low", low);
                params.put("idType", Utils.keyType(fList));// 主键数据类型
                params.put("table", table.getT_name());
                params.put("id", fList.get(0));
                params.put("cond", cond);
                params.put("tName", table.getT_name());
                params.put("idName", fList.get(0).getName());
                params.put("time", date);
                params.put("swagger", cond.getSwagger());
                List<String> importList = Lists.newArrayList();
                importList.add(Utils.dateImport(fList));
                importList.add(Utils.bigImport(fList));
                params.put("importList", importList);
                params.put("dollar", "$");
                params.put("well", "#");

                params.put("insertFields", Utils.addFieldsWrap(fList, "", ",", "\");\r\n\t\tsql.append(\"", 1));
                params.put("insertValues", Utils.addFieldsWrap(fList, "", "?,", "", 0));
                params.put("insertParams", Utils.addFieldsWrap(fList, " vo.get", "(),", "//\r\n\t\t\t\t",2));

                params.put("updateFields", Utils.addFieldsWrap(fList, "", "=?,", "\");\r\n\t\tsql.append(\"", 1));
                params.put("updateValues", Utils.addUpdateField(fList, " vo.get", "(),", "//\r\n\t\t\t\t",2));

                params.put("selectFields", Utils.addFieldsWrap(fList, "t.", ",", "\");\r\n\t\tsql.append(\"", 1));
                params.put("insertParamsBatch", Utils.addFieldsWrap(fList, ":", ",", "\");\r\n\t\tsql.append(\"", 1));

                params.put("insertFieldsMybatis", Utils.addFieldsWrap(fList, "", ",", "\r\n\t\t\t", 1));
                params.put("selectFieldsMybatis", Utils.addFieldsWrap(fList, "t.", ",", "\r\n\t\t\t", 1));
                params.put("insertValuesMybatis", Utils.addFieldsWrap(fList, "#{", "},", "\r\n\t\t\t", 1));

                params.put("model", cond.getModel());
                utils.getTemplates().forEach(item -> {
                    String[] split = item.split("/");
                    params.put("pName", cond.pack(split[0], low));
                    params.put("path", cond.path(split[0], low));
                    utils.process(item, params);
                });

            }

        }

        /**
         * @功能描述 查询数据库中表名列表
         */
        public List<Table> queryTables(CodeCond para) {
            List<Table> list = dao.queryTables(para);
//		log.info(list.toString());
            list.forEach(item -> {
                item.setCls_upp(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, item.getT_name()));
                item.setC_name(item.getComment());
            });
            return list;
        }

        /**
         * @功能描述 查询数据库中字段名列表
         */
        public List<Field> queryFields(CodeCond cond) {
            List<Field> list = dao.queryFields(cond);
            list.forEach(item -> item.setBigName(Utils.firstUpper(item.getName())));
            return list;
        }

}
