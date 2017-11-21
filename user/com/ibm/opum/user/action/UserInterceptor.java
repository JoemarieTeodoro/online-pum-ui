package com.ibm.opum.user.action;

import com.ibm.opum.user.bean.Role;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object role = ActionContext.getContext().getSession().get("role");

        return role != null && role.toString().contains(Role.ADMIN.toString()) ? "admin" : invocation.invoke();
    }
}
