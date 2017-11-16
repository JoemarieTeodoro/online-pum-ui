package com.ibm.opum.util.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ibm.opum.admin.bean.JavaToJsonUtil;
import com.ibm.opum.user.bean.ResetPassword;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ResetPasswordAction extends ActionSupport {

	private Logger logger = Logger.getLogger(ResetPasswordAction.class);

	private static final long serialVersionUID = 1L;
	
	public String validateToken() {
		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setEmail(ServletActionContext.getRequest().getParameter("email"));
		resetPassword.setToken(ServletActionContext.getRequest().getParameter("token"));
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/online-pum-rest/webapi/resetPassword/validateToken");
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, JavaToJsonUtil.javaToJson(resetPassword));

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
		return "resetPasswordLink";
	}
	
}
