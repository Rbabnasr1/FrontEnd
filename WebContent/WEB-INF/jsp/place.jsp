<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>


     <form action = "doAddPlace" method = "post">
	Place Name:  <input type="text" name = "name" /> <br>
	 <br>
	 Place Description: <input type="text" name = "descr" /> <br>
	 <br>
	 <input type="submit" value = " Add place" />
	 <br>
	 </form>
	 <form action = "doSavePlace" method = "post">
	 <br>
	 Place Name: <input type="text" name = "place" /> <br>
	  <br>
	 <input type="submit" value = "Save place" />
	 </form>
	  <p> <a href="/FCISquareApp/app/home"> BACK</a>

</body>
</html>