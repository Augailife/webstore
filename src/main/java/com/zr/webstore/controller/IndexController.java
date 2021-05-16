package com.zr.webstore.controller;

import com.zr.webstore.DTO.PageDTO;
import com.zr.webstore.Service.ProductService;
import com.zr.webstore.mapper.ProductMapper;
import com.zr.webstore.model.Product;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String index(@RequestParam(name = "size",defaultValue = "4") Integer size,
                        @RequestParam(name = "page",defaultValue = "1") Integer pageNow,
                        @RequestParam(name="type",defaultValue = "1") Integer type,
                        @RequestParam(name="search",required = false) String search,
                        Model model){
        if(search!=null&&search!="") {
            PageDTO<Product> pageDTO=productService.list(search,pageNow,size);
            model.addAttribute("pageDTO", pageDTO);
            model.addAttribute("search", search);
        }else {
            PageDTO<Product> pageDTO=productService.list(size,pageNow,type);
            model.addAttribute("pageDTO", pageDTO);
        }
        return "index";
    }

}
