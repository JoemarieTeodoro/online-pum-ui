package com.ibm.opum.resetPasswordInterceptor.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ResetPasswordInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

    	// Do pre processing before action
    	Map<String, Object> session = invocation.getInvocationContext().getSession();
        Object isPasswordMismatch = session.get("isPasswordMismatch");

    	if (isPasswordMismatch != null && Boolean.valueOf(isPasswordMismatch.toString())) {
    		ActionSupport action = (ActionSupport) invocation.getAction();
    		action.addActionError("Input in New Password does not match with input in Confirm New Password!");
    	}
    	
    	// invoke intercepted action (ResetPasswordAction)
    	String result = invocation.invoke();
    	
    	// Do post processing after action
    	if ("passwordMismatch".equals(result)) {
    		ActionContext.getContext().getSession().put("isPasswordMismatch", true);
    	}
    	return result;
    }
    
}
