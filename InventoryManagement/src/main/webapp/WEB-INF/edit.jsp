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
<title> Updating Item</title>
 <!-- css -->
<link href="../CSS/edit.css" rel="stylesheet" type="text/css">
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
			<div id="pdiv">
				showing info for Item number: <c:out value="${item.id}"></c:out>
			
				<form:form action="/update/${item.id}" method="post"  modelAttribute="item">
				<!-- For us to be able to overrite the form in the put method we need the following -->
				<!-- We need the page isErrorPage="true" %>  -->
				<!-- We need the  hiddenmethod.filter set to true in Application.properties  -->
				<input type="hidden" name="_method" value="put" />
				<form:hidden path="user" value="${userId}" />
					<div>
				 	<label>Name:</label>
					<form:input path="name" type="text" class="input-field" placeholder="Name" />
        			<form:errors path="name" class="text-danger" />
        			</div>
        			
        			<div>
        			<label>Quantity:</label>
        			<form:input path="quantity" type="number" class="input-field" placeholder="Quantity" />
        			<form:errors path="quantity" class="text-danger" />
        			</div>
        			
        			<div>
        			<label>Price:</label>
        			<form:input path="price" type="number" class="input-field" placeholder="Price" />
        			<form:errors path="price" class="text-danger" />
        			</div>
        			
        			<div>
        			<label>Description:</label>
        			<form:input path="description" type="text" class="input-field" placeholder="Description" />
        			<form:errors path="description" class="text-danger" />
        			</div>
        			
        		<%-- 	<div>
        			<label>Image:</label>
        			<form:input path="image" type="file" name="file" class="input-field"></form:input>
        			<form:errors path="image" class="text-danger" /> 
        			</div> --%>
        			
        			<div class="submit">
					<button id="create" type="submit" value="Update item">Update</button>
<!-- 					<a href="/dashboard"><button id="cancel">Cancel</button></a>
 -->					</div>
				</form:form>
				
			</div>
				
			
		</main>
	</div>
	

</body>
</html>