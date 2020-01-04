package com.wangyang.controller;

import com.wangyang.pojo.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/img")
    public R fileUploadImg(@RequestParam("filename")MultipartFile file){
        try {
            if(!file.isEmpty()){
                System.out.println("file is not empty");
                String fileName= UUID.randomUUID()+file.getOriginalFilename();
                file.transferTo(new File("img/"+fileName));
                return R.ok(fileName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("file  upload fail");
    }


    @RequestMapping(value = "/upload",headers = "content-type=multipart/form-data")
    public R fileUpload(@RequestParam("filename")MultipartFile[] files, String html5,String qqEmail){
        System.out.println(html5);
        System.out.println(qqEmail);
        try {
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                file.transferTo(new File("img/"+file.getOriginalFilename()));
                System.out.println(file.getName());
            }

//            file.transferTo(new File(file.getOriginalFilename()));
//            String fileName = file.getOriginalFilename();
//            String result  ="上传文件["+fileName+"]";
//            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok("file upload success");
    }

    @RequestMapping("/upload_s")
    public R fileUpload_s(MultipartFile file){

        try {


            file.transferTo(new File(file.getOriginalFilename()));
            String fileName = file.getOriginalFilename();
            String result  ="上传文件["+fileName+"]";
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok("file upload success");
    }
}
