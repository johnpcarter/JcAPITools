<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<HTML>
  <HEAD>
	  <meta charset="utf-8">
	  <title>JcAPITools Documentation</title>
	  <base href="/JcAPITools">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="icon" type="image/x-icon" href="favicon.ico">
	  <link rel="stylesheet" type="text/css" href="./swagger-ui.css" >
	  <link rel="stylesheet" type="text/css" href="./hide-header.css">
	  <link rel="stylesheet" type="text/css" href="/WmRoot/top.css">
	  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	  <link rel="stylesheet" href="/JcAPITools/styles.css">
	  <link rel="stylesheet" href="/JcAPITools/page.css">	  
  </HEAD>
  <BODY style="padding:10px">
		<div class="app-page" [class.example-is-mobile]="mobileQuery.matches">
		  <div class="tdmasthead" id="top" height="50px" width="100%">
			<div style="float:right;padding: 10px">
				<img src="/JcAPITools/resources/sag-logo-white@3x.png" height="25px"/>
			</div>
			<div class="saglogo" style="display: flex; align-items: center;">
				<img src="/JcAPITools/resources/wm-microservice-runtime.svg" height="50px" alt="Micro Service Runtime"/>
			</div>
		</div>
		<div style="padding: 10px; float: right; font-family: sans-serif">
		  <a href="/JcAPITools/index.html">API Documentation</a> : Built in Services
		</div>
		
		  <div class="page-content" style="padding-top: 40px; width: 80%; margin-left: auto; margin-right: auto">
		  	<h2 style="font-size: 36px; margin: 0; font-family: sans-serif; color: #3b4151;">Welcome</h2>
		  	The following services are available for use from packages that you develop and host in the same runtime container.</p>
		  	<p>These services leverage the API Gateway Administrative API to allow you to automate the publishing and deployment of your API's to a remote webMethods API Gateway.</p><br>
		  	<h3>Summary of services</h3>
		  	<p style="margin-left: 30px"><b><a href="/JcAPITools/listLocalAPIs">listLocalAPIs</a></b> - Returns a list of all APIs that are hosted within the local container. <a href="/invoke/jc.api.tools_.services:listLocalAPIs"><img src="JcAPITools/resources/ns_flow.gif"> Run</a></p>
			<p style="margin-left: 30px"><b><a href="/JcAPITools/publishLocalAPIs">publishLocalAPIs</a></a></b> - Deploys all locally hosted API's to the API Gateway '%invoke jc.api.tools_.services:gatewaySettings%%value api_gateway_url%%endinvoke%.' <a href="/invoke/jc.api.tools_.services:publishLocalAPIs"><img src="JcAPITools/resources/ns_flow.gif"> Run</a></p></p>
		  	<p style="margin-left: 30px"><b><a href="/JcAPITools/publishLocalAPI">publishLocalAPI</a></a></b> - Deploys the swagger definition provided by the named API descriptor hosted locally.</p></p>
			<p style="margin-left: 30px"><b><a href="/JcAPITools/publishAPI">publishAPI</a></b> - Deploys the swagger definition provided by the url or git repo and publishes it to the API Gateway.</p>
			<p style="margin-left: 30px"><b><a href="/JcAPITools/setAPIAttributes">setAPIAttributes</a></b> - Updates an existing API's meta-data</p>
			<p style="margin-left: 30px"><b><a href="/JcAPITools/redeployAPI">redeployAPI</a></b> - Deploys an API from one API Gateway to another using Promotion and/or publishes API to the Portal</p>
			<p style="margin-left: 30px"><b><a href="/JcAPITools/rollbackAPI">rollbackAPI</a></b> - Removes the API from the Portal and/or removes last changes to API Gateway</p>
			<br>
		  	<p>API Definition can be downloaded <a href="http://localhost:5555/rad/jc.api:api?swagger.json">here</a></p>
		  	<p>An example Jenkins CI/CD script can be downloaded here <a href="/JcAPITools/Jenkinsfile">here</a></p>
		  	<p>Software AG's API Documentation is available <a href="https://api.webmethodscloud.eu/#sagapis/gallery">here</a></p>

		  	<br><br>
			<h2><a href="/JcAPITools/release.txt"><img src="/JcAPITools/_images/forward-button.png" width="18px"> Version 1.2 / January 2020 / Update</a></h2>
			  Updated documentation, including swagger doc.
			<h2><a href="/JcAPITools/release.txt"><img src="/JcAPITools/_images/forward-button.png" width="18px"> Version 1.1 / November 2020 / Update</a></h2>
				Update home page to allow API's to be published to default API Gateway (see above!!)
		  	<h2><a href="/JcAPITools/release.txt"><img src="/JcAPITools/_images/forward-button.png" width="18px"> Version 1.0 / January 2020 / New</a></h2>
		  		Packaged properly at last!
		  </div>
		</div>
	</BODY>
</HTML>