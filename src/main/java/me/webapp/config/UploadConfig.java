package me.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/upload.properties")
public class UploadConfig {

    /**
     * 图片上传base目录
     */
    @Value("${webapp.upload.img.baseFolder}")
    private String uploadImgBaseFolder;

    /**
     * 文件上传base目录
     */
    @Value("${webapp.upload.file.baseFolder}")
    private String uploadFileBaseFolder;


    public String getUploadImgBaseFolder() {
        return uploadImgBaseFolder;
    }

    public String getUploadFileBaseFolder() {
        return uploadFileBaseFolder;
    }
}
