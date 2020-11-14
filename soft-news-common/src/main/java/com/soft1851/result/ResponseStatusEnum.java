package com.soft1851.result;

/**
 * @description: 鍝嶅簲杩斿洖鏋氫妇
 * @author: mqxu
 * @create: 2020-11-14 11:22
 **/
public enum ResponseStatusEnum {
    /**
     * 鎴愬姛鎴栧け璐�
     */
    SUCCESS(200, true, "操作成功"),
    FAILED(500, false, "鎿嶄綔澶辫触锛�"),

    /**
     * 50x
     */
    UN_LOGIN(501, false, "璇风櫥褰曞悗鍐嶇户缁搷浣滐紒"),
    TICKET_INVALID(502, false, "浼氳瘽澶辨晥锛岃閲嶆柊鐧诲綍锛�"),
    NO_AUTH(503, false, "鎮ㄧ殑鏉冮檺涓嶈冻锛屾棤娉曠户缁搷浣滐紒"),
    MOBILE_ERROR(504, false, "鐭俊鍙戦�佸け璐ワ紝璇风◢鍚庨噸璇曪紒"),
    SMS_NEED_WAIT_ERROR(505, false, "鐭俊鍙戦�佸お蹇暒~璇风◢鍚庡啀璇曪紒"),
    SMS_CODE_ERROR(506, false, "楠岃瘉鐮佽繃鏈熸垨涓嶅尮閰嶏紝璇风◢鍚庡啀璇曪紒"),
    USER_FROZEN(507, false, "鐢ㄦ埛宸茶鍐荤粨锛岃鑱旂郴绠＄悊鍛橈紒"),
    USER_UPDATE_ERROR(508, false, "鐢ㄦ埛淇℃伅鏇存柊澶辫触锛岃鑱旂郴绠＄悊鍛橈紒"),
    USER_INACTIVE_ERROR(509, false, "璇峰墠寰�[璐﹀彿璁剧疆]淇敼淇℃伅婵�娲诲悗鍐嶈繘琛屽悗缁搷浣滐紒"),
    FILE_UPLOAD_NULL_ERROR(510, false, "鏂囦欢涓嶈兘涓虹┖锛岃閫夋嫨涓�涓枃浠跺啀涓婁紶锛�"),
    FILE_UPLOAD_FAILD(511, false, "鏂囦欢涓婁紶澶辫触锛�"),
    FILE_FORMATTER_FAILD(512, false, "鏂囦欢鍥剧墖鏍煎紡涓嶆敮鎸侊紒"),
    FILE_MAX_SIZE_ERROR(513, false, "浠呮敮鎸�500kb澶у皬浠ヤ笅鐨勫浘鐗囦笂浼狅紒"),
    FILE_NOT_EXIST_ERROR(514, false, "浣犳墍鏌ョ湅鐨勬枃浠朵笉瀛樺湪锛�"),
    USER_STATUS_ERROR(515, false, "鐢ㄦ埛鐘舵�佸弬鏁板嚭閿欙紒"),
    USER_NOT_EXIST_ERROR(516, false, "鐢ㄦ埛涓嶅瓨鍦紒"),

    /**
     * 鑷畾涔夌郴缁熺骇鍒紓甯� 54x
     */
    SYSTEM_INDEX_OUT_OF_BOUNDS(541, false, "绯荤粺閿欒锛屾暟缁勮秺鐣岋紒"),
    SYSTEM_ARITHMETIC_BY_ZERO(542, false, "绯荤粺閿欒锛屾棤娉曢櫎闆讹紒"),
    SYSTEM_NULL_POINTER(543, false, "绯荤粺閿欒锛岀┖鎸囬拡锛�"),
    SYSTEM_NUMBER_FORMAT(544, false, "绯荤粺閿欒锛屾暟瀛楄浆鎹㈠紓甯革紒"),
    SYSTEM_PARSE(545, false, "绯荤粺閿欒锛岃В鏋愬紓甯革紒"),
    SYSTEM_IO(546, false, "绯荤粺閿欒锛孖O杈撳叆杈撳嚭寮傚父锛�"),
    SYSTEM_FILE_NOT_FOUND(547, false, "绯荤粺閿欒锛屾枃浠舵湭鎵惧埌锛�"),
    SYSTEM_CLASS_CAST(548, false, "绯荤粺閿欒锛岀被鍨嬪己鍒惰浆鎹㈤敊璇紒"),
    SYSTEM_PARSER_ERROR(549, false, "绯荤粺閿欒锛岃В鏋愬嚭閿欙紒"),
    SYSTEM_DATE_PARSER_ERROR(550, false, "绯荤粺閿欒锛屾棩鏈熻В鏋愬嚭閿欙紒"),

