package com.zr.webstore.controller;

import com.zr.webstore.DTO.OrderCreateDTO;
import com.zr.webstore.DTO.ResultDTO;
import com.zr.webstore.Service.ShopService;
import com.zr.webstore.enums.ExceptionCode;
import com.zr.webstore.model.OrderItem;
import com.zr.webstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ShopService shopService;

    @ResponseBody
    @PostMapping("/shop")
    public ResultDTO shoping(@RequestBody OrderCreateDTO orderCreateDTO,
                          HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            return ResultDTO.errorOf(ExceptionCode.LONGIN_NOT);
        }

        Object message=shopService.shop(orderCreateDTO);
        if(message instanceof ResultDTO){
            return (ResultDTO) message;
        }else {
            OrderItem orderItem=(OrderItem) message;
            return ResultDTO.okOf(orderItem.getOrderId());
        }
    }
    @GetMapping("/seckill")
    public String seckill(HttpServletRequest request,
                          Model model){
        if(request.getSession().getAttribute("user")==null){
            return "redirect:/login";
        }else {
            List<Product> productList=shopService.querySeckillBook();
            model.addAttribute("productList", productList);
            return "seckill";
        }
    }

    @ResponseBody
    @PostMapping("/seckill")
    public ResultDTO seckill(@RequestBody OrderCreateDTO orderCreateDTO,
                             HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            return ResultDTO.errorOf(ExceptionCode.LONGIN_NOT);
        }
        String message=shopService.seckill(orderCreateDTO);
        return ResultDTO.messageOf(message);
    }

}
