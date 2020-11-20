package com.soft1851.api.controller.admin;

import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author qin jian
 * @Date 2020/11/20
 * @Version 1.0
 **/

@Api(value = "管理员维护",tags = {"管理员维护controller"})
@RequestMapping("adminMsg")
public interface AdminMsgControllerApi {

    /**
     * 管理员登录
     * @param request 请求
     * @param response 响应
     * @param adminLoginBO 管理员登录BO类
     * @throws Exception 异常
     */

    @PostMapping("/adminLogin")
    @ApiOperation(value = "管理员登录",notes = "管理员登录",httpMethod = "POST")
    GraceResult adminLogin(@RequestBody AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询管理员是否存在
     * @param username 管理员用户名
     * @throws GraceResult
     */

    @PostMapping("/adminIsExist")
    @ApiOperation(value = "查询管理员是否存在",notes = "查询管理员是否存在",httpMethod = "POST")
    GraceResult adminIsExist(@RequestParam String username);
}
