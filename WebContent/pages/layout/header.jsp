<!-- MASTHEAD_BEGIN -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="ibm-masthead">
    <div id="ibm-mast-options">
        <ul id="ibm-mast-options-dw">
            <s:if test="#session.isLogin">
                <li><a href=""><span class="ibm-access">
                    </span> <s:property value="#session.fullName" /></a></li>
                <li id="ibm-sso"><span class="ibm-sso-link">
                        <a href="<s:url action="../logout/logoutLink"/>" tabindex="0"
                        class="ibm-sso-signin" role="button"
                        aria-label="Log Out"
                        aria-describedby="ibm-welcome-msg">Log Out</a>
                </span></li>
            </s:if>
            <s:else>
                <li><span class="ibm-access"> </span></li>
            </s:else>
        </ul>
    </div>
    <div id="ibm-universal-nav">
        <ul id="ibm-unav-links">
            <li style="font-size: 20px; font-style: bold;">
                <s:if test="%{#session.role == 'SYS_ADMIN' || #session.role == 'ADMIN'}">
                    <a href="<s:url action="adminHomeLink"/>">Online PUM®</a>
                </s:if>
                <s:elseif test="#session.role == 'USER'">
                    <a href="<s:url action="userLink"/>">Online PUM®</a>
                </s:elseif>
            </li>
        </ul>
    </div>
</div>