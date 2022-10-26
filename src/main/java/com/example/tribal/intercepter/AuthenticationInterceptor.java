//package com.example.tribal.intercepter;
//
//import com.example.tribal.annotation.PassToken;
//import com.example.tribal.entity.User;
//import com.example.tribal.service.UserService;
//import com.example.tribal.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.auth0.jwt.JWT;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * @author lhui    919238538@qq.com:
// * @description
// * @date 2022/7/29 下午3:46
// */
//
//
//public class AuthenticationInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private UserService userService;
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 从 http 请求头中取出 token
//        String token = request.getHeader("token");
//
//        // 1、如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        // 2、检查是否有 PassToken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            return true;
//        }
//
//        // 3、检查 token
//        if (token == null || "".equals(token)) {
//            throw new RuntimeException("token无效");
//        }
//
//        // 4、验证 token
//        if (!JwtUtil.verify(token)) {
//            throw new RuntimeException("token验证失败");
//        }
//
//        // 5、验证用户是否存在
//        // 获取 token 中的 userId
//        Integer userId = JWT.decode(token).getClaim("userId").asInt();
//
//        User user = userService.getUserById(userId + "");
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//
//        return true;
//
//    }
//}
