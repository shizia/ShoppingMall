package com.cn.util;
 
import javax.servlet.http.Cookie;

 
/**
 * 
 * Cookie 工具类
 *
 */
public final class CookieUtils {
 
	/**
     * 在Cookie数组中查找指定name cookie
     */
    public static Cookie findCookie(Cookie[] cookies, String name){
        if(cookies == null){
            return null;
        }else{
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
            return null;
        }
    }
}
