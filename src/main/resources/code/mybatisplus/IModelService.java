package ${pName};

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @类说明 【${cName}】业务逻辑接口
 * @author ${auth}
 * @date ${time}
 **/
public interface I${upp}Service extends IService<${upp}> {
	
	/**
     * 获取${cName}
     *
     * @param id
     * @return
     */
	${upp} get(String id);

    /**
     * 新增${cName}
     *
     * @param ${lowUpp}
     * @return
     */
    boolean add(${upp} ${lowUpp});

    /**
     * 更新资源信息
     *
     * @param ${lowUpp}
     */
    boolean update(${upp} ${lowUpp});

    /**
     * 根据id删除资源
     *
     * @param id
     */
    boolean delete(String id);

}
