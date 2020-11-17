package com.soft1851.user.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.vo.UserAccountInfoVO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author Qin jian
 * @Date 2020/11/14
 * @Version 1.0
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController extends BaseController implements UserControllerApi {

    private final UserService userService;

    @Override
    public GraceResult getAllUsers() {
        return null;
    }

    @Override
    public GraceResult getUserInfo(String userId) {
        //0、判断不能为空
        if(StringUtils.isBlank(userId)){
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        //1、根据userId查询用户，调用内部封装方法
        AppUser user = getUser(userId);
        //2.设置VO——需要展示的信息
        UserAccountInfoVO accountInfoVO = new UserAccountInfoVO();
        //3、属性拷贝
        BeanUtils.copyProperties(user,accountInfoVO);
        return GraceResult.ok(accountInfoVO);
    }

    private AppUser getUser(String userId){
        return userService.getUser(userId);
    }
}
