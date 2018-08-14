<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <style type="text/css">
        .tab {margin-left: 40%}
    </style>

    <head>
        <title> add user </title>
    </head>

    <body>
        <header>
            <h2 class="tab"> add user page </h2>
        </header>

        <button class="tab" onclick= "location.href = '/showalluser' "> Show all user </button>
        &nbsp;
        <c:if test="${message != null}">
            <strong style="color: red">
                <c:out value="${message}" />
            </strong>
            <br> <br>
        </c:if>

        <c:if test="${message == null}">
            <br> <br>
        </c:if>

        <form action="/adduser" class="tab" method="post">
            <input type="text" name="name" value="${name}" size="20" maxlength="10"> name
            <br><br>
            <input type="text" name="login" value="${login}" size="20" maxlength="10"> login
            <br><br>
            <input type="password" name="password" value="${password}" size="20" maxlength="32"> password
            <br><br>
            <input type="submit" value="submit"/>
            <br><br>
            <c:if test="${help1 != null}">
                <em style="color: green">
                    <br>
                    <c:out value="${help1}" />
                    <br>
                    <c:out value="${help2}" />
                </em>
            </c:if>
        </form>
        <br>

    </body>

</html>

