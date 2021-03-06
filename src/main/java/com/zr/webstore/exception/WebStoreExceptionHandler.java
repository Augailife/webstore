package com.zr.webstore.exception;

import com.alibaba.fastjson.JSON;
import com.zr.webstore.DTO.ResultDTO;
import com.zr.webstore.enums.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class WebStoreExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {//当在页面中return+"页面名如index"时，返回的就是ModelAndView
        HttpStatus status = getStatus(request);
        String contentType = httpServletRequest.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            if(ex instanceof WebStoreException){
                resultDTO=ResultDTO.errorOf((WebStoreException) ex);
            }else {
                ex.printStackTrace();
                resultDTO=ResultDTO.errorOf((ExceptionCode.SYS_ERROR));
            }
            try {
                //用原始方法将返回数据变为json
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(200);
                httpServletResponse.setCharacterEncoding("utf-8");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;

        }else {
            if (ex instanceof WebStoreException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", "当前有点拥挤呢，稍后再试试吧~~");
                ex.printStackTrace();
            }
            return new ModelAndView("error");
        }

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
