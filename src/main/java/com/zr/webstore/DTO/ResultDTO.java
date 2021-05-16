package com.zr.webstore.DTO;

import com.zr.webstore.enums.ExceptionCode;
import com.zr.webstore.exception.WebStoreException;
import lombok.Data;

@Data
public class ResultDTO<T> {

    private Integer code;
    private String message;
    private T data;
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
//        因为是静态方法，在外部可以直接调用，所以此处必须实例化一个对象来设置；
        return resultDTO;
    }
//    public static ResultDTO errorOf(CustomizeException customizeException){
//        return ResultDTO.errorOf(customizeException.getCode(), customizeException.getMessage());
//    }
//    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
//        ResultDTO resultDTO=new ResultDTO();
//        resultDTO.setCode(customizeErrorCode.getCode());
//        resultDTO.setMessage(customizeErrorCode.getMessage());
////        因为是静态方法，在外部可以直接调用，所以此处必须实例化一个对象来设置；
//
//        return resultDTO;
//    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
    public static <T> ResultDTO okOf(T data){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(data);
        return resultDTO;
    }
    public static <T> ResultDTO messageOf(T data){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(2008);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(data);
        return resultDTO;
    }

    public static ResultDTO errorOf(WebStoreException ex) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ex.getCode());
        resultDTO.setMessage(ex.getMessage());
        return resultDTO;
    }

    public static ResultDTO errorOf(ExceptionCode sysError) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(sysError.getCode());
        resultDTO.setMessage(sysError.getMessage());
        return resultDTO;
    }
}

