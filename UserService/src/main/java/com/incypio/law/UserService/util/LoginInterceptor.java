package com.incypio.law.UserService.util;

import com.incypio.law.UserService.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.incypio.law.UserService.util.UserThreadLocal;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //访问的其他资源直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");

        UserEntity user = null ;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            user = JwtUtils.parseJwt(authorization);

        }
        if (user == null){
            throw new RuntimeException("Please login");
        }
        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
