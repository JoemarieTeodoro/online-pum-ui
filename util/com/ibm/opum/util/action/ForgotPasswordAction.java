package com.ibm.opum.util.action;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;

import com.ibm.opum.resourceutils.ClientConfiguration;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ForgotPasswordAction extends ActionSupport {

    private Logger logger = Logger.getLogger( ForgotPasswordAction.class );

    private static final long serialVersionUID = 1L;

    private String newPassword;

    private String confirmNewPassword;

    private String email;

    private final String VALID_EMAIL_REGEX = "^[a-zA-Z0-9]+@{1}+[a-zA-Z]{0,2}([\\\\.]+)+ibm+([\\\\.]+)com$";

    public String validateEmail() {

        ActionContext.getContext().getSession().clear();

        if( StringUtils.isEmpty( email ) ){
            return "isEmptyEmail";
        }
        else if( !email.matches( VALID_EMAIL_REGEX ) ){
            return "isIncorrectEmail";
        }

        Client client = Client.create();
        WebResource webResource =
            client.resource( ClientConfiguration.getConfigProperties().getProperty( "SERVER_URL" ) +
                "/online-pum-rest/webapi/resetPassword/validateEmail" );
        ClientResponse response = webResource.type( "application/json" ).post( ClientResponse.class, email );

        if( response.getStatus() != 200 ){
            logger.error( "invalid Email!" );
            ActionContext.getContext().getSession().clear();
            ActionContext.getContext().getSession().put( "isInvalidEmail", true );
            return "isInvalidEmail";
        }

        ActionContext.getContext().getSession().put( "isEmailSentSuccessfully", true );

        return "login";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String forgotPassword() {
        return "forgotPw";
    }
}
