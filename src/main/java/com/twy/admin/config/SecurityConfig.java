package com.twy.admin.config;

import com.twy.admin.security.CaptchaFilter;
import com.twy.admin.security.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author gongpeng
 * @date 2021/5/10 16:05
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CaptchaFilter captchaFilter;
    //请求白名单
    public static final String[] URL_WHITELIST = {"/webjars/**", "/favicon.ico", "/captcha", "/login", "/logout",};

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .formLogin()
                .failureHandler(loginFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                // 不会创建 session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
