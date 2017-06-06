package com.huiwan.gdata.common.utils.http;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Charsets;

/**
 * @author 
 */
public class UriHelper {
    private static final Logger log = LoggerFactory.getLogger(UriHelper.class);
    /**
     * 将字符串转为规范的uri字符串形式
     * @param url 字符串形式的url
     * @param charset 编码方式
     * @return 编码后的uri
     */
    public static String toUriString(String url, Charset charset) {
        String encodedString = url;
        try {
            encodedString = UriComponentsBuilder.fromUriString(url).build().encode(charset.name()).toUriString();
        } catch (UnsupportedEncodingException ignored) {
            log.error("编码异常", ignored);
        }
        return encodedString;
    }
    public static String toUTF8UriString(String url) {
        return toUriString(url, Charsets.UTF_8);
    }
}
