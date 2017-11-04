package com.ibm.opum.logout.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOutLinkAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    public String logout() {
        ActionContext.getContext().getSession().clear();

        return "logout";
    }
}
