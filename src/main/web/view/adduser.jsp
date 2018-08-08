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
            <h2 class="tab">  add user page </h2>
        </header>

        <form action="/adduser" class="tab" method="post">
            <input type="text" name="name" value="add name" size="20" maxlength="10"> name
            <br><br>
            <input type="text" name="login" value="add login" size="20" maxlength="10"> login &nbsp;&nbsp;

                <c:if test="${message != null}">
                <i style="color: red">
                    <c:out value="${message}" />
                    <br><br>
                </i>
                </c:if>

                <c:if test="${message == null}" >
                    <br><br>
                </c:if>

            <input type="password" name="password" value="" size="20" maxlength="32"> password
            <br><br>
            <input type="submit" value="submit"/>
        </form>

    </body>

</html>
