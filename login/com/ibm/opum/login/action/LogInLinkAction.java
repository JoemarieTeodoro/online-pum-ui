package com.ibm.opum.login.action;

import com.ibm.opum.resourceutils.ClientConfiguration;
import com.ibm.opum.user.bean.Employee;
import com.ibm.opum.user.bean.Role;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;

public class LogInLinkAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public String login() {
        ActionContext.getContext().getSession().clear();
        return "login";
    }

    public String authenticate() {
        try {
			ClientConfig clientConfig = ClientConfiguration.getInstance();
			Client client = Client.create(clientConfig);

            WebResource webResource = client
                    .resource("http://localhost:8080/online-pum-rest/webapi/opum/userLogin/" + username);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, password);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
			Employee employee = response.getEntity(Employee.class);
            ActionContext.getContext().getSession().put("employeeID", employee.getEmployeeId());
			ActionContext.getContext().getSession().put("role", employee.getAssignedRoles().get(0).toString());
			ActionContext.getContext().getSession().put("email", employee.getIntranetId());
            ActionContext.getContext().getSession().put("fullName", employee.getFullName());
			ActionContext.getContext().getSession().put("employeeIdNumber", employee.getEmployeeId());
            ActionContext.getContext().getSession().put("username", username);
            ActionContext.getContext().getSession().put("password", password);
            ActionContext.getContext().getSession().put("isLogin", true);

			if (employee.getAssignedRoles().contains(Role.SYS_ADMIN)
					|| employee.getAssignedRoles().contains(Role.ADMIN)) {
				return "admin";
			} else {
				return "user";
			}

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
