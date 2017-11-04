package com.ibm.opum.login.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();

        Object isLogin = session.get("isLogin");
        Object role = session.get("role");

        if ((isLogin == null || !Boolean.valueOf(isLogin.toString())) && role == null) {
            return "login";
        }

        return role.toString();
    }
}
