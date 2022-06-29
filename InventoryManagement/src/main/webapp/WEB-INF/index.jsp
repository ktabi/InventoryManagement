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
<title>Inventory Management App</title>
 <!-- css -->
<link href="../CSS/index.css" rel="stylesheet" type="text/css">
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
					<a href="#"> HOME </a>
				</li>
				<li>
					<a href="#"> ABOUT</a>
				</li>
				<li>
					<a href="#"> CONTACT </a>
				</li>
				<li>
					<a id="logout" href="logout"> LOGOUT </a>
				</li>
			</ul>
		</nav>
		
		<main>
		    <header> <!-- Beginning of Container -->
		    	<h5> Welcome <span id="greeting"><c:out value="${loggedUser.firstName}"></c:out></span></h5>
		        <h3> Inventory Management System</h3>
		    </header> <!-- End of Container -->
		    
 <!--  Flash attribute for trying to access update while not original  creater!-->    	
 	<p class="text-danger"><c:out value="${user}"></c:out></p>
	   		    
		    <table id="items">
		    	<thead>
		    		<tr>
		    			<th> ID </th>
	    				<th> Name </th>
		    			<th> Price</th>
		    			<th> Description </th>
		    			<th> Quantity </th>
		    			<th> Actions </th>
		    		</tr>
		    	</thead>
		    	<tbody>
		    		<c:forEach var="item" items="${items}">
		    		<tr>		    			
		    			<td> <c:out value="${item.id}" /> </td>
		    			<td> <a href="/details/${item.id}"><c:out value="${item.name}" /> </a></td>
		    			<td> $<c:out value="${item.price}" /> </td>
		    			<td> <c:out value="${item.description}" /> </td>
		    			<td> <c:out value="${item.quantity}" /> </td>
		    			<td>
		    			<a href="/edit/${item.id}"><button class="submit">Update</button></a>
		    			<form action="/delete/${item.id}" method="post">
		    				<input type="hidden" name="_method" value="delete" />
		    				<button id="delete">Delete</button>
		    			</form>
		    			</td>
		    		</tr>
		    		</c:forEach>
		    	</tbody>
		    
		    </table>
		    
		   	<a href="/add"><button class="submit">Add New Item</button></a>
		    
		</main>
	</div>
</body>
</html>