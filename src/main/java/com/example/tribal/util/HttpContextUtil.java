package com.example.tribal.util;



import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/8/16 下午4:48
 */
/**
 * @desc 全局获取HttpServletRequest、HttpServletResponse
 */
public class HttpContextUtil {

    private HttpContextUtil() {

    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }
}
