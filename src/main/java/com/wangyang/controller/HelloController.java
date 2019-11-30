package com.wangyang.controller;

import com.wangyang.config.MultipartRequestWapper;
import com.wangyang.poji.Hello;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello words";
    }

    /**
     * 直接重定向到静态资源
     * @return
     */
    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/index.html";
    }

    /**
     * 文件上传
     * @param picture
     * @param name
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    @ResponseBody
    public String processRegistration(@RequestPart("filepond")MultipartFile picture,String name){
        try {
            picture.transferTo((new File(picture.getOriginalFilename())));
            String fileName = picture.getOriginalFilename();
            String result  ="由用户["+name+"]上传文件["+fileName+"]";
            System.out.println(result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail upload file";
    }

    /**
     * bean视图解析器
     * @return
     */
    @RequestMapping("/bean")
    public String beanTest(){
        return "customViewResolver";
    }

    @RequestMapping("/listHello")
    public  @ResponseBody Hello listHello(){
        return new Hello("zhangsan","123456");
    }

    /**
     * jsp的视图解析器
     * @return
     */
    @RequestMapping({"/index","/"})
    public String helloJsp(){
        return "index";
    }

    /**
     * Spring Security中当已登录用户再次访问登录界面时，应跳转到home
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login_p(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            return "login";
        }else{
            return "redirect:/index";
        }

//        return "login";
    }

    @RequestMapping("/s_upload")
//    @ResponseBody
    public void servletUpload(HttpServletRequest request, HttpServletResponse response){
        request = new MultipartRequestWapper(request);
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("file"));
    }

    @RequestMapping("/my_login")
    @ResponseBody
    public String login(HttpSession session){
        session.setAttribute("user","wangyang");
        return "Login success";
    }
    @RequestMapping("test_login")
    @ResponseBody
    public String test_login(HttpSession session){
        Object user = session.getAttribute("user");
        if(user==null)
            return "no login";
        return "already login";
    }
}
