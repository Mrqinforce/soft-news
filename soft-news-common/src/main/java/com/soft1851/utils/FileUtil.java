package com.soft1851.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

/**
 * @description: 鏂囦欢宸ュ叿绫�
 * @author: mqxu
 * @create: 2020-11-15
 **/
public class FileUtil {

    /**
     * 鏂囦欢娴佷笅杞斤紝鍦ㄦ祻瑙堝櫒灞曠ず
     *
     * @param response 鍝嶅簲
     * @param file     鏂囦欢浠庣洏绗﹀紑濮嬬殑瀹屾暣璺緞
     */
    public static void downloadFileByStream(HttpServletResponse response, File file) {
        String filePath = file.getPath();
        System.out.println("filePath = " + filePath);
        // 瀵筫ncode杩囩殑filePath澶勭悊
        if (filePath.contains("%")) {
            try {
                filePath = URLDecoder.decode(filePath, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        ServletOutputStream out = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            String[] dir = filePath.split("/");
            String fileName = dir[dir.length - 1];
            String[] array = fileName.split("[.]");
            String fileType = array[array.length - 1].toLowerCase();
            // 鍒ゆ柇鍥剧墖绫诲瀷
            if ("jpg,jepg,gif,png".contains(fileType)) {
                response.setContentType("image/" + fileType);
                // 鍒ゆ柇pdf绫诲瀷
            } else if ("pdf".contains(fileType)) {
                response.setContentType("application/pdf");
            } else {
                // 璁剧疆multipart
                response.setContentType("multipart/form-data");
            }
            out = response.getOutputStream();
            // 璇诲彇鏂囦欢娴�
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 鏂囦欢杞崲涓篵ase64
     *
     * @param file 鍏ュ弬
     * @return String
     */
    public static String fileToBase64(File file) {
        //灏嗗浘鐗囨枃浠惰浆鍖栦负瀛楄妭鏁扮粍瀛楃涓诧紝骞跺鍏惰繘琛孊ase64缂栫爜澶勭悊
        InputStream in;
        byte[] fileData = null;
        // 璇诲彇鏂囦欢瀛楄妭鏁扮粍
        try {
            in = new FileInputStream(file);
            fileData = new byte[in.available()];
            in.read(fileData);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 瀵瑰瓧鑺傛暟缁凚ase64缂栫爜骞朵笖杩斿洖
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(fileData);
    }

}