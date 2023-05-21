package com.zkm.common.result.handler;

import cn.hutool.json.JSONUtil;
import com.zkm.common.result.ResultTool;
import com.zkm.common.utils.JWTUtils;
import com.zkm.mapper.UserZkmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginSuccessHanlder implements AuthenticationSuccessHandler {
    @Autowired
    UserZkmMapper userZkmMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        // 生成JWT，并放置到请求头中
        String jwt = JWTUtils.generateToken(authentication.getName());
        response.setHeader(JWTUtils.AUTHORIZATION, jwt);

        outputStream.write(JSONUtil.toJsonStr(ResultTool.success(jwt)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }
}
