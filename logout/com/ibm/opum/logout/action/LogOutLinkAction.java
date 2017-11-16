package com.ibm.opum.logout.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOutLinkAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private SessionMap<String, Object> sessionMap;

    public String logout() {
        sessionMap.invalidate();
        ActionContext.getContext().getSession().clear();

        return "logout";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap<String, Object>) map;
    }
}
