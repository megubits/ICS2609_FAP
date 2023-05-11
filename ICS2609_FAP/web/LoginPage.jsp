<%-- 
    Document   : LoginPage
    Created on : 05 9, 23, 7:13:03 PM
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
        <form action="LoginServlet" method="post">
                <label>Username: </label>   
                <input type="text" placeholder="Enter Username" name="username" required><br><br>  
                <label>Password: </label>
                <input type="password" placeholder="Enter Password" name="password" required><br><br>
                <input type="submit" align="center" value="Login">               
        </form>
        <form action="SongDisplayer" action="get">
            <input type="submit" align="center" name="song" value="1">
        <!-- </form>
        <form action="SongDisplayer" action="get">-->
            <input type="submit" align="center" name="song" value="2">
        </form>
    </body>
</html>