    /**
     * admin 绠＄悊绯荤粺 56x
     */
    ADMIN_USERNAME_NULL_ERROR(561, false, "绠＄悊鍛樼櫥褰曞悕涓嶈兘涓虹┖锛�"),
    ADMIN_USERNAME_EXIST_ERROR(562, false, "绠＄悊鍛樼櫥褰曞悕宸插瓨鍦紒"),
    ADMIN_NAME_NULL_ERROR(563, false, "绠＄悊鍛樿礋璐ｄ汉涓嶈兘涓虹┖锛�"),
    ADMIN_PASSWORD_ERROR(564, false, "瀵嗙爜涓嶈兘涓虹┖鍚庤�呬袱娆¤緭鍏ヤ笉涓�鑷达紒"),
    ADMIN_CREATE_ERROR(565, false, "娣诲姞绠＄悊鍛樺け璐ワ紒"),
    ADMIN_PASSWORD_NULL_ERROR(566, false, "瀵嗙爜涓嶈兘涓虹┖锛�"),
    ADMIN_NOT_EXIT_ERROR(567, false, "绠＄悊鍛樹笉瀛樺湪鎴栧瘑鐮侀敊璇紒"),
    ADMIN_FACE_NULL_ERROR(568, false, "浜鸿劯淇℃伅涓嶈兘涓虹┖锛�"),
    ADMIN_FACE_LOGIN_ERROR(569, false, "浜鸿劯璇嗗埆澶辫触锛岃閲嶈瘯锛�"),
    CATEGORY_EXIST_ERROR(570, false, "鏂囩珷鍒嗙被宸插瓨鍦紝璇锋崲涓�涓垎绫诲悕锛�"),

    /**
     * 濯掍綋涓績 鐩稿叧閿欒 58x
     */
    ARTICLE_COVER_NOT_EXIST_ERROR(580, false, "鏂囩珷灏侀潰涓嶅瓨鍦紝璇烽�夋嫨涓�涓紒"),
    ARTICLE_CATEGORY_NOT_EXIST_ERROR(581, false, "璇烽�夋嫨姝ｇ‘鐨勬枃绔犻鍩燂紒"),
    ARTICLE_CREATE_ERROR(582, false, "鍒涘缓鏂囩珷澶辫触锛岃閲嶈瘯鎴栬仈绯荤鐞嗗憳锛�"),
    ARTICLE_QUERY_PARAMS_ERROR(583, false, "鏂囩珷鍒楄〃鏌ヨ鍙傛暟閿欒锛�"),
    ARTICLE_DELETE_ERROR(584, false, "鏂囩珷鍒犻櫎澶辫触锛�"),
    ARTICLE_WITHDRAW_ERROR(585, false, "鏂囩珷鎾ゅ洖澶辫触锛�"),
    ARTICLE_REVIEW_ERROR(585, false, "鏂囩珷瀹℃牳鍑洪敊锛�"),
    ARTICLE_ALREADY_READ_ERROR(586, false, "鏂囩珷閲嶅闃呰锛�"),

    /**
     * 浜鸿劯璇嗗埆閿欒浠ｇ爜
     */
    FACE_VERIFY_TYPE_ERROR(600, false, "浜鸿劯姣斿楠岃瘉绫诲瀷涓嶆纭紒"),
    FACE_VERIFY_LOGIN_ERROR(601, false, "浜鸿劯鐧诲綍澶辫触锛�"),

    /**
     * 绯荤粺閿欒锛屾湭棰勬湡鐨勯敊璇� 555
     */
    SYSTEM_ERROR(555, false, "绯荤粺绻佸繖锛岃绋嶅悗鍐嶈瘯锛�"),
    SYSTEM_OPERATION_ERROR(556, false, "鎿嶄綔澶辫触锛岃閲嶈瘯鎴栬仈绯荤鐞嗗憳"),
    SYSTEM_RESPONSE_NO_INFO(557, false, "");


    /**
     * 鍝嶅簲涓氬姟鐘舵��
     */
    private final Integer status;
    /**
     * 璋冪敤鏄惁鎴愬姛
     */
    private final Boolean success;
    /**
     * 鍝嶅簲娑堟伅锛屽彲浠ヤ负鎴愬姛鎴栬�呭け璐ョ殑娑堟伅
     */
    private final String msg;

    ResponseStatusEnum(Integer status, Boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Integer status() {
        return status;
    }

    public Boolean success() {
        return success;
    }

    public String msg() {
        return msg;
    }
}
