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
<title>Title Here</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <h1>All expenses</h1>
        <div class="container ">
        
            <c:forEach var="i" items="${expenses}">
        	<p>Expense Name: <c:out value="${i.expenseName}"></c:out></p>
            <p>vendor: <c:out value="${i.vendor}"></c:out></p>
            <p>Description: <c:out value="${i.description}"></c:out></p>
            <p>Amount: <c:out value="${i.amount}"></c:out></p>
            <p>Owner: <c:out value="${i.owner.firstName} ${i.owner.lastName}"></c:out></p>
            <a href="/editExpense/${i.id}">Update</a>
            <a href="/delete/${i.id}">Delete</a>
            <a href="/oneExpense/${i.id}">View One</a>
           
        </c:forEach>
        <br>
        <a href="/newOwner">New Owner</a>
        </div>	
  
   
        
        <h1>Expense Form</h1>
<form:form action="/processExpense" method="post" modelAttribute="expense">
    <p>
        <form:label path="expenseName">Expense:</form:label>
        <form:errors path="expenseName"/>
        <form:input path="expenseName"/>
    </p>
    <p>
        <form:label path="vendor">Vendor:</form:label>
        <form:errors path="vendor"/>
        <form:input path="vendor"/>
    </p>
    <p>
        <form:label path="description">Description:</form:label>
        <form:errors path="description"/>
        <form:input path="description"/>
    </p>
    <p>
        <form:label path="amount">Amount:</form:label>
        <form:errors path="amount"/>     
        <form:input type="number" path="amount"/>
    </p>    
    
    <form:select path="owner">
   	<c:forEach var="i" items="${allOwners }">
   		<form:option value="${i.id }">${i.firstName } ${i.lastName }</form:option>
    </c:forEach>
    </form:select>
    
    
    <input type="submit" value="Submit"/>
</form:form> 
    </div> <!-- End of Container -->
</body>
</html>