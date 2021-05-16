package com.zr.webstore.controller;


import com.zr.webstore.Service.OrderService;
import com.zr.webstore.model.OrderItem;
import com.zr.webstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orderItem")
    public String myOrder(HttpServletRequest request,
            Model model){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        List<OrderItem> orderItems= orderService.getUserOrder(user.getId());
        model.addAttribute("orderItems", orderItems);
        return "order";
    }
}
