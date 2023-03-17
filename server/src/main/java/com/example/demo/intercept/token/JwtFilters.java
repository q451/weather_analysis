package com.example.demo.intercept.token;
import com.example.demo.common.codeEnum.ErrorCodeEnum;
import com.example.demo.common.exception.MyException;
import com.example.demo.util.JwtUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截验证，不带token不能访问接口
 * @author 杨小平
 * @date 2022年10月31日15:35:51
 */
public class JwtFilters implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            throw new MyException(ErrorCodeEnum.TOKEN_TIME_OUT);
        }
        try {
            JwtUtil.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ErrorCodeEnum.TOKEN_TIME_OUT);
        }
        return true;
    }
}
