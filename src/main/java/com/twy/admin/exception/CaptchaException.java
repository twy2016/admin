package com.twy.admin.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author gongpeng
 * @date 2021/5/10 15:50
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
