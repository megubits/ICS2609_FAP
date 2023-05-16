<%-- 
    Document   : SelfAccount
    Created on : 05 9, 23, 7:14:14 PM
    Author     : Isha Quingquing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome back, <%=session.getAttribute("username")%></h1>
        <form action="SongLink" action="get">
            <input type="submit" align="center" name="songNo" value="1">
        <!-- </form>
        <form action="SongDisplayer" action="get">-->
            <input type="submit" align="center" name="songNo" value="2">
        </form>
    </body>
</html>
