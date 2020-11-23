package com.soft1851.utils;

import com.aliyuncs.utils.Base64Helper;
import com.soft1851.enums.FaceVerifyType;
import com.soft1851.exception.GraceException;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.extend.AliyunResource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;

/**
 * @author mqxu
 */
@Component
public class FaceVerifyUtil {

    final static Logger logger = LoggerFactory.getLogger(FaceVerifyUtil.class);

    @Autowired
    private AliyunResource aliyunResource;

    private static final String gateway = "https://dtplus-cn-shanghai.data.aliyuncs.com/face/verify";

    /**
     * 璁＄畻MD5+BASE64
     *
     * @param s
     * @return
     */
    public static String MD5Base64(String s) {
        if (s == null) {
            return null;
        }
        String encodeStr = "";
        byte[] utfBytes = s.getBytes();
        MessageDigest mdTemp;
        try {
            mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(utfBytes);
            byte[] md5Bytes = mdTemp.digest();
            Base64Helper b64Encoder = new Base64Helper();
            encodeStr = b64Encoder.encode(md5Bytes);
        } catch (Exception e) {
            throw new Error("Failed to generate MD5 : " + e.getMessage());
        }
        return encodeStr;
    }

    /**
     * 璁＄畻 HMAC-SHA1
     *
     * @param data 鍏ュ弬
     * @param key  鍏ュ弬
     * @return String
     */
    public static String HMACSha1(String data, String key) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = (new Base64Helper()).encode(rawHmac);
        } catch (Exception e) {
            throw new Error("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    /**
     *  绛夊悓浜巎avaScript涓殑 new Date().toUTCString();
     * @param date 鍏ュ弬
     * @return String
     */
    public static String toGMTString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    /**
     * 鍙戦€丳OST璇锋眰 杩涜涓ゅ紶鍥剧殑浜鸿劯瀵规瘮
     *
     * @param type  0: 閫氳繃url璇嗗埆锛屽弬鏁癷mage_url涓嶄负绌猴紱1: 閫氳繃鍥剧墖content璇嗗埆锛屽弬鏁癱ontent涓嶄负绌�
     * @param face1 type涓�0锛屽垯浼犲叆鍥剧墖url锛屼负1鍒欎紶鍏ase64
     * @param face2 type涓�0锛屽垯浼犲叆鍥剧墖url锛屼负1鍒欎紶鍏ase64
     * @return
     */
    //濡傛灉鍙戦€佺殑鏄浆鎹负base64缂栫爜鍚庡悗闈㈠姞璇锋眰鍙傛暟type涓�1锛屽鏋滆姹傜殑鏄浘鐗囩殑url鍒欎笉鐢ㄥ姞type鍙傛暟銆�
    public String sendPostVerifyFace(int type, String face1, String face2) throws Exception {
        String body = "";
        if (type == FaceVerifyType.BASE64.type) {
            body = "{\"content_1\": \"" + face1 + "\", \"content_2\":\"" + face2 + "\", \"type\":\"" + type + "\"}";
        } else if (type == FaceVerifyType.IMAGE_URL.type) {
            body = "{\"image_url_1\": \"" + face1 + "\", \"image_url_2\":\"" + face2 + "\", \"type\":\"" + type + "\"}";
        } else {
            GraceException.display(ResponseStatusEnum.FACE_VERIFY_TYPE_ERROR);
        }
//        String body = "{\"content_1\": \"" + face1 + "\", \"content_2\":\"" + face2 + "\", \"type\":\"" + "1" + "\"}";
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        int statusCode = 200;
        try {
            URL realUrl = new URL(gateway);
            /*
             * http header 鍙傛暟
             */
            String method = "POST";
            // 杩斿洖鍊肩被鍨�
            String accept = "application/json";
            // 璇锋眰鍐呭绫诲瀷
            String content_type = "application/json";
            String path = realUrl.getFile();
            // GMT鏃堕棿
            String date = toGMTString(new Date());
            // 1.瀵筨ody鍋歁D5+BASE64鍔犲瘑
            String bodyMd5 = MD5Base64(body);
            String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
                    + path;
            // 2.璁＄畻 HMAC-SHA1
            String signature = HMACSha1(stringToSign, aliyunResource.getAccessKeySecret());
            // 3.寰楀埌 authorization header
            String authHeader = "Dataplus " + aliyunResource.getAccessKeyId() + ":" + signature;
            // 鎵撳紑鍜孶RL涔嬮棿鐨勮繛鎺�
            URLConnection conn = realUrl.openConnection();
            // 璁剧疆閫氱敤鐨勮姹傚睘鎬�
            conn.setRequestProperty("Accept", accept);
            conn.setRequestProperty("Content-type", content_type);
            conn.setRequestProperty("Date", date);
            // 璁よ瘉淇℃伅
            conn.setRequestProperty("Authorization", authHeader);
            // 鍙戦€丳OST璇锋眰蹇呴』璁剧疆濡備笅涓よ
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
            out = new PrintWriter(conn.getOutputStream());
            // 鍙戦€佽姹傚弬鏁�
            out.print(body);
            // flush杈撳嚭娴佺殑缂撳啿
            out.flush();
            // 瀹氫箟BufferedReader杈撳叆娴佹潵璇诲彇URL鐨勫搷搴�
            statusCode = ((HttpURLConnection) conn).getResponseCode();
            if (statusCode != 200) {
                in = new BufferedReader(new InputStreamReader(((HttpURLConnection) conn).getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (statusCode != 200) {
            throw new IOException("\nHttp StatusCode: " + statusCode + "\nErrorMessage: " + result);
        }
        return result;
    }

    /**
     * @param type
     * @param face1
     * @param face2
     * @param targetConfidence 鐩爣鍙俊搴︼紝鑷畾涔夐槇鍊�
     * @return
     */
    public boolean faceVerify(int type, String face1, String face2, double targetConfidence) {

        String response = null;
        try {
            response = sendPostVerifyFace(type, face1, face2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> map = JsonUtil.jsonToPojo(response, Map.class);
        Object confidenceStr = map.get("confidence");
        Double responseConfidence = (Double) confidenceStr;

        logger.info("浜鸿劯瀵规瘮缁撴灉锛歿}", responseConfidence);

//        System.out.println(response.toString());
//        System.out.println(map.toString());

        if (responseConfidence > targetConfidence) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 灏嗗浘鐗囪浆鎹负Base64
     * 灏哹ase64缂栫爜瀛楃涓茶В鐮佹垚img鍥剧墖
     *
     * @param imgUrl
     * @return
     */
    public String getImgBase64(String imgUrl) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 鍒涘缓URL
            URL url = new URL(imgUrl);
            byte[] by = new byte[1024];
            // 鍒涘缓閾炬帴
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 灏嗗唴瀹规斁鍒板唴瀛樹腑
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 瀵瑰瓧鑺傛暟缁凚ase64缂栫爜
        return Base64.encodeBase64String(data.toByteArray());
    }

//    public static void main(String[] args) {
//        String face3 = "http://122.152.205.72:88/group1/M00/00/05/CpoxxF5MvvGAfnLXAAIHiv37wNk363.jpg";
//        String face4 = "http://122.152.205.72:88/group1/M00/00/05/CpoxxF5Mv3yAH74mAACOiTd9pO4462.jpg";
//
//        boolean result = new FaceVerifyUtils().faceVerify(FaceVerifyType.IMAGE_URL.type, face3, face4, 60);
//
//        logger.info("浜鸿劯瀵规瘮鏄惁鎴愬姛锛歿}", result);
//    }

}
