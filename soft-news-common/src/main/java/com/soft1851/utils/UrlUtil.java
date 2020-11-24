package com.soft1851.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mqxu
 */
public class UrlUtil {

    /**
     * 楠岃瘉鏄惁鏄疷RL
     *
     * @param url
     * @return
     */
    public static boolean verifyUrl(String url) {

        // URL楠岃瘉瑙勫垯
        // String regEx ="[A-Za-z]+://[A-Za-z0-9-_]+\\\\.[A-Za-z0-9-_%&\\?\\/.=]+";
        String regEx = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        // 缂栬瘧姝ｅ垯琛ㄨ揪寮�
        Pattern pattern = Pattern.compile(regEx);
        // 蹇界暐澶у皬鍐欑殑鍐欐硶
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        // 瀛楃涓叉槸鍚︿笌姝ｅ垯琛ㄨ揪寮忕浉鍖归厤
        boolean rs = matcher.matches();
        return rs;

    }

    public static void main(String[] args) {
        boolean res = verifyUrl("http://qj.com:8005/doc.html");
        System.out.println(res);
    }
}