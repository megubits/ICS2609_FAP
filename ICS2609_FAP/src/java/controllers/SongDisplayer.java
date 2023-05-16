/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 *
 * @author Isha Quingquing
 */
public class SongDisplayer extends HttpServlet {

    Connection conn;
    HashMap<Integer, String> history = new HashMap<>();
    int historyCount = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            Class.forName(config.getServletContext().getInitParameter("jdbcClassName"));
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
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String songNo = (String) session.getAttribute("songNo");

        if ((Boolean) session.getAttribute("nav") == true && (Boolean) session.getAttribute("link") == false) {

            if ("goback".equals(session.getAttribute("songDirect"))) {
                if (historyCount == 1) {
                    System.out.println("nothing backwards!");
                } else {
                    historyCount--;
                    session.setAttribute("songNo", history.get(historyCount));
                    System.out.println("going back " + (String) session.getAttribute("songDirect"));
                }
            } else if ("goforward".equals(session.getAttribute("songDirect"))) {
                if (historyCount == history.size()) {
                    System.out.println("nothing forwards");
                } else {
                    historyCount++;
                    session.setAttribute("songNo", history.get(historyCount));
                    System.out.println("going forward " + (String) session.getAttribute("songDirect"));
                }
            }

            System.out.println("nav status: " + session.getAttribute("nav")
                    + "\n link status: " + session.getAttribute("link")
                    + "\n songDirect status: " + session.getAttribute("songDirect"));

        } else if ((Boolean) session.getAttribute("link") == true && (Boolean) session.getAttribute("nav") == false) {

            if (historyCount != history.size()) {
                int loopHistoryCount = historyCount;
                int loopHistorySize = history.size();

                while (loopHistoryCount != loopHistorySize) {
                    loopHistoryCount++;
                    history.remove(loopHistoryCount);
                }

            }

            historyCount++;
            history.put(historyCount, songNo);
            session.setAttribute("songNo", songNo);

            System.out.println("nav status: " + session.getAttribute("nav")
                    + "\n link status: " + session.getAttribute("link"));
        } else {
            System.out.println("error");
        }

        try {
            if (conn != null) {
                String querysong = "SELECT TITLE, ARTIST_ID FROM SONGLIST WHERE SONG_ID = ?";
                PreparedStatement pssongdet = conn.prepareStatement(querysong);
                pssongdet.setString(1, (String) session.getAttribute("songNo"));
                ResultSet details = pssongdet.executeQuery();

                while (details.next()) {
                    session.setAttribute("title", details.getString("TITLE"));
                    session.setAttribute("artist", details.getString("ARTIST_ID"));
                }
            }
        } catch (SQLException sqle) {
            System.out.println("no connection :(");
        }
        
        try {
            if (conn != null) { 
                String querysong = "SELECT ARTIST_NAME FROM ARTISTS WHERE ARTIST_ID = ?";
                PreparedStatement psartistdet = conn.prepareStatement(querysong);
                psartistdet.setString(1, (String) session.getAttribute("artist"));
                ResultSet details = psartistdet.executeQuery();

                while (details.next()) {
                    session.setAttribute("artist", details.getString("ARTIST_NAME"));
                }
            }
        } catch (SQLException sqle) {
            System.out.println("no connection :(");
        }

        System.out.println("Where are we?: " + historyCount
                + "\n Song Playing: " + session.getAttribute("songNo")
                + "\n HashMap Status: " + history);
        
        response.sendRedirect("SongPage.jsp");
    }

}
