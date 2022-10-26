//package com.example.tribal.intercepter;
//
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author lhui    919238538@qq.com:
// * @description 全局异常处理
// * @date 2022/7/29 下午3:48
// */
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler
//    public Object exceptionHandler(Exception e){
//        Map<String, Object> map = new HashMap<>();
//
//        String message = e.getMessage();
//        if(message == null || "".equals(message)){
//            map.put("msg", "未知错误");
//        } else{
//            map.put("msg", message);
//        }
//        return map;
//    }
//}
