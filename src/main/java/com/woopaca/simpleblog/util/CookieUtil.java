package com.woopaca.simpleblog.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {

    /**
     * 요청값을 바탕으로 쿠키 추가
     * @param response HttpServletResponse
     * @param name 쿠키 이름
     * @param value 쿠키 값
     * @param maxAge 쿠키 생존 시간
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 쿠키의 이름을 입력받아 쿠키 삭제
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param name 삭제할 쿠키 이름
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 객체를 직렬화하여 쿠키 값으로 변환
     * @param object 직렬화할 객체
     * @return 객체의 직렬화 결과
     */
    public static String serialize(Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    /**
     * 쿠키 값을 역직렬화하여 객체로 변환
     * @param cookie 쿠키
     * @param clazz 결과 객체 타입
     * @return 역직렬화된 결과 객체
     */
    public static <T> T deserialize(Cookie cookie, Class<T> clazz) {
        return clazz.cast(SerializationUtils
                .deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
