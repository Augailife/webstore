package com.zr.webstore.controller;

import com.zr.webstore.DTO.ResultDTO;
import com.zr.webstore.Service.ProductService;
import com.zr.webstore.enums.ExceptionCode;
import com.zr.webstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ItemController {
    @Autowired
    ProductService productService;
    @GetMapping("/item")
    public String productItem(@RequestParam("id") Integer productId,
                            @RequestParam("type")  Integer type,
                            Model model,
                            HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            return "redirect:/login";
        }
        Product product=productService.getProductById(productId,type);
        model.addAttribute("product", product);
        return "productitem";
    }
}
