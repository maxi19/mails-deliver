package com.turnero.config;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionContext { // ver si se deja thread-safe

    private static final SessionContext instance = new SessionContext();

    private SessionContext() {
    }

    public static SessionContext getInstance() {
        return instance;
    }

    public HttpSession retrieveCurrentSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public <T> void add(String attrName, T value) throws Exception {
        if (value == null) {
			throw new Exception("value for attr '" + attrName + "' is null");
		}
        retrieveCurrentSession().setAttribute(attrName, value);

    }

    public <T> T get(String attrName, Class<T> clazz) {
        return clazz.cast(retrieveCurrentSession().getAttribute(attrName));
    }

}
