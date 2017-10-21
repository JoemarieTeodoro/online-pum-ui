<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtm11/DTD/xhtm11-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<link rel="icon" href="http://www.icon-computers.in/app/webroot/img/gallery_images/1d6d1c0a04eaa002c1fa053236f0b656.png" type="image/x-icon" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link href="../resources/system/css/normalize.css" rel="stylesheet" type="text/css" />
	<link href="../resources/common/v17/css/www.css" rel="stylesheet" title="www" type="text/css" />
	<script src="../resources/common/js/dojo/w3.js" type="text/javascript"></script> 
	<link href="../resources/system/css/style.css" rel="stylesheet" type="text/css" />

</head>
<body id="ibm-com" class="bodyStyle">
	<div id="ibm-top">
		<tiles:insertAttribute name="header" />
		<!-- Non-Homepage Start-->
			<s:if test="actionName !='loginLink' && actionName!='homeLink' && actionName!='home'">
				<div class="nonhomeStyle">
					<div id="ibm-pcon" style="min-height: 575px;margin-bottom: 0px;">
						<div id="ibm-content">
							<!-- CONTENT_BODY -->
								<div id="ibm-content-body">
									<div id="ibm-content-main">
										<tiles:insertAttribute name="body" />
									</div>
								 
										<div id="ibm-content-sidebar"></div>
									
								</div>
							<!-- CONTENT_BODY_END -->
						</div>
						<!-- NAVIGATION_MENU_START -->
							<tiles:insertAttribute name="menu" />
						<!-- NAVIGATION_MENU_END -->
					</div>
				</div>
			</s:if>
		<!-- Non-Homepage End-->
		
		<!-- Homepage Start-->
			<s:else>
				<div class="homeStyle">
				<tiles:insertAttribute name="body" />	
				</div>
			</s:else>
		<!--Homepage End-->
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>

