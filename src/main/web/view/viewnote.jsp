<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>

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
            <c:out value="${user.login}"/> &nbsp; : user online
        </p>

        <table border="1" cellpadding="5" class="tab">

            <header>
                <h2 class="tab"> Notes of users </h2>
            </header>

            <button class="tab" onclick= "location.href = '/view/addnote.jsp' "> Add note </button>
            <br><br>

            <tr>
                <th>DEL \ SORT</th>
                <th>
                    <button onclick="location.href = '/sortnote?id=1'"> ID </button>
                </th>
                <th>
                    <button onclick="location.href = '/sortnote?id=2'"> NAME </button>
                </th>
                <th>
                    <button onclick="location.href = '/sortnote?id=3'"> DESCRIPTION </button>
                </th>
                <th>
                    <button onclick="location.href = '/sortnote?id=4'"> CREATE DATE </button>
                </th>
                <th>
                    <button onclick="location.href = '/sortnote?id=5'"> STATUS </button>
                </th>
            </tr>

            <c:set var="count" scope="page" value="0" />

            <c:if test="${pointer == null}">
                <c:set var="point" scope="page" value="0" />
            </c:if>
            <c:if test="${pointer != null}">
                <c:set var="point" scope="page" value="${pointer}" />
            </c:if>

                <c:forEach items="${listnote}" var="list" varStatus="status">
                    <c:if test="${count < ( point + 10 )}">
                        <c:if test="${count >= point}">
                            <tr>
                                <td>
                                    <a href = "/deletenote?id=${list.id}"> delete </a>
                                </td>
                                <td><c:out value="${list.id}" /></td>
                                <td><c:out value="${list.name}" /></td>
                                <td><c:out value="${list.description}" /></td>
                                <td><c:out value="${list.createDate}" /></td>
                                <td><c:out value="${list.status}" /></td>
                                <td>
                                    <a href = "/updatenote?id=${list.id}"> update </a>
                                </td>
                            </tr>
                        </c:if>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:if>
                </c:forEach>
        </table>

        <c:set var="pointer" value="${count}" scope="session"/>
        <c:out value="${pointer}"/>


        <br>
        <button class="tab" onclick= "location.href = '/index.html' "> Log out </button> &nbsp;
        <button onclick= "location.href = '/selectnote?id=2' "> <<< </button> &nbsp;
        <button onclick= "location.href = '/selectnote?id=1' "> >>> </button>

    </body>

</html>