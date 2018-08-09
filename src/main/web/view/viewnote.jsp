<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <style type="text/css">
        .tab {margin-left: 40%}
    </style>

    <head>
        <title> view note </title>
    </head>

    <body>
        <header>
            <h2 class="tab">  view note page </h2>
        </header>

        <p class="tab">
            <c:out value="${login}"/> &nbsp; : user online
        </p>

        <table border="1" cellpadding="5" class="tab">
            <header>
                <h2 class="tab"> Notes of users </h2>
            </header>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Create Date</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${notelist}" var="list" varStatus="status">
                <tr>
                    <td><c:out value="${list.id}" /></td>
                    <td><c:out value="${list.name}" /></td>
                    <td><c:out value="${list.description}" /></td>
                    <td><c:out value="${list.createDate}" /></td>
                    <td><c:out value="${list.status}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>

</html>