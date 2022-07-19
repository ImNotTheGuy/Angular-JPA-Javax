<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1>Hi Mom 😀 !</h1>

    <h2>Received doc ${ doc.id }, which has category ${ doc.category.name } and URL <a href="${ doc.url }">url</a></h2>
<div>
    <a href="/WednesdayProject/api/documentation">Documentation list</a> <br/>
    <a href="/WednesdayProject/api/category">Category list</a>
</div>
</body>
</html>