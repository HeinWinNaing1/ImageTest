<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action="image" method="post" enctype="multipart/form-data">
 		<input type="hidden" name="mode" value="CREATE">
        <div>
            <input type="file" id="image" name="image">
        </div>
        <div>
            <input type="submit">
        </div>

    </form>
</body>
</html>