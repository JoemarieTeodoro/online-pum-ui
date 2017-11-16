package com.ibm.opum.login.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private Logger logger = Logger.getLogger(LoginInterceptor.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();

        Object isLogin = session.get("isLogin");
        Object role = session.get("role");

        if ((isLogin == null || !Boolean.valueOf(isLogin.toString())) && role == null) {
        	if(Boolean.valueOf(session.get("isResetSuccessful").toString()))
        	{
        		ActionSupport action = (ActionSupport) invocation.getAction();
        		action.addActionError("Successful password reset! Please login to continue.");
        		logger.info("action: " + action.getClass());
        		
        	}
            return "login";
        }

        return role.toString();
    }
    
}
