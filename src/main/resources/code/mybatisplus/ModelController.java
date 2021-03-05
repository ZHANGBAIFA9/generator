package ${pName};

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yidu.tdpt.common.core.entity.vo.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @类说明 【${cName}】控制器
 * @author ${auth}
 * @date ${time}
 **/
@Api(tags = "${cName}接口")
@RestController
@RequestMapping("${low}")
public class ${upp}Controller {

	@Autowired
	private I${upp}Service ${lowUpp}Service;//注入【${cName}】业务逻辑接口
	
	/**
	 * @方法说明  新增【${cName}】记录
	 */
	@PostMapping("add")
	@ApiOperation(value = "新增【${cName}】记录")
	@ApiImplicitParam(name = "${lowUpp}Form", value = "新增【${cName}】form表单", required = true, dataType = "{upp}Form")
	 public Result<Boolean> add(@RequestBody @Valid ${upp}Form ${lowUpp}Form) {
 		return Result.success(${lowUpp}Service.add(${lowUpp}Form.toPo(${upp}.class)));
	}
	
	/**
	 * @方法说明 按主键删除【${cName}】记录
	 */
	
	@ApiOperation(value = "按主键删除【${cName}】记录")
	@ApiImplicitParam(paramType = "path", name = "id", value = "${cName}ID", required = true, dataType = "string")
	@DeleteMapping(value = "/{id}")
	public Result<Boolean> delete(@PathVariable String id) {
		return Result.success(${lowUpp}Service.delete(id));
	}
	
	/**
	 * @方法说明 修改【${cName}】记录
	 */
	
	@ApiOperation(value = "修改【${cName}】记录")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "${cName}ID", required = true, dataType = "string"),
        @ApiImplicitParam(name = "${lowUpp}Form", value = "${cName}实体", required = true, dataType = "{upp}Form")
	})
	@PutMapping(value = "/{id}")
	public Result<Boolean> update(@PathVariable String id, @Valid @RequestBody ${upp}Form ${lowUpp}Form) {
		${upp} ${lowUpp} = ${lowUpp}Form.toPo(Group.${upp});
		${lowUpp}.setId(id);
		return Result.success(${lowUpp}Service.update(${lowUpp}));
	}
	
	
	
	/**
	 * @方法说明 按主键查单个【${cName}】记录
	 */
	@ApiOperation(value = "按主键查单个【${cName}】记录")
	@ApiImplicitParam(paramType = "path", name = "id", value = "${cName}ID", required = true, dataType = "string")
	@GetMapping(value = "/{id}")
	public Result<${upp}> get(Integer id) {
		return Result.success(${lowUpp}Service.get(id));
	}
}
