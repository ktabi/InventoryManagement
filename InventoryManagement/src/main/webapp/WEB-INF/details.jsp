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
<title> Details Page</title>
 <!-- css -->
<link href="../CSS/details.css" rel="stylesheet" type="text/css">
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<nav>
			<ul>
				<li>
					<a href="/dashboard"> HOME </a>
				</li>
			
				<li>
					<a id="logout" href="logout"> LOGOUT </a>
				</li>
			</ul>
		</nav>
		
		<main>	
			<header> <!-- Beginning of Container -->
		    	<h5> Showing Details OF Item Number: <span id="idNumb"><c:out value="${item.id}"></c:out></span></h5>
		    </header> <!-- End of Container -->
		    		
			<div id="pdiv">
				<h5> Item Name: <span id="idNumb"><c:out value="${item.name}"></c:out></span></h5>
				<h5> Item Price: $<span id="idNumb"><c:out value="${item.price}"></c:out></span></h5>
				<h5> Item Quantity: <span id="idNumb"><c:out value="${item.quantity}"></c:out></span></h5>
				<h5>Item Description: <span id="idNumb"><c:out value="${item.description}"></c:out></span></h5>
				<h5>Added By: <span id="idNumb"><c:out value="${item.user.firstName}"></c:out></span></h5>
				
				
				<a href="/edit/${item.id}"><button class="submit">Update</button></a>
		    			<form action="/delete/${item.id}" method="post">
		    				<input type="hidden" name="_method" value="delete" />
		    				<button id="delete">Delete</button>
		    			</form>
			</div>
			
		</main>
	</div>
	

</body>
</html>