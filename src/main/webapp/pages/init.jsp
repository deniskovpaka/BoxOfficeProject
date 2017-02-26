<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script>
		window.onload = function() {
			document.getElementById("initJsp").submit();
		}
	</script>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Initialize request</title>
</head>
	<body>
		<form id="initJsp" method="POST" action="BoxOfficeController">
			<input type="hidden" name="jspRequest" value="initializeRequest" />
			<input type="submit" name="continue" value="continue">
		</form>
	</body>
</html>