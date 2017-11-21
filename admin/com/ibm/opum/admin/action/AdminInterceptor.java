package com.ibm.opum.admin.action;

import com.ibm.opum.user.bean.Role;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object role = ActionContext.getContext().getSession().get("role");

        return role != null && role.toString().equals(Role.USER.toString()) ? "user" : invocation.invoke();
    }
}
