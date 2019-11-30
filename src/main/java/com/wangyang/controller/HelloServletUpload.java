package com.wangyang.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@WebServlet("s3_upload")
@MultipartConfig
public class HelloServletUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String savePath="/home/wy/source_code/JavaProject/spring-study/upload/";
        //home/wy/source_code/JavaProject/spring-study/build/serverBaseDir_jetty9/webapps-exploded
        Collection<Part> parts = req.getParts();
        if(parts.size()==1){
            Part part = req.getPart("file");
            String header = part.getHeader("content-disposition");
            String fileName =getFileName(header);
            System.out.println(fileName+"----------");
            part.write(fileName);
        }else{
            for(Part part : parts){
                String header = part.getHeader("content-disposition");
                String fileName = getFileName(header);
                part.write(fileName);
            }
        }
    }

    private String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
        return fileName;
    }
}
