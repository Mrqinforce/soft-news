package com.soft1851.files.controller;

import com.mongodb.client.gridfs.GridFSBucket;
import com.soft1851.api.controller.files.FileUploadControllerApi;
import com.soft1851.files.resource.FileResource;
import com.soft1851.files.service.UploadService;
import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.extend.AliImageReviewUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qin Jian
 * @description TODO
 * @Data 2020/11/19
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileUploadController implements FileUploadControllerApi {

    private final UploadService uploadService;
    private final FileResource fileResource;
    private final AliImageReviewUtil aliImageReviewUtil;
    private final GridFSBucket gridFSBucket;


    @Override
    public GraceResult uploadFace(String userId, MultipartFile file) throws Exception {
        String path;
        if(file != null){
            //获得文件上传名称
            String fileName = file.getOriginalFilename();
            //判断文件名不能为空
            if(StringUtils.isNotBlank(fileName)){
                // 分割命名
                String[] fileNameArr = fileName.split("\\.");
                //获得后端
                String suffix = fileNameArr[fileNameArr.length-1];
                //判断后缀是否符合我们的预定义范围
                if(!"png".equalsIgnoreCase(suffix)&&!"jpg".equalsIgnoreCase(suffix)&&
                        !"jpeg".equalsIgnoreCase(suffix)){
                    return GraceResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_NULL_ERROR);
                }
                //执行上传服务，得到回调路径
                path = uploadService.uploadFdfs(file,suffix);
            }else {
                return GraceResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_NULL_ERROR);
            }
            log.info("path = "+path);
            String finalPath;
            if(StringUtils.isNotBlank(path)){
                finalPath = fileResource.getHost()+path;
            }else {
                return GraceResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
            }
            return GraceResult.ok(doAliimageReview(finalPath));
        }
        return null;
    }





    /**
     * 检测不通过的默认图片
     */
    public static final String FAILED_IMAGE_URL = "https://sillyforce.oss-cn-beijing.aliyuncs.com/soft-news/201119BXRRMTGN54.jpg";

    /**
     * 阿里云图片智能检测
     *
     * @param pendingImageUrl 图片路径
     * @return 返回结果
     */
    private String doAliimageReview(String pendingImageUrl) {
        log.info(pendingImageUrl);
        boolean result = false;
        try {
            result = aliImageReviewUtil.reviewImage(pendingImageUrl);
        } catch (Exception e) {
            System.err.println("图片识别出错");
        }
        if (!result) {
            return  FAILED_IMAGE_URL;
        }
        return pendingImageUrl;
    }

    @Override
    public GraceResult uploadSomeFiles(String userId, MultipartFile[] files) throws Exception {
       // 声明list，用于存放多个图片的地址路径，返回到前端
        List<String> imageUrlList = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String path;
                if (file != null) {
                    //获得文件上传的名称
                    String fileName = file.getOriginalFilename();
                    //判断文件名不能为空
                    if (StringUtils.isNoneBlank(fileName)) {
                        String[] fileNameArr = fileName.split("\\.");
                        //获得后缀
                        String suffix = fileNameArr[fileNameArr.length - 1];
                        //判断后缀符合我们的预定义规范
                        if (!"png".equalsIgnoreCase(suffix) && !"jpg".equalsIgnoreCase(suffix) &&
                                !"jpeg".equalsIgnoreCase(suffix)) {
                            continue;
                        }
                        //执行上传服务，得到回调路径
                        path = uploadService.uploadOSS(file, userId, suffix);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
                String finalPath;
                if (StringUtils.isNoneBlank(path)) {
                    finalPath = fileResource.getOssHost() + path;
                    // TOOD: 2020/11/19 : 后缀需要对图片做一次审核
                    imageUrlList.add(finalPath);
                }
            }
        }
            return GraceResult.ok(imageUrlList);
        }

    @Override
    public GraceResult uploadToGridFs(NewAdminBO newAdminBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // base64字符串
        String file64 = newAdminBO.getImg64();
        // 将字符串转换为byte数组
        byte[] bytes = new BASE64Decoder().decodeBuffer(file64.trim());
        // 转换为输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        // 上传
        ObjectId fileId = gridFSBucket.uploadFromStream(newAdminBO.getUsername() + ".jpg", inputStream);
        System.out.println("上传完成。文件ID：" + fileId);
        // 文件在mongodb中的id
        String fileIdStr = fileId.toString();
        System.out.println("fileIdStr=" + fileIdStr);
        return GraceResult.ok(fileIdStr);
    }


}
