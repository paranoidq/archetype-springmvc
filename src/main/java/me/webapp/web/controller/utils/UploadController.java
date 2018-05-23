package me.webapp.web.controller.utils;

import me.webapp.config.AppConfig;
import me.webapp.config.UploadConfig;
import me.webapp.exception.ErrorCode;
import me.webapp.open.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
@ResponseBody
@RequestMapping(path = "/upload")
public class UploadController {

    @Autowired
    private UploadConfig uploadConfig;

    /**
     * 上传文件base目录
     */
    private String baseFileFolder;
    /**
     * 上传图片base目录
     */
    private String baseImgFolder;


    @PostConstruct
    public void init() {
        this.baseFileFolder = uploadConfig.getUploadFileBaseFolder();
        this.baseImgFolder = uploadConfig.getUploadImgBaseFolder();
    }


    /**
     * 上传图片接口
     * @param uploadImg
     * @return
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public ApiResponse uploadImg(@RequestPart("upload") MultipartFile uploadImg) {
        // TODO
        String originalFileNme = uploadImg.getOriginalFilename();
        try {


            // ...
            String storePath = "";
            File f = new File(storePath);
            if (f.exists()) {
                //
            }
            uploadImg.transferTo(f);
        } catch (Throwable t) {
            return ApiResponse.createError(ErrorCode.UPLOAD_ERROR, "上传文件失败");
        }
        return ApiResponse.createOk("上传图片成功");
    }


    /**
     * 上传文件接口
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ApiResponse uploadFile(@RequestPart("upload") MultipartFile uploadFile) {
        // TODO
        return ApiResponse.createOk("上传文件成功");
    }

}
