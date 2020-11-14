package com.soft1851.result;

import java.util.Map;

/**
 * @description: 鑷畾涔変紭闆呯殑鍝嶅簲鏁版嵁缁撴瀯
 * @author: mqxu
 * @create: 2020-11-14
 **/
public class GraceResult {

    /**
     * 鍝嶅簲涓氬姟鐘舵€佺爜
     */
    private Integer status;

    /**
     * 鍝嶅簲娑堟伅
     */
    private String msg;

    /**
     * 鏄惁鎴愬姛
     */
    private Boolean success;

    /**
     * 鍝嶅簲鏁版嵁锛屽彲浠ユ槸Object锛屼篃鍙互鏄疞ist鎴朚ap绛�
     */
    private Object data;

    /**
     * 鎴愬姛杩斿洖甯︽湁鏁版嵁鐨勭粨鏋�
     *
     * @param data@� 鍏ュ弬
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult ok(Object data) {
        return new GraceResult(data);
    }

    /**
     * 鎴愬姛杩斿洖锛屼笉甯︽湁鏁版嵁鐨勶紝鐩存帴璋冪敤ok鏂规硶锛宒ata鏃犻』浼犲叆
     *
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult ok() {
        return new GraceResult(ResponseStatusEnum.SUCCESS);
    }

    public GraceResult(Object data) {
        this.status = ResponseStatusEnum.SUCCESS.status();
        this.msg = ResponseStatusEnum.SUCCESS.msg();
        this.success = ResponseStatusEnum.SUCCESS.success();
        this.data = data;
    }


    /**
     * 閿欒杩斿洖锛岀洿鎺ヨ皟鐢╡rror鏂规硶鍗冲彲锛屽綋鐒朵篃鍙互鍦≧esponseStatusEnum涓嚜瀹氫箟閿欒鍚庡啀杩斿洖涔熼兘鍙互
     *
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult error() {
        return new GraceResult(ResponseStatusEnum.FAILED);
    }

    /**
     * 閿欒杩斿洖锛宮ap涓寘鍚簡澶氭潯閿欒淇℃伅锛屽彲浠ョ敤浜庤〃鍗曢獙璇侊紝鎶婇敊璇粺涓€鐨勭粨鏋滆繑鍥炲嚭鍘�
     *
     * @param map锛� 澶氭潯閿欒淇℃伅绛塵ap
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult errorMap(Map map) {
        return new GraceResult(ResponseStatusEnum.FAILED, map);
    }

    /**
     * 閿欒杩斿洖锛岀洿鎺ヨ繑鍥為敊璇殑娑堟伅
     *
     * @param msg锛� 閿欒娑堟伅
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult errorMsg(String msg) {
        return new GraceResult(ResponseStatusEnum.FAILED, msg);
    }

    /**
     * 閿欒杩斿洖锛宼oken寮傚父锛屼竴浜涢€氱敤鐨勫彲浠ュ湪杩欓噷缁熶竴瀹氫箟
     *
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult errorTicket() {
        return new GraceResult(ResponseStatusEnum.TICKET_INVALID);
    }

    /**
     * 鑷畾涔夐敊璇寖鍥达紝闇€瑕佷紶鍏ヤ竴涓嚜瀹氫箟鐨勬灇涓撅紝鍙互鍒癧ResponseStatusEnum.java涓嚜瀹氫箟鍚庡啀浼犲叆
     *
     * @param responseStatus锛� 鍝嶅簲鏋氫妇
     * @return GraceResult: 杩斿洖缁撴灉
     */
    public static GraceResult errorCustom(ResponseStatusEnum responseStatus) {
        return new GraceResult(responseStatus);
    }

    public static GraceResult exception(ResponseStatusEnum responseStatus) {
        return new GraceResult(responseStatus);
    }

    public GraceResult(ResponseStatusEnum responseStatus) {
        this.status = responseStatus.status();
        this.msg = responseStatus.msg();
        this.success = responseStatus.success();
    }

    public GraceResult(ResponseStatusEnum responseStatus, Object data) {
        this.status = responseStatus.status();
        this.msg = responseStatus.msg();
        this.success = responseStatus.success();
        this.data = data;
    }

    public GraceResult(ResponseStatusEnum responseStatus, String msg) {
        this.status = responseStatus.status();
        this.msg = msg;
        this.success = responseStatus.success();
    }

    public GraceResult() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}