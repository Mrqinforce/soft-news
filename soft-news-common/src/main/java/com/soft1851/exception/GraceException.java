package com.soft1851.exception;

import com.soft1851.result.ResponseStatusEnum;

/**
 * @description: 浼橀泤鐨勭粺涓€澶勭悊寮傚父
 * @author: mqxu
 * @create: 2020-11-15
 **/
public class GraceException {

    public static void display(ResponseStatusEnum responseStatusEnum) {
        throw new MyCustomException(responseStatusEnum);
    }

}