<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title> MaPage </title>
    </head>
    <body>
        <h1>Hello World</h1>
        <%
            for (int i = 1; i <= 9; i++)
                out.println(i);
        %>
        avec quelques accents à é è ù
    </body>
</html>