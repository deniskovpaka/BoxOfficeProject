<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <script language="javascript">
            function checkUserInput() {
                var pId = document.getElementById('id').value;
                var playIds = document.getElementsByClassName('playId');
                var playNames = document.getElementsByClassName('playName');
                for(var i = 0; i < playNames.length; i++) {
                    var name = playNames[i].innerText;
                    if (pId.toUpperCase() === name.toUpperCase()) {
                        document.getElementById('id').value = playIds[i].innerText;
                        return true;
                    }
                }
                alert("Please be careful!!! Input play's name one more time.")
                return false;
            }
        </script>
        <title>Input play request</title>
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
        <h1>Play Names List</h1>
            <table id="table" class="tg">
                <tr>
                    <th width="40">PlayID</th>
                    <th width="120">PlayName</th>
                </tr>
                <c:forEach items="${playsList}" var="play">
                    <tr>
                        <td class="playId">${play.id}</td>
                        <td class="playName">${play.playName}</td>
                    </tr>
                </c:forEach>
            </table>
        <h1>Input a Play name</h1>
        <form onsubmit="return checkUserInput();" method="POST" action="BoxOfficeController">
            <input type="hidden" name="jspRequest" value="playsRequest" />
            <table>
                <tr>
                    <td>Please input the play's name :):</td>
                    <td><input type="text" id="id" name="jspUserInput" size="30" maxlength="30"></td>
                </tr>
                <tr>
                    <td align="right" colspan="2"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>