package com.ibm.opum.login.action;

import java.util.Arrays;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Interceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "";
	    if(ActionContext.getContext().getSession().isEmpty()){
	    	result = "login";
	    	ActionSupport action = (ActionSupport)invocation.getAction();
	    	action.addActionError("Log in First!");
	    }
	    else{
	    	String url = ServletActionContext.getRequest().getRequestURL().toString();
	    	String role = ActionContext.getContext().getSession().get("role").toString();
	    	if(url.contains("/applicant/") && !role.equals("Applicant Manager")){
	    		result = "unauthorized";
	    	}
	    	else if(url.contains("/asset/") && !role.equals("Asset Manager")){
	    		System.out.println();
	    		result = "unauthorized";
	    	}
	    	else{
	    		String actionName = invocation.getInvocationContext().getName();
	    		String[] publicActions = {"allWorkstationsLink","availabilityLink","availability","searchemployeeLink","searchemployee",
	    				"ViewMyDTRLink",
	    				"leaveFormLink","myLeaveByStatusLink","myLeaveByTypeLink","myEntitlementsLink","add","update","viewLeave","editLeave",
	    				"myconsultationLink","myhealthlogLink", "displayByConsultationNumView",
	    				"viewActiveLink", "searchResult", "searchByEmpLink", "find", "myProfileLink"};
	    		if(Arrays.asList(publicActions).contains(actionName)){
	    			result = invocation.invoke();
	    		}
	    		else if((url.contains("/workstation/") && !role.equals("Workstation Manager")) || 
	    				(url.contains("/security/") && !role.equals("Security")) ||
	    				(url.contains("/leave/") && !role.equals("HR Manager")) ||
	    				(url.contains("/employee/") && !role.equals("HR Manager"))){
	    			result = "unauthorized";
	    		}
	    		else if(url.contains("/clinic/")){
	    			String[] nurseActions = {"addMedicine","saveDailyMedSheet","updatedruginventory",
	    					"displayByDrugNumber","displayMedById","getMedicationEmployeeById","dailymedsheetLink",
	    					"addmedicineLink","druginventoryLink","viewmedsheetbyidLink","viewdailymedsheetLink","updatedruginventoryLink"};
	    			System.out.println(actionName);
	    			if(role.equals("Nurse") && Arrays.asList(nurseActions).contains(actionName)){
	    				result = invocation.invoke();
	    			}
	    			else if(role.equals("Doctor") && !Arrays.asList(nurseActions).contains(actionName)){
	    				result = invocation.invoke();
	    			}
	    			else
	    				result = "unauthorized";
	    		}
	    		else
	    			result = invocation.invoke();
	    		
	    	}
	    		    	
	    }
	    return result;
	}
	

	/*@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/
}
