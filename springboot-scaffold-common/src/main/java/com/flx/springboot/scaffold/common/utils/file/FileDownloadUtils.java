package com.flx.springboot.scaffold.common.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @Author: Fenglixiong
 * @Date: 2020/6/11 20:11
 * @Description:
 */
@Slf4j
public class FileDownloadUtils {

    /**
     * 下载文件
     * @param response
     * @param path
     * @param fileName
     */
    public static void download(HttpServletResponse response,String path,String fileName){
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            File file = new File(path+"/"+fileName);
            if(!file.exists()) {
                throw new Exception("file not exist !");
            }
            response.setContentType("application/force-download;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
//                response.setContentType("multipart/form-data;charset=UTF-8");
            response.addHeader("Content-Disposition", String.format("attachment;fileName=%s", fileName));
            byte[] buffer = new byte[1024];
            bis = new BufferedInputStream(new FileInputStream(file));
            os = response.getOutputStream();
            String suffix = fileName.substring(fileName.lastIndexOf("."));

            int count;
            while ((count=bis.read(buffer))!=-1){
                os.write(buffer,0,count);
            }
            os.flush();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bis != null) {

                    bis.close();
                }
                if (os != null) {
                    os.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件并且追加到文件后面
     * @param file
     * @param pathName
     * @return
     */
    public static boolean uploadAndAppend(MultipartFile file,String pathName){
        InputStreamReader is = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            File writeFile = new File(pathName);
            if (!writeFile.exists()) {
                boolean result = writeFile.createNewFile();
                if(!result){
                    return false;
                }
            }
            StringBuilder sb = new StringBuilder();
            //read file
            is = new InputStreamReader(file.getInputStream(), Charset.forName("UTF-8"));
            br=new BufferedReader(is);
            //write file
            fw = new FileWriter(writeFile.getAbsoluteFile(),true);
            bw = new BufferedWriter(fw);
            String temp;
            while ((temp = br.readLine())!=null){
                sb.append(temp).append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is!=null) {
                    is.close();
                }
                if(br!=null) {
                    br.close();
                }
                if(fw!=null) {
                    fw.close();
                }
                if(bw!=null) {
                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }

}
