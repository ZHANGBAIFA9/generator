package ${pName};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * @类说明 【${cName}】业务逻辑实现
 * @author ${auth}
 * @date ${time}
 **/
//@Slf4j
@Service
public class ${upp}ServiceImpl extends ServiceImpl<I${upp}Mapper, ${upp}> implements I${upp}Service {
//	@Autowired
//	private I${upp}Mapper ${lowUpp}Mapper;//注入【${cName}】数据访问接口
//	
//	/**
//	 * @方法说明 自定义联表查询
//	 **/
//	@Override
//	public IPage<${upp}> queryPage(IPage<${upp}> page, ${upp} param){
//		return ${lowUpp}Mapper.queryPage(page,param);
//	}
	@Override
    public boolean add(${upp} ${lowUpp}) {
        return this.save(${lowUpp});
    }

    @Override
    @Cached(name = "${lowUpp}::", key = "#id", cacheType = CacheType.BOTH)
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @Cached(name = "${lowUpp}::", key = "#${lowUpp}.id", cacheType = CacheType.BOTH)
    public boolean update(${upp} ${lowUpp}) {
        return this.updateById(${lowUpp});
    }

    @Override
    @Cached(name = "${lowUpp}::", key = "#id", cacheType = CacheType.BOTH)
    public ${upp} get(String id) {
        return this.getById(id);
    }
	
}