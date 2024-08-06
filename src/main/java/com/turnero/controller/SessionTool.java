package com.turnero.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionTool {

    @Autowired
    private HttpServletRequest request;


    public void store( String key, Object value) throws  Exception{
        HttpSession session = this.request.getSession(true);
        session.setAttribute(key, value);
        session.setMaxInactiveInterval(30*60);
    }

    public Object get(String key) throws Exception {
        if (this.request.getSession().getAttribute(key) != null){
            return this.request.getSession().getAttribute(key);
        }
        throw  new Exception("Data Session Not found");
    }

    public void remove(String key) throws Exception{
        if (this.request.getSession().getAttribute(key) != null){
             this.request.getSession().removeAttribute(key);
        }
        throw new Exception("Data Session does not found");
    }






}
