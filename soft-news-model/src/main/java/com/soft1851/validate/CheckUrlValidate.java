package com.soft1851.validate;

import com.soft1851.utils.UrlUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author Qin jian
 * @Date 2020/11/24
 * @Version 1.0
 **/
public class CheckUrlValidate implements ConstraintValidator<com.soft1851.validate.CheckUrl, String> {

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return UrlUtil.verifyUrl(url.trim());
    }
}
