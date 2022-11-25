<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image</title>
</head>
<body>
   <c:forEach var="image" items="${imageList }">
    
    <div>
     <c:out value="${image.id }"> </c:out>
    </div>
     <div>
     <img src="${image.name }">
    </div>
    
   
   </c:forEach>
   
   <a href="createimage.jsp">create</a>
</body>
</html>