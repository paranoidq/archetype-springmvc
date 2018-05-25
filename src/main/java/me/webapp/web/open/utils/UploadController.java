package me.webapp.web.open.utils;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

import me.webapp.config.UploadConfig;
import me.webapp.web.common.ApiErrorCode;
import me.webapp.web.common.ApiResponse;
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
            return ApiResponse.createError(ApiErrorCode.UPLOAD_ERROR, "上传文件失败");
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
