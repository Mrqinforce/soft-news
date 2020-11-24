package com.soft1851.api.controller.admin;

import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.pojo.bo.SaveFriendLinkBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author Qin jian
 * @Date 2020/11/24
 * @Version 1.0
 **/
@Api(value = "首页友情链接维护",tags = {"首页友情链接维护controller"})
@RequestMapping("friendLink")
public interface FriendLinkControllerApi {
    /**
     * 新增或者修改友情链接
     * @param saveFriendLinkBO 入参
     * @param result 校验结果
     * @return GraceResult
     */
    @PostMapping("/saveOrUpdateFriendLink")
    @ApiOperation(value = "新增或者修改友情链接",notes = "新增或者修改友情链接",httpMethod = "POST")
    GraceResult saveOrUpdateFriendLink(@RequestBody @Valid SaveFriendLinkBO saveFriendLinkBO, BindingResult result);

    /**
     * 查询友情链接列表
     * @return GraceResult
     */
    @PostMapping("getFriendLinkList")
    @ApiOperation(value = "查询友情链接列表", notes = "查询友情链接列表", httpMethod = "POST")
    GraceResult getFriendLinkList();

    /**
     * 删除友情链接
     * @param linkId 友链id
     * @return GraceResult
     */
    @PostMapping("delete")
    @ApiOperation(value = "删除友情链接", notes = "删除友情链接", httpMethod = "POST")
    GraceResult delete(@RequestParam String linkId);
}
