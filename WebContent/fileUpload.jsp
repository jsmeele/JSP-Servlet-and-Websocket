<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java</title>
</head>
<body>
	<h1>Hello JSP and Servlet!</h1>
	<form action="uploadFile" method="post" enctype="multipart/form-data">
    Select File to Upload: <input type="file" name="filename">
    <input type="submit" value="Call Servlet" />
</form>
</body>
</html>