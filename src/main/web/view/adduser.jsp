<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <head>
        <title> add user </title>
    </head>

    <style type="text/css">
        .tab {margin-left: 40%}
    </style>

    <body>
        <form action="/adduser" class="tab" method="post">
            <input type="text" name="name" value="add name" size="20" maxlength="10"> name <br><br>
            <input type="text" name="login" value="add login" size="20" maxlength="10"> login <br><br>
            <input type="password" name="password" value="" size="20" maxlength="32"> password <br><br>
            <input type="submit" value="submit"/>
        </form>
    </body>

</html>
