package com.soft1851.enums;

import java.awt.color.ICC_ColorSpace;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author Qin jian
 * @Date 2020/11/16
 * @Version 1.0
 **/
public enum  UserStatus {
    INACTIVE(0,"未激活"),
    ACTIVE(1,"已激活"),
    FROZEN(2,"已冻结");
    public final Integer type;
    public final String value;
    UserStatus(Integer type,String value){
        this.type = type;
        this.value = value;
    }

    public static boolean isUserStatusValid(Integer tempStatus) {
        if (tempStatus != null){
            if (tempStatus == INACTIVE.type || tempStatus == ACTIVE.type || tempStatus == FROZEN.type){
                return true;
            }
        }
        return false;
    }
}
