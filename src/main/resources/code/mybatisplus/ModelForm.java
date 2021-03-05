package ${pName};
<#list importList as item>${item}</#list>

import com.yidu.tdpt.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @类说明 【${cName}】DTO对象
 * @author ${auth}
 * @date ${time}
 **/
@ApiModel
@Data
public class ${upp}Form extends BaseForm<${upp}>  {
	<#list fList as fi>
	/* -${fi.comment} */
	@ApiModelProperty(value = "${upp}")
	private ${fi.type} ${fi.name};
	</#list>
}