<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
    <div class="ibm-body">
        <div class="ibm-main">
            <div class="ibm-title">
            
                    <s:if test="#session.role == 'SYS_ADMIN'">
                         <h2>System Administrator Home</h2>
                    </s:if>
                    <s:if test="#session.role == 'ADMIN'">
                         <h2>Admin Home</h2>
                    </s:if>
                
                <hr>
            </div>
        </div>
    </div>
</body>
</html>