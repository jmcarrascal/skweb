<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="<%=request.getContextPath()%>/css/loginForm.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(document).ready(function () {
		//txt = "<p>Browser CodeName: " + navigator.appCodeName + "</p>";
		//txt+= "<p>Browser Name: " + navigator.appName + "</p>";
		//txt+= "<p>Browser Version: " + navigator.appVersion + "</p>";
		//txt+= "<p>Cookies Enabled: " + navigator.cookieEnabled + "</p>";
		//txt+= "<p>Platform: " + navigator.platform + "</p>";
		//txt+= "<p>User-agent header: " + navigator.userAgent + "</p>";
		//alert(txt);
		//alert(navigator.userAgent);
		//if ((/Netscape/.test(navigator.userAgent)))
		if (0) {
			$("#pNavegador").show();
		} else {	
			$("#validateEmpresa table").show();	
		}
	});
</script>

<div class="main" style="background: #EBEBEB;">
	<div class="separator"></div>
	<div class="space"></div>
	<div class="panel_100">
		<div class="space"></div>
		<div align="center">
			<s:form action="validateEmpresa">
				<table style="display: none">
					<tr>
						<td><s:textfield label="Usuario" name="usuario" /></td>
					</tr>
					<tr>
						<td><s:password label="Password" name="password" /></td>
					</tr>
					<tr>
						<s:submit value="Enviar" align="center"/>
					</tr>
				</table>
				<s:actionerror/>
			</s:form>
		</div>
	</div>
</div>
<div class="space"></div>
<div class="space"></div>
<div class="space"></div>
<div class="space"></div>
