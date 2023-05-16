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
        <title>Member Page</title>
    </head>
    <body>
        <img src="IMG/ProfilePictures/<%=session.getAttribute("userid")%>.jpg" width="100%">
        <h1>Welcome back, <%=session.getAttribute("username")%></h1>
        <form action="SongLink" action="get">
            <input type="submit" align="center" name="songNo" value="SONG_1">
            <input type="submit" align="center" name="songNo" value="SONG_2">
        </form>
        <form action="SongLink" action="get">
            <input type="submit" align="center" name="songNo" value="SONG_1">
            <input type="submit" align="center" name="songNo" value="SONG_2">
        </form>
    </body>
</html>
