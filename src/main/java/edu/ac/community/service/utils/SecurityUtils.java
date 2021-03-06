package edu.ac.community.service.utils;

import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 加密工具类
 */
public class SecurityUtils {



    public static String md5(String content){
        return DigestUtils.md5DigestAsHex(content.getBytes());
    }

    public static String base64(String content){
        return Base64Utils.encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String decoderBase64(String content){
        return new String(Base64Utils.decodeFromString(content));
    }

}
