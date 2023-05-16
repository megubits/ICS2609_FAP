<%-- 
    Document   : SongPage
    Created on : 05 9, 23, 7:15:33 PM
    Author     : Isha Quingquing
--%>

<%@page import="java.io.*, java.util.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=session.getAttribute("title")%></h1>
        <h2><%=session.getAttribute("artist")%></h2>
        <%

            InputStream file = getServletContext().getResourceAsStream("/WEB-INF/SongLyrics/" + session.getAttribute("songNo") + ".txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();%>
        <p align="center"><font color="orange"> <%= data%></font></p>
            <%};

                reader.close();
            %>
        <form action="SongLink" action="get">           
            <input type="submit" align="center" name="songNo" value="1">
            <input type="submit" align="center" name="songNo" value="2">
            <input type="submit" align="center" name="songNo" value="3"> 
        </form>

        <form action="SongNav" action="get">
            <input type="submit" align="center" name="songDirect" value="goback">
            <input type="submit" align="center" name="songDirect" value="goforward">
        </form>

        <form action="LyricsPDFServlet" action="get">
            <input type="submit" align="center" value="Print Lyrics!">
        </form>
    </body>
</html>
