package com.wangyang.config;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultipartRequestWapper extends HttpServletRequestWrapper {
    Map<String,String[]> params = new HashMap<String, String[]>();
    public final static String PATH="/home/wy/source_code/JavaProject/spring-study/";
    public MultipartRequestWapper(HttpServletRequest request) {
        super(request);
        setParams(request);
    }

    private void setParams(HttpServletRequest request) {
        try {
            //判断是否是multpart类型
            boolean isMul= ServletFileUpload.isMultipartContent(request);
            if(isMul){
                ServletFileUpload upload =new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(request);
                InputStream is = null;
                while (iter.hasNext()){
                    FileItemStream fis = iter.next();
                    is = fis.openStream();
                    if(fis.isFormField()){
                        setFormParam(fis.getFieldName(),is);
                    }else{
                        //完成文件的上传
                        Streams.copy(is,new FileOutputStream(PATH+"/upload/"+fis.getName()),true);
                        params.put(fis.getFieldName(),new String[]{fis.getName()});


                    }
                }

            }else {
                //不是multipart直接通过请求
                params=request.getParameterMap();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFormParam(String fieldName, InputStream is) throws IOException {
        if(params.containsKey(fieldName)){
            //表单域存在值
            String[] values=params.get(fieldName);
            values= Arrays.copyOf(values, values.length+1);
            values[values.length-1]=Streams.asString(is);
            params.put(fieldName,values);
        }else{
            params.put(fieldName,new String[]{Streams.asString(is)});
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if(values!=null){
            return values[0];
        }
        return null;
    }


    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if(values!=null){
            return values;
        }
        return null;
    }
}
