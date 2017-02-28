<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Initialize request</title>
</head>
    <body>
        <h1>Choose Play:</h1>
        <form method="POST" action="BoxOfficeController">
            <input type="hidden" name="jspRequest" value="initializeRequest" />
            <table border = "0">
                <tr>
                    <td>Choice</td>
                    <td>
                        <select name = "jspUserInput">
                            <option value = "1">by Play's ID</option>
                            <option value = "2">by Play's name</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>