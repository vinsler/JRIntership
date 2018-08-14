<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <head>
        <title> login user </title>
    </head>

    <style type="text/css">
        .tab {margin-left: 40%}
    </style>

    <body>
        <header>
            <h2 class="tab"> login user page </h2>
        </header>

        <button class="tab" onclick= "location.href = '/showalluser' "> Show all user </button>
        <br> <br>

        <form action="/loginuser" class="tab" method="get">
            <input type="text" name="login" value="${log}" size="20" maxlength="10"> login <br><br>
            <input type="password" name="password" size="20" maxlength="32"> password <br><br>
            <input type="submit" value="submit"/>

            <c:if test="${message != null}">
                <strong style="color: red">
                    <c:out value="${message}" />
                </strong>
            </c:if>

        </form>
    </body>

</html>
