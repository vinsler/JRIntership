<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title> show user </title>
</head>

<style type="text/css">
    .tab {margin-left: 40%}
</style>

<body>

    <h2 class="tab">
        <a href="/view/adduser.jsp"> Add User </a> <br>
        <a href="/view/loginuser.jsp"> Login user </a>
    </h2>

    <form>
        <table border="1" cellpadding="5" class="tab">
            <header>
                <h2 class="tab" List of users </h2>
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

    <h2 class="tab">
        <a href="/index.html"> Get Start Page </a>
    </h2>

</body>

</html>
