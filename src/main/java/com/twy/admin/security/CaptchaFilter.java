package com.twy.admin.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.twy.admin.common.Const;
import com.twy.admin.exception.CaptchaException;
import com.twy.admin.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 图片验证码校验过滤器，在登录过滤器前
 *
 * @author gongpeng
 * @date 2021/5/10 15:41
 */
@Component
@Slf4j
public class CaptchaFilter extends OncePerRequestFilter {
    private final String loginUrl = "/login";
    private final String loginMethod = "POST";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (loginUrl.equals(url) && loginMethod.equals(request.getMethod())) {
            log.info("正在校验验证码---{}", url);
            try {
                validate(request);
            } catch (CaptchaException e) {
                log.info(e.getMessage());
                // 交给登录失败处理器处理
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) {
        String code = request.getParameter("code");
        String token = request.getParameter("token");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(token)) {
            throw new CaptchaException("验证码不能为空");
        }
        if (!code.equals(redisUtil.hget(Const.captcha_KEY, token))) {
            throw new CaptchaException("验证码不正确");
        }
        redisUtil.hdel(Const.captcha_KEY, token);
    }
}
