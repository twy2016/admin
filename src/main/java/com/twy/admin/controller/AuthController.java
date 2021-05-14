package com.twy.admin.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.twy.admin.common.Const;
import com.twy.admin.common.Result;
import com.twy.admin.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author gongpeng
 * @date 2021/5/10 15:20
 */
@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {
    private final Producer producer;
    private final RedisUtil redisUtil;

    @GetMapping("/captcha")
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 存储到redis中
        redisUtil.hset(Const.captcha_KEY, key, code, 120);
        log.info("验证码 -- {} - {}", key, code);
        return Result.ok(MapUtil.builder().put("token", key).put("base64Img", base64Img).build());
    }
}
