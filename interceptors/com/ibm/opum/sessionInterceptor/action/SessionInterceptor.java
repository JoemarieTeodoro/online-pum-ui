package com.ibm.opum.sessionInterceptor.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (ActionContext.getContext().getSession().isEmpty()) {
            return "login";
        }

        return invocation.invoke();
    }
}
