package com.soft1851.api.controller.files;

import com.soft1851.pojo.bo.NewAdminBO;
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
 * @author Qin Jian
 * @description TODO
 * @Data 2020/11/19
 */
@Api(value = "文件相关上传Controller",tags = {"文件相关上传Controller"})
@RequestMapping("fs")
public interface FileUploadControllerApi {

    /**
     * 上传用户头像
     * @param userId 用户id
     * @param file 文件对象
     * @return 封装结果
     * @throws Exception 异常
     */
    @ApiOperation(value = "上传用户头像",notes = "上传用户头像",httpMethod = "POST")
    @PostMapping("/uploadFace")
    GraceResult uploadFace(@RequestParam String userId, MultipartFile file) throws Exception;

    /**
     * 上传多个文件
     *
     * @param  userId 用户id
     * @param files 文件数组
     * @return 返回
     * @throws Exception 异常
     */
    @PostMapping("/uploadSomeFiles")
    GraceResult uploadSomeFiles(@RequestParam String userId,
                                MultipartFile[] files) throws Exception;
    /**
     * 文件上传到MongoDB的GridFs中
     *
     * @param newAdminBO 入参
     * @param request 请求
     * @param response 响应
     * @return 返回
     * @throws Exception 异常
     */
    @ApiOperation(value = "文件上传到MongoDB的GridFs中",notes = "文件上传到MongoDB的GridFs中",httpMethod = "POST")
    @PostMapping("/uploadToGridFs")
    GraceResult uploadToGridFs(@RequestBody NewAdminBO newAdminBO,
                               HttpServletRequest request, HttpServletResponse response) throws Exception;


}