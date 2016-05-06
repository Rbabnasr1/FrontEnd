<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
        
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>


		        <c:forEach items="${it}" var="item" >
   				<p><c:out value="${item}"></c:out><p>
			    </c:forEach>
			    <br>
	<p> To sort home page by place <a href="/FCISquareApp/app/history/Psort"> here</a> <br>
	<p> To sort home page by User Name <a href="/FCISquareApp/app/history/Usort"> here</a>
	 <p> <a href="/FCISquareApp/app/home"> BACK</a>
</body>
</html>