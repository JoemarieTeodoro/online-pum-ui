package com.ibm.opum.util.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ibm.opum.admin.bean.JavaToJsonUtil;
import com.ibm.opum.resourceutils.ClientConfiguration;
import com.ibm.opum.util.bean.ResetPassword;
import com.ibm.opum.util.bean.ResetPasswordToken;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ResetPasswordAction extends ActionSupport {

	private Logger logger = Logger.getLogger(ResetPasswordAction.class);

    private static final long serialVersionUID = 1L;

    private String newPassword;
    private String confirmNewPassword;
    private String email;
    private String token;

    public String validateToken() {
    	ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setEmail(ServletActionContext.getRequest().getParameter("email"));
        resetPasswordToken.setToken(ServletActionContext.getRequest().getParameter("token"));

        ActionContext.getContext().getSession().put("email", resetPasswordToken.getEmail());
        ActionContext.getContext().getSession().put("token", resetPasswordToken.getToken());
        
        Client client = Client.create();
        WebResource webResource = client
				.resource(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
						+ "/online-pum-rest/webapi/resetPassword/validateToken");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
                JavaToJsonUtil.javaToJson(resetPasswordToken));

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return "validateTokenLink";
    }

    public String resetPassword() {
        setEmail(ActionContext.getContext().getSession().get("email").toString());
        setToken(ActionContext.getContext().getSession().get("token").toString());
        
    	if (!newPassword.equals(confirmNewPassword)) {
        	logger.info("isValidPassword!");
        	ActionContext.getContext().getSession().clear();
        	ActionContext.getContext().getSession().put("showMismatch", true);
            return "passwordMismatch";
        }
    	
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setNewPassword(newPassword);
        resetPassword.setEmail(ActionContext.getContext().getSession().get("email").toString());

        Client client = Client.create();
		WebResource webResource = client.resource(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
				+ "/online-pum-rest/webapi/resetPassword/reset");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
                JavaToJsonUtil.javaToJson(resetPassword));

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        ActionContext.getContext().getSession().clear();
        ActionContext.getContext().getSession().put("isResetSuccessful", true);

        return "login";
    }

    public String invalid() {
        return "passwordChecking";
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
