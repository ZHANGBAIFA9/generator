package com.generator.dao;

import com.generator.pojo.CodeCond;
import com.generator.pojo.Field;
import com.generator.pojo.Table;
import com.generator.utils.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 14:33
 * @Description:
 */
@Slf4j
@Repository
public class CodeDao extends BaseDao {
    /** @功能描述 查询表名列表 */
    public List<Table> queryTables(CodeCond cond) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT table_name t_name,if(table_comment='',table_name,table_comment) comment");
        sb.append(" FROM information_schema.tables WHERE table_schema=(SELECT DATABASE())");
        log.info(sb.toString());
        return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Table.class));
    }

    /** @功能描述 查询字段名列表 */
    public List<Field> queryFields(CodeCond cond) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COLUMN_NAME NAME,");
        sb.append(" CASE WHEN COLUMN_COMMENT = '' THEN COLUMN_NAME ELSE COLUMN_COMMENT END COMMENT,");
        sb.append(" CASE WHEN DATA_TYPE='varchar' OR DATA_TYPE='text' OR DATA_TYPE='char' OR DATA_TYPE='longtext' OR DATA_TYPE='mediumtext' THEN 'String'");
        sb.append(" WHEN DATA_TYPE = 'tinyint' THEN 'Byte'");
        sb.append(" WHEN DATA_TYPE = 'smallint' THEN 'Short'");
        sb.append(" WHEN DATA_TYPE = 'int' OR DATA_TYPE = 'mediumint' THEN 'Integer'");
        sb.append(" WHEN DATA_TYPE = 'datetime' OR DATA_TYPE = 'timestamp' OR DATA_TYPE = 'date' OR DATA_TYPE = 'time' THEN 'Date'");
        sb.append(" WHEN DATA_TYPE = 'bigint' THEN 'Long'");
        sb.append(" WHEN DATA_TYPE = 'float' THEN 'Float'");
        sb.append(" WHEN DATA_TYPE = 'double' THEN 'Double'");
        sb.append(" WHEN DATA_TYPE = 'decimal' THEN 'BigDecimal'");
        sb.append(" WHEN DATA_TYPE = 'boolean' OR DATA_TYPE = 'bit' THEN 'Boolean'");
        sb.append(" ELSE CONCAT ('DATA TYPE IS ERROR', DATA_TYPE) END type,CHARACTER_MAXIMUM_LENGTH length");
        sb.append(" FROM INFORMATION_SCHEMA.COLUMNS WHERE table_schema=(SELECT DATABASE())");
        sb.append(cond.and());
        sb.append(" AND  COLUMN_NAME not in ('id','created_by','created_time','updated_by','updated_time')");
        sb.append(" ORDER BY ORDINAL_POSITION");
        log.info(super.sql(sb.toString(), cond.array()));
        return jdbcTemplate.query(sb.toString(), cond.array(), new BeanPropertyRowMapper<>(Field.class));
    }
}
