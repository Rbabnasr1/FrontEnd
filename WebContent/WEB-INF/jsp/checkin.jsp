<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

     <form action = "doCheckin" method = "post">
	 Place:<input type="text" name = "place" /> <br>
	 <br>
	<input type="submit" value = "Check-In" />
    </form>
    <br>
    <form action = "doLikeCheckin" method = "post">
	 notified user: <input type="text" name = "notifieduser" /> <br>
	 <br>
	 place :<input type="text" name = "place" /> <br>
	 <br>
	<input type="submit" value = "Like Check-In" />
    </form>
    <br>
    <form action = "doCommentCheckin" method = "post">
	 notified user: <input type="text" name = "notifieduser" /> <br>
	 <br>
	 place :<input type="text" name = "place" /> <br>
	 <br>
	 Comment :<input type="text" name = "comment" /> <br>
	 <br>
	<input type="submit" value = "Comment on a Check-In" />
    </form>
    
    <p> <a href="/FCISquareApp/app/home"> BACK</a>
   
</body>
</html>