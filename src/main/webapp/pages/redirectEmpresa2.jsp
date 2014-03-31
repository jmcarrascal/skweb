  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  
  <body onLoad="document.cheatForm.submit()">  
    <form action="/skweb1/validateMS" method="post" name="cheatForm">    
    	<input type="hidden"  name="usuario" value='<%=(String) request.getAttribute("usuarioWeb") %>'/>
    	<input type="hidden"  name="password" value='<%=(String) request.getAttribute("passwordWeb") %>'/>
    </form>
  </body>
