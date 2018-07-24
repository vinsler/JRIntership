<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <head>
        <title> login user </title>
    </head>

    <style type="text/css">
        .tab {margin-left: 40%}
    </style>

    <body>
        <form action="viewnote.jsp" class="tab" method="get">
            <input type="text" name="name" value="enter name" size="20" maxlength="10"> name <br><br>
            <input type="text" name="login" value="enter login" size="20" maxlength="10"> login <br><br>
            <input type="password" name="password" value="" size="20" maxlength="32"> password <br><br>
            <button onclick="location.href = '/loginuser' " > submit </button>
        </form>
    </body>

</html>
