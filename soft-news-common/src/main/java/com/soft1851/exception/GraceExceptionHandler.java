package com.soft1851.exception;

import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 缁熶竴寮傚父鎷︽埅澶勭悊
 * 鍙互閽堝寮傚父鐨勭被鍨嬭繘琛屾崟鑾凤紝鐒跺悗杩斿洖json淇℃伅鍒板墠绔�
 * @author: mqxu
 * @create: 2020-11-15
 */
@ControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    public GraceResult returnMyException(MyCustomException e) {
        e.printStackTrace();
        return GraceResult.exception(e.getResponseStatusEnum());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public GraceResult returnMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return GraceResult.errorCustom(ResponseStatusEnum.FILE_MAX_SIZE_ERROR);
    }

}