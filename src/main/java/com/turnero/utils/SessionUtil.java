package com.turnero.utils;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.turnero.config.SessionContext;

public class SessionUtil {


    private static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);

    public static void saveCookies(String cookie) {
        try {
            SessionContext.getInstance().add("SET-COOKIE", cookie);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    public static String retrieveCookies() {
        return SessionContext.getInstance().get("SET-COOKIE", String.class);
    }

    public static List<String> extractPlaySessionCookie(String cookies) {
        return Arrays.stream(cookies.split(";"))
                .filter(cookie->cookie.contains("PLAY_SESSION"))
                .collect(Collectors.toList());
    }

}
