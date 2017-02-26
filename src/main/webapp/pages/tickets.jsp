<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tickets list</title>
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
    <h4>Tickets table state:</h4>
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Ticket's PlayID</th>
            <th width="120">User/Visitor Name</th>
            <th width="220">Ticket's numbers</th>
        </tr>
        <%-- get list of all plays --%>
        <%
            String[] playNamesList = (String[]) request.getAttribute("playNamesList");
            int namePosition = 0;
        %>
        <c:forEach items="${ticketTableList}" var="ticket">
            <tr>
                <td>${ticket.id}</td>
                <td><%= playNamesList[namePosition++] %></td>
                <td>${ticket.visitorName}</td>
                <td>
                    <c:forEach items="${ticket.ticketNumber}" var="tNumber">
                        <c:out value="${tNumber}"/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/boxoffice">Continue</a>
    </body>
</html>