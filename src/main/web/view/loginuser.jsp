<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
%>

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
        &nbsp;
        <button onclick= "location.href = '/index.html' "> back </button>
        <br> <br>

        <form action="/loginuser" class="tab" method="post">
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
