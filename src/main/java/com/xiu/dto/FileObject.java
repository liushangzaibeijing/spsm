package com.xiu.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileObject
 * @Desc 文件信息实体对象
 * @Author xieqx
 * @Date 2020/11/30 16:05
 **/
public class FileObject {

    private MultipartFile fileInfo;

    private String fileName;

    private String fileDesc;

    public MultipartFile getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(MultipartFile fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }
}
