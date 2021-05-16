package com.zr.webstore.controller;

import com.zr.webstore.DTO.PageDTO;
import com.zr.webstore.Service.BookManageService;
import com.zr.webstore.enums.ExceptionCode;
import com.zr.webstore.exception.WebStoreException;
import com.zr.webstore.model.Product;
import com.zr.webstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BookManageController {
    @Autowired
    BookManageService bookManageService;

    @GetMapping("/edit/{action}")
    public  String edit(HttpServletRequest request,
                        @PathVariable("action") String action,
                        @RequestParam(value = "id",required = false) Integer productId,
                        @RequestParam(value = "type",required = false) Integer type,
                        Model model){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        if(user.getType()!=1){
            return "redirect:/";
        }else {
            if(action.equals("update")){
                Product product=bookManageService.selectById(productId,type);
                if(product!=null){
                    model.addAttribute("product",product);
                    return "edit";
                }else {
                    return "redirect:/manage";
                }
            }else {
                return "edit";
            }
        }
    }
    @GetMapping("/manage")
    public String post(
            HttpServletRequest httpServletRequest,
            Model model,
            @RequestParam(name = "size",defaultValue = "5") Integer size,
            @RequestParam(name = "page",defaultValue = "1") Integer pageNow
    ){
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        if(user.getType()!=1){
            return "redirect:/";
        }else {
            PageDTO<Product> pageDTO =bookManageService.list(size,pageNow);
            model.addAttribute("pageDTO", pageDTO);
        }
        return "manage";
    }
    @PostMapping("/manage/{action}")
    public String update(@PathVariable("action") String action,
                         @RequestParam(name = "file",required = false) MultipartFile file,
                         Product product,
                         HttpServletRequest httpServletRequest){
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        if(user.getType()!=1){
           throw new WebStoreException(ExceptionCode.USERTYPE_ERROR);
        }
        String message="";
        if(action.equals("update")){
            if (file!=null&&!file.isEmpty()){
                String upload = bookManageService.upload(file);
                if(upload.equals("上传失败，请选择文件")&&upload.equals("上传失败")){
                    message=upload;
                    httpServletRequest.getSession().setAttribute("message", message);
                    return "manage";
                }else {
                    product.setImgPath(upload);
                    bookManageService.update(product);
                }
            }else {
                message=bookManageService.update(product);
            }
        }else if(action.equals("insert")){
            if(file!=null){
                String upload = bookManageService.upload(file);
                if(upload.equals("上传失败，请选择文件")&&upload.equals("上传失败")){
                    message=upload;
                    httpServletRequest.getSession().setAttribute("message", message);
                    return "manage";
                }else {
                    product.setImgPath(upload);
                    bookManageService.insert(product);
                }
            }else {
                bookManageService.insert(product);
            }
        }else if(action.equals("delete")){
            bookManageService.delete(product);
        }
        return "redirect:/manage";
    }

}
