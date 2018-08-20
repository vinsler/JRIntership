<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
%>

<html>

<head>
    <title> show user </title>
</head>

<style type="text/css">
    .tab {margin-left: 40%}
</style>

<body>
    <header>
        <h2 class="tab">  show all user page </h2>
    </header>

    <h2 class="tab">
        <button onclick="location.href='/view/adduser.jsp' "> Add User </button> &nbsp;
        <button onclick="location.href ='/view/loginuser.jsp' "> Login user </button>
    </h2>

    <form>
        <table border="1" cellpadding="5" class="tab">
            <header>
                <h2 class="tab"> List of users </h2>
            </header>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
            </tr>
                <c:forEach items="${listuser}" var="list" varStatus="status">
                    <tr>
                        <td><c:out value="${list.id}" /></td>
                        <td><c:out value="${list.name}" /></td>
                        <td><c:out value="${list.login}" /></td>
                    </tr>
                </c:forEach>
        </table>
    </form>

</body>

</html>
