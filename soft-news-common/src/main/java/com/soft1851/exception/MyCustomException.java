package com.soft1851.exception;

import com.soft1851.result.ResponseStatusEnum;

/**
 * 鑷畾涔夊紓甯�
 * 渚夸簬瑙ｈ€︼紝service涓巆ontroller閿欒鐨勮В鑰︼紝涓嶄細琚玸ervice杩斿洖鐨勭被鍨嬭€岄檺鍒�
 *
 * @author: mqxu
 * @create: 2020-11-15
 */
public class MyCustomException extends RuntimeException {

    private ResponseStatusEnum responseStatusEnum;

    public MyCustomException(ResponseStatusEnum responseStatusEnum) {
        super("寮傚父鐘舵€佺爜涓猴細" + responseStatusEnum.status()
                + "锛涘叿浣撳紓甯镐俊鎭负锛�" + responseStatusEnum.msg());
        this.responseStatusEnum = responseStatusEnum;
    }

    public ResponseStatusEnum getResponseStatusEnum() {
        return responseStatusEnum;
    }

    public void setResponseStatusEnum(ResponseStatusEnum responseStatusEnum) {
        this.responseStatusEnum = responseStatusEnum;
    }
}