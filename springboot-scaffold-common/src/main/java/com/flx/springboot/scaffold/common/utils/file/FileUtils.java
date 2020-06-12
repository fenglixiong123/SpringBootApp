package com.flx.springboot.scaffold.common.utils.file;

import java.io.File;

/**
 * @Author: Fenglixiong
 * @Date: 2020/6/10 12:06
 * @Description:
 */
public class FileUtils {

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static boolean delete(String path){
        File file = new File(path);
        if(file.exists()){
            if(file.delete()){
                return true;
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过后缀获取类型
     * @param suffix
     * @return
     */
    public static String getContentTypeBySuffix(String suffix){
        String result;
        if(".xls".equalsIgnoreCase(suffix) || ".xlsx".equalsIgnoreCase(suffix)){
            result = "application/vnd.ms-excel";
        }else if(".doc".equalsIgnoreCase(suffix) || ".docx".equalsIgnoreCase(suffix)){
            result = "application/vnd.msword";
        }else if(".exe".equalsIgnoreCase(suffix)){
            result = "application/octet-stream";
        }else if(".jpe".equalsIgnoreCase(suffix) || ".jpeg".equalsIgnoreCase(suffix) || ".jpg".equalsIgnoreCase(suffix)){
            result = "image/jpeg";
        }else if(".png".equalsIgnoreCase(suffix)){
            result = "image/png";
        }else if(".gif".equalsIgnoreCase(suffix)){
            result = "image/gif";
        }else if(".mp3".equalsIgnoreCase(suffix)){
            result = "audio/x-mpeg";
        }else if(".mp4".equalsIgnoreCase(suffix) || ".mpg4".equalsIgnoreCase(suffix)){
            result = "video/mp4";
        }else if(".wm".equalsIgnoreCase(suffix)){
            result = "video/x-ms-wm";
        }else if(".wmv".equalsIgnoreCase(suffix)){
            result = "audio/x-ms-wmv";
        }else if(".rm".equalsIgnoreCase(suffix) || ".rmvb".equalsIgnoreCase(suffix)){
            result = "audio/x-pn-realaudio";
        }else if(".xml".equalsIgnoreCase(suffix)){
            result = "text/xml";
        }else if(".gz".equalsIgnoreCase(suffix)){
            result = "application/x-gzip";
        }else if(".gtar".equalsIgnoreCase(suffix)){
            result = "application/x-gtar";
        }else if(".tar".equalsIgnoreCase(suffix) || ".taz".equalsIgnoreCase(suffix)){
            result = "application/x-tar";
        }else if(".rar".equalsIgnoreCase(suffix)){
            result = "application/x-rar-compressed";
        }else if(".zip".equalsIgnoreCase(suffix)){
            result = "application/zip";
        }else{
            result = "multipart/form-data";
        }
        return result;
    }

}
