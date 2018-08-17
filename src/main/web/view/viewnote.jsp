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
            <h2 class="tab"> view note page </h2>
        </header>

        <p class="tab">
            <c:out value="${login}"/> &nbsp; : user online
        </p>

        <table border="1" cellpadding="5" class="tab">
            <header>
                <h2 class="tab"> Notes of users </h2>
            </header>

            <button class="tab" onclick= "location.href = '/view/addnote.jsp' "> Add note </button>
            <br><br>

            <tr>
                <th>DEL</th>
                <th>
                    <a href = "/sortnote?id=1"> ID </a>
                </th>
                <th>
                    <a href = "/sortnote?id=2"> NAME </a>
                </th>
                <th>
                    <a href = "/sortnote?id=3"> Description </a>
                </th>
                <th>
                    <a href = "/sortnote?id=4"> Create Date </a>
                </th>
                <th>
                    <a href = "/sortnote?id=5"> Status </a>
                </th>
            </tr>


            <c:forEach items="${listnote}" var="list" varStatus="status">
                <tr>
                    <td>
                        <a href = "/deletenote?id=${list.id}"> delete </a>
                    </td>
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