<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inventory Management System</title>
<!-- css -->
<link href="../CSS/authenticate.css" rel="stylesheet" type="text/css">
  <!-- Bootstrap -->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous"> -->

</head>
<body>
    <div class="hero">
    
    <!-- Flash attribute for trying to access dashboard while not logged in! -->
    	<p id="unauthorized" class="text-danger"><c:out value="${login}"></c:out></p>
    
    	<div class="form-box">
    		<div class="button-box">
    			<div id="btn"></div>
    			<button type="button" class="toggle-btn" onclick="login()"> Log In</button>
    			<button type="button" class="toggle-btn" onclick="register()"> Register</button>
    		</div>
    		
    	<form:form action="/login" method="post" modelAttribute="newLogin" id="login" class="input-group">
        	<form:input path="email" type="email" class="input-field" placeholder="enter email" />
        	<form:errors path="email" class="text-danger" />
        	
        	
        	<form:input path="password" type="password" class="input-field" placeholder="Password" />
        	<form:errors path="password" class="text-danger" />
        	
        	<button type="submit" value="Login" class="submit-btn"> Log In</button>
        </form:form>
        
        <form:form action="/register" method="post" modelAttribute="newUser" id="register" class="input-group">
        	<form:input type="text"  path="firstName" class="input-field" placeholder="First Name" />
        	<form:errors path="firstName" class="text-danger" />
        	
        	<form:input type="text" path="lastName" class="input-field" placeholder="Last Name" />
        	<form:errors path="lastName" class="text-danger" />
        	
        	<form:input type="email" path="email" class="input-field" placeholder="Email" />
        	<form:errors path="email" class="text-danger" />
        	
        	<form:input type="password" path="password" class="input-field" placeholder="Password" />
        	 <form:errors path="password" class="text-danger" />
        	
        	<form:input type="password" path="confirm" class="input-field" placeholder="Confirm Password" />
        	<form:errors path="confirm" class="text-danger" />
        	
        	<button type="submit" value="Register" class="submit-btn"> Register</button>
        </form:form>
    		
        </div>
 
    </div>
    
    <script>
    	var x= document.getElementById("login");
    	var y= document.getElementById("register");
    	var z= document.getElementById("btn");
    	
    	function register() {
    		x.style.left = "-400px";
    		y.style.left = "50px";
    		z.style.left = "110px";
    	}
    	
    	function login() {
    		x.style.left = "50px";
    		y.style.left = "450px";
    		z.style.left = "0px";
    	}
    	
    </script>
    
</body>
</html>