<%-- 
    Document   : PublicAccount
    Created on : 05 9, 23, 7:14:41 PM
    Author     : Isha Quingquing
--%>

<%@page import="java.sql.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/nav.css">
        <link rel="stylesheet" type="text/css" href="CSS/landing.css">
        <title>Song Artists</title>
    </head>
    <body>
        <div class="top">
            <h1>SONG ARTISTS</h1>
        </div>
        <div class="navbar">
            <a href="index.html">Home</a>
            <a href="PublicLanding.jsp">Artists</a>
            <a href="about.jsp">About</a>
            <a href="developers.jsp">Developers</a>
            <%if (session.getAttribute("userid") == null) {%>
            <a href="SignUp.jsp">Sign-Up</a>
            <%} else if (session.getAttribute("userid") != null) {%>
            <a href="LogoutServlet">Logout</a>
            <%}%>

        </div>

        <div class="gallery">

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
                    String queryartist = "SELECT ARTIST_ID, ARTIST_NAME FROM ARTISTS";
                    PreparedStatement psartist = conn.prepareStatement(queryartist);
                    ResultSet rsartist = psartist.executeQuery();
                    while (rsartist.next()) {%>
            <figure>
                <a href="ArtistPage?artistID=<%=rsartist.getString("ARTIST_ID")%>">
                    <img src="IMG/<%=rsartist.getString("ARTIST_ID")%>.jpg" width="100%">
                    <figcaption><%=rsartist.getString("ARTIST_NAME")%></figcaption>
                </a>
            </figure>
            <%    }
                    psartist.close();
                }%>

            <!-- <figure>
                <a href="">
                    <img src="IMG/mcr.jpg" width="100%">
                    <figcaption>MY CHEMICAL ROMANCE</figcaption>
                </a>
            </figure>

            <figure>
                <a href="">
                    <img src="IMG/paramore.jpg" width="100%">
                    <figcaption>PARAMORE</figcaption>            
                </a>
            </figure>

            <figure>
                <a href="">
                    <img src="IMG/fob.jpg" width="100%">
                    <figcaption>FALL OUT BOY</figcaption>            
                </a>
            </figure>

            <figure>
                <a href="">
                    <img src="IMG/adele.jpg" width="100%">
                    <figcaption>ADELE</figcaption>            
                </a>
            </figure>

            <figure>
                <a href="">
                    <img src="IMG/edsheeran.jpg" width="100%">
                    <figcaption>ED SHEERAN</figcaption>            
                </a>
            </figure>

            <figure>
                <a href="">
                    <img src="IMG/taylorswift.jpg" width="100%">
                    <figcaption>TAYLOR SWIFT</figcaption>            
                </a>
            </figure> -->
        </div>
    </body>
</html>
