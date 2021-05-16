package com.zr.webstore.controller;

import com.zr.webstore.DTO.ResultDTO;
import com.zr.webstore.Service.UserService;
import com.zr.webstore.enums.ExceptionCode;
import com.zr.webstore.exception.WebStoreException;
import com.zr.webstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
   @Autowired
   UserService userService;
//   登录
    @GetMapping("/loginSuccess")
    public  String loginSuccess(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        if(user.getType()!=1){
            return "redirect:/";
        }else{
            return "loginSuccess";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
   @PostMapping("/login")
   public String login(@RequestParam("name") String name,
                       @RequestParam("pwd") String pwd,
                       Model model,
                       HttpServletRequest httpServletRequest
   ) {
       User user = userService.testLogin(name);
       String message = "登陆成功";
       if (user != null) {
           if (user.getPwd().equals(pwd)) {
               model.addAttribute("message", message);
               if(user.getType()!=1){
                   httpServletRequest.getSession().setAttribute("user", user);
               }else {
                   httpServletRequest.getSession().setAttribute("user", user);
                   httpServletRequest.getSession().setAttribute("type", 1);
               }
               return "redirect:/loginSuccess";
           } else {
               message = "密码错误";
           }
       } else {
           message="用户名不存在";
       }
       model.addAttribute("message", message);
       return "login";
   }
//   注册
    @GetMapping("/regist")
    public String register(){
        return "register";
    }
    @PostMapping("/regist")
    public String register(@RequestParam("name") String name,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("phone") String phone,
                           @RequestParam("email") String email,
                           Model model,
                           HttpServletRequest httpServletRequest) {
        if (userService.existsName(name)) {
            model.addAttribute("message", "用户名已存在");
        } else {
            User user = userService.regist(name, pwd, phone,email);
            httpServletRequest.getSession().setAttribute("user", user);
            return "redirect:/loginSuccess";
        }
        return "register";
    }

    /**
     * ajax验证用户名
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/ajaxUser")
    public Object existsName(@RequestBody User user){
        boolean existsName = userService.existsName(user.getName());
        return ResultDTO.okOf(existsName);
    }

}
