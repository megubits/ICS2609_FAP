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
        <% try {
                File songLyrics = new File("C:/Users/Isha Quingquing/Documents/NetBeansProjects/ICS2609_FAP/SongLyrics/" + session.getAttribute("songNo") + ".txt");
                Scanner reader = new Scanner(songLyrics);

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();%>
        <p align="center"><font color="orange"> <%= data%></font></p>
            <%};

                    reader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occured.");
                }%>
    </body>
</html>
