package com.ibm.opum.forgotPasswordInterceptor.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ForgotPasswordInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    private String email;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        Map<String, Object> session = invocation.getInvocationContext().getSession();
        Object isInvalidEmail = session.get( "isInvalidEmail" );
        Object isEmptyEmail = session.get( "isEmptyEmail" );
        Object isIncorrectEmail = session.get( "isIncorrectEmail" );

        ActionSupport action = ( ActionSupport ) invocation.getAction();

        if( isInvalidEmail != null && Boolean.valueOf( isInvalidEmail.toString() ) ){
            action.addActionError( "Email does not exists in PUM employee directory!" );
        }
        else if( isEmptyEmail != null && Boolean.valueOf( isEmptyEmail.toString() ) ){
            action.addActionError( "Email is required!" );
        }
        else if( isIncorrectEmail != null && Boolean.valueOf( isIncorrectEmail.toString() ) ){
            action.addActionError( "Invalid Email Address!" );
        }

        String result = invocation.invoke();
        if( !result.equals( "login" ) ){
            ActionContext.getContext().getSession().clear();
        }
        if( "isEmptyEmail".equals( result ) ){
            ActionContext.getContext().getSession().put( "isEmptyEmail", true );
        }
        else if( "isIncorrectEmail".equals( result ) ){
            ActionContext.getContext().getSession().put( "isIncorrectEmail", true );
        }
        else if( "isInvalidEmail".equals( result ) ){
            ActionContext.getContext().getSession().put( "isInvalidEmail", true );
        }
        return result;
    }

    public String getUserName() {
        return email;
    }

    public void setUserName( String email ) {
        this.email = email;
    }
}
