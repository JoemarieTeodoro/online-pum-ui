<!-- MASTHEAD_BEGIN -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="ibm-masthead">
    <div id="ibm-mast-options">
        <ul id="ibm-mast-options-dw">
            <s:if test="#session.isLogin">
                <li><a href=""><span class="ibm-access">
                    </span> <s:property value="#session.fullName" /></a></li>
                <li id="ibm-sso"><span class="ibm-sso-link">
                        <a href="../logout/logoutLink" tabindex="0"
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
            <li style="font-size: 20px; font-style: bold;"><a
                href="../login/homeLink">Online PUM®</a></li>
        </ul>
    </div>
</div>