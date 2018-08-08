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
            <h2 align="center">  login user page </h2>
        </header>

        <form action="/loginuser" class="tab" method="get">
            <input type="text" name="login" value="enter login" size="20" maxlength="10"> login <br><br>
            <input type="password" name="password" value="" size="20" maxlength="32"> password <br><br>
            <input type="submit" value="submit"/>
        </form>
    </body>

</html>
