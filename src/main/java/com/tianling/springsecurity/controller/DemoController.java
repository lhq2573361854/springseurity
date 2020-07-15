package com.tianling.springsecurity.controller;

import com.tianling.springsecurity.exception.except.LoginRunTimeException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author TianLing
 * Date 2020/6/3 22:03
 * Description
 */
@RestController
public class DemoController {
    @GetMapping("/hello")
    @ApiOperation("你好哦 hello")
    public String hello(@ApiParam("你传id啊") int id){
        return "hello world";
    }
    @GetMapping("/demo")
    public String demo(){
        return "hello demo";
    }

    @GetMapping("/exception")
    public void exception(){
        throw new LoginRunTimeException("出错了");
    }

    @RequestMapping("/file")
    public String file(MultipartFile file){
        String folder = "C:\\Users\\TianLing\\Desktop\\";
        File file1 = new File(folder + file.getOriginalFilename());
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
        return file1.getAbsolutePath();
    }
    @RequestMapping("/download/{id}")
    public String download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\TianLing\\Desktop\\hello.txt");
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=hello.txt");
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
    @GetMapping("/signin")
    public String  binding(){
        return "signin";
    }

}
