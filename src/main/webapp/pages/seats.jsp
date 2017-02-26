<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script language="javascript">
			function checkUserInput(form) {
				var uName = document.getElementById('uName').value;
				return checkString(uName) && checkCheckboxArray(form, "seatNumbers");
			}
			function checkString(str) {
				// validation fails if the input is blank
				if(str == "") {
					alert("Error: Input is empty!");
					return false;
				}
				// regular expression to match only alphanumeric characters and spaces
				var re = /^[\w ]+$/;
				// validation fails if the input doesn't match our regular expression
				if(!re.test(str)) {
					alert("Error: Input contains invalid characters!");
					return false;
				}
				// validation was successful
				return true;
			}
			function checkCheckboxArray(form, arrayName) {
				var retval = new Array();
				for(var i = 0; i < form.elements.length; i++) {
					var el = form.elements[i];
					if(el.type == "checkbox"
						&& el.name == arrayName
						&& el.checked) {
						retval.push(el.value);
					}
				}
				// validation fails if the input doesn't match at least one seat
				if (retval.length == 0) {
					alert("Error: Please check the seats!");
					return false;
				} else
					return true;
			}
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Seats list</title>
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
		<h4>Available seats</h4>
		<form onsubmit="return checkUserInput(this);" method="POST" action="BoxOfficeController">
			<% Integer availableSeatNumber = 0; %>
			<c:forEach items="${seatsList}" var="isReservedSeat">
				<%-- show seat number only when seat is not reserved --%>
				<c:if test="${!isReservedSeat}">
					<h5>
						<%= availableSeatNumber %> <input type="checkbox" name="seatNumbers" value=<%= availableSeatNumber %> >
					</h5>
				</c:if>
				<% availableSeatNumber++; %>
			</c:forEach>
			<h4>Add Visitor/User Name Please)</h4>
			<table>
				<tr>
					<td>Visitor/User Name:</td>
					<td><input type="text" id="uName" name="user_name"></td>
				</tr>
			</table>
			<input type="hidden" name="jspRequest" value="seatsRequest" />
			<input type="hidden" name="play_id" value="${play_id}" />
			<input type="submit" value="Submit" />
		</form>
	</body>
</html>