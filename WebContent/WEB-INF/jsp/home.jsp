<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
	<p>Welcome  ${it.name}  to your home page </p>
	
  
	<p> You can show your current position on map and update your position 
	     on our database from <a href="/FCISquareApp/app/showLocation"> here</a>
    <p> you can un follow a friend from <a href="/FCISquareApp/app/unfollow"> here</a>
    <p> you can get your last position from <a href="/FCISquareApp/app/getLastPosition"> here</a>
    <p> you can add or save a place from <a href="/FCISquareApp/app/place/addPlace"> here</a>
    <p> Show Notifications from  <a href="/FCISquareApp/app/notification/GetAllNotifications"> here</a>
    <p> you can check-in from  <a href="/FCISquareApp/app/checkin/checkin"> here</a>
    <p> Show HomePage from <a href="/FCISquareApp/app/doShowHomePage"> here</a>
    <p> Show History of actions from <a href="/FCISquareApp/app/history/showhistory"> here</a>
   	
	
      <form action = "doFollow" method = "post">
	  <input type="text" name="id2"><br>
	 <br>
	<input type="submit" value = "follow" />

</body>
</html>