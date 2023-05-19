<%-- 
    Document   : ArtistPage
    Created on : 05 16, 23, 10:59:58 PM
    Author     : Isha Quingquing
--%>

<%@page import="java.sql.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Connection conn;
            Class.forName(config.getServletContext().getInitParameter("jdbcClassName"));
            //System.out.println("jdbcClassName: " + config.getInitParameter("jdbcClassName"));
            String username = config.getServletContext().getInitParameter("dbUserName");
            String password = config.getServletContext().getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(config.getServletContext().getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getServletContext().getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getServletContext().getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getServletContext().getInitParameter("databaseName"));
            conn
                    = DriverManager.getConnection(url.toString(), username, password);

            if (conn != null) {
                String queryartistsongs = "SELECT TITLE, SONG_ID FROM SONGLIST WHERE ARTIST_ID = ?";
                PreparedStatement psartistsongs = conn.prepareStatement(queryartistsongs);
                psartistsongs.setString(1, (String) session.getAttribute("artistID"));
                ResultSet rsartistsongs = psartistsongs.executeQuery();
                while (rsartistsongs.next()) {%>
        <a href=SongLink?songNo=<%=rsartistsongs.getString("SONG_ID")%>>
            <p><%=rsartistsongs.getString("TITLE")%></p>
        </a>
        <a href="UserFave?songFaveId=<%=rsartistsongs.getString("SONG_ID")%>">
           Fave It!
        </a>
        <%    }
                    psartistsongs.close();
                }%>    
                
        <h1>Hello World!</h1>
    </body>
</html>
