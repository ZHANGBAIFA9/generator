package ${pName};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
 
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * @类说明 【${cName}】数据访问层
 * @author ${auth}
 * @date ${time}
 **/
@Mapper
@Repository
public interface ${upp}Mapper extends BaseMapper<${upp}> {
	
//	/**
//	 * @方法说明 自义联表查询
//	 **/
//	IPage<${upp}> queryPage(IPage<${upp}> page, ${upp} param);
}
