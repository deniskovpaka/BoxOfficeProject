<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script language="javascript">
		function checkUserInput() {
			var pId = parseInt(document.getElementById('id').value);
			var pListSize = parseInt(document.getElementById('size').value);
			if (pId < 1 || pId > pListSize
						|| isNaN(pId)) {
				alert('Please be careful. Input playId one more time.');
				return false;
			}
			return true;
		}
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Plays list</title>
		<style type="text/css">
			.tg {
				border-collapse: collapse;
				border-spacing: 0;
				border-color: #ccc;
			}
			.tg td {
				font-family: Arial, sans-serif;
				font-size: 14px;
				padding: 10px 5px;
				border-style: solid;
				border-width: 1px;
				overflow: hidden;
				word-break: normal;
				border-color: #ccc;
				color: #333;
				background-color: #fff;
			}
			.tg th {
				font-family: Arial, sans-serif;
				font-size: 14px;
				font-weight: normal;
				padding: 10px 5px;
				border-style: solid;
				border-width: 1px;
				overflow: hidden;
				word-break: normal;
				border-color: #ccc;
				color: #333;
				background-color: #f0f0f0;
			}
			.tg .tg-4eph {
				background-color: #f9f9f9
			}
		</style>
</head>
	<body>
		<h1>Plays List</h1>
		<table class="tg">
			<tr>
				<th width="40">PlayID</th>
				<th width="120">PlayName</th>
			<tr>
			<c:forEach items="${playsList}" var="play">
				<tr>
					<td>${play.id}</td>
					<td>${play.playName}</td>
				<tr>
			</c:forEach>
		</table>
		<h1>Choose a Play</h1>
			<form onsubmit="return checkUserInput();" method="POST" action="BoxOfficeController">
				<input type="hidden" name="jspRequest" value="playsRequest" />
				<input type="hidden" id="size" name="playListSize" value="${fn:length(playsList)}" />
				<table>
					<tr>
						<td>Please input the play ID:</td>
						<td><input type="text" id="id" name="jspUserInput" size="8" maxlength="8"></td>
					</tr>
					<tr>
						<td align="right" colspan="2"><input type="submit" value="Submit"></td>
					</tr>
				</table>
			</form>
	</body>
</html>