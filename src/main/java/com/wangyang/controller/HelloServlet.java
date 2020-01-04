package com.wangyang.controller;

import com.wangyang.util.MultipartRequestWapper;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//@WebServlet("/servlet")
@Deprecated
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        readFileByWapper(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        String name =req.getParameter("name");
        String result = "["+name+"]测试Get请求";
        System.out.println(result);
        resp.getWriter().write(result);
    }


    /**
     * 采用装饰器模式对request进行分装，封装器见MultiPartRequestWapper
     * @param req
     * @param resp
     * @throws IOException
     */
    public void readFileByWapper(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        String name = req.getParameter("name");
        if(isMultipart){
            req=new MultipartRequestWapper(req);
            String fileName = req.getParameter("filepond");
            String result = "请求是multipart["+isMultipart+"]，由用户["+name+"]上床文件["+fileName+"]";
            System.out.println(result);
            resp.getWriter().write(result);
        }else{
            String result = "请求是multipart["+isMultipart+"]非二进制文件，由用户["+name+"]测试";
            System.out.println(result);
            resp.getWriter().write(result);
        }


    }

    public void readFile01(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        InputStream in = req.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str="";
        StringBuffer sb = new StringBuffer();
        while ((str=br.readLine())!=null){
            sb.append(str);
        }
        System.out.println(sb);
        resp.getWriter().write(sb.toString());

    }
}
