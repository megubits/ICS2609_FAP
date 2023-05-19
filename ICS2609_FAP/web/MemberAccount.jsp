<%-- 
    Document   : SelfAccount
    Created on : 05 9, 23, 7:14:14 PM
    Author     : Isha Quingquing
--%>

<%@page import="java.sql.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome!</title>
    </head>
    <body>

        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

            if (session.getAttribute("userid") == null) {
                response.sendRedirect("LoginPage.jsp");
            } else if (session.getAttribute("userid") != null) {
        %>

<!-- <img id="pfp" src="IMG/ProfilePictures/<%--=session.getAttribute("userid")--%>.jpg" height="100px" width="100px">-->
        <h1>Welcome back, <%=session.getAttribute("username")%></h1>
        <%System.out.println(session.getAttribute("userid"));%>
        <form action="SongLink" action="get">
            <input type="submit" align="center" name="songNo" value="SONG_1">
            <input type="submit" align="center" name="songNo" value="SONG_2">
        </form>
        <form action="SongLink" action="get">
            <input type="submit" align="center" name="songNo" value="SONG_1">
            <input type="submit" align="center" name="songNo" value="SONG_2">
        </form>
        <a href="MemberLanding.jsp">Main Page</a>
        <a href="LogoutServlet">Logout</a><%}%>

        <h1> Faves </h1>
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

            String title = null;
            String artist = null;
            String songId = null;
            String artistId = null;

            if (conn != null) {

                String table = session.getAttribute("userid") + "_FAVES";

                DatabaseMetaData dbm = conn.getMetaData();
                ResultSet tables = dbm.getTables(null, null, table, null);

                if (!tables.next()) {%>
        <h2>You don't have any favorites yet!</h2>
        <%} else {
            Statement queryfaves = conn.createStatement();
            ResultSet rsfaves = queryfaves.executeQuery("SELECT SONG_ID FROM " + table);

            while (rsfaves.next()) {

                songId = rsfaves.getString("SONG_ID");
                Statement querySongDetails = conn.createStatement();
                ResultSet rssong = querySongDetails.executeQuery("SELECT TITLE, ARTIST_ID FROM SONGLIST "
                        + "WHERE SONG_ID = '" + songId + "'");

                while (rssong.next()) {
                    title = rssong.getString("TITLE");
                    artistId = rssong.getString("ARTIST_ID");

                }

                Statement queryArtistDetails = conn.createStatement();
                ResultSet rsartist = queryArtistDetails.executeQuery("SELECT ARTIST_NAME FROM ARTISTS WHERE "
                        + "ARTIST_ID = '" + artistId + "'");

                while (rsartist.next()) {
                    artist = rsartist.getString("ARTIST_NAME");
                }


        %>          
        <a href=SongLink?songNo=<%=songId%>>
            <p><%=title%></p>
        </a>
        <a href=ArtistPage?artistID=<%=artistId%>>
            <p><%=artist%></p>
        </a>

        <%   }
                }
            }%>
    </body>
</html>
