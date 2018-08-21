<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>

<html>

    <style type="text/css">
        .tab {margin-left: 40%}
    </style>

    <head>
        <title> update note </title>
    </head>

    <body>
        <header>
            <h2 class="tab"> update note page </h2>
        </header>

        <form action="/updatenote" class="tab" method="post">

            <input type="text" name="name" value="${currentnote.name}"> Name <br> <br>
            <input type="text" name="description" value="${currentnote.description}"> Description <br> <br>
            <input type="text" name="status" value="${currentnote.status}"> Status <br> <br>

            <input type="submit" value="submit">
            <br><br>

            <c:if test="${help1 != null}" >
                <strong style="color: red">
                    <c:out value="${help1}"/>
                </strong>
            </c:if>

        </form>

    </body>
</html>
