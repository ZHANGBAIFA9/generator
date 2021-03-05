package com.generator.controller;

import com.generator.pojo.CodeCond;
import com.generator.pojo.Field;
import com.generator.pojo.Table;
import com.generator.service.CodeService;
import com.generator.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 17:42
 * @Description:
 */
@RestController
@RequestMapping("/code")
public class CodeAction {
    @Autowired
    private CodeService service;// 生成器业务罗辑接口

    /**
     * @功能描述 查询数据库中表名列表
     */
    @PostMapping("/queryList")
    public List<Table> queryList(@RequestBody CodeCond cond) {
        return service.queryTables(cond);
    }

    /**
     * @功能描述 查询数据库中表名列表
     */
    @PostMapping("/queryField")
    public List<Field> queryField(@RequestBody CodeCond cond) {
        return service.queryFields(cond);
    }

    /**
     * @功能描述 生成代码
     */
    @PostMapping("/create")
    public void create(@RequestBody CodeCond cond) {
        Utils.delDir(new File(Utils.path() + "com/"));
        service.create(cond);
        Utils.chmod();
    }

    /**
     * @功能描述 下载代码
     */
    @GetMapping("/downCode")
    public void downCode(HttpServletResponse response) throws IOException {
        String fileName = "code.zip";
        Utils.createZip(Utils.path() + "com", Utils.path() + fileName);
        Path path = Paths.get(Utils.path() + fileName);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Content-Length", "" + Files.size(path));
        response.setContentType("application/zip");
        OutputStream out = response.getOutputStream();
        out.write(Files.readAllBytes(path));
        out.flush();
        out.close();
    }
}
