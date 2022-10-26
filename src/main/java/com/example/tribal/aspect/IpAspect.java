package com.example.tribal.aspect;


import com.example.tribal.util.AddressUtil;
import com.example.tribal.util.HttpContextUtil;
import com.example.tribal.util.IPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/8/16 下午4:46
 */


@Aspect
@Component
public class IpAspect {


    @Pointcut("@annotation(com.example.tribal.annotation.Ip)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);

        System.out.println(MessageFormat.format("当前IP为:[{0}]；当前IP地址解析出来的地址为:[{1}]", ip, AddressUtil.getCityInfo(ip)));
        return point.proceed();
    }

}
