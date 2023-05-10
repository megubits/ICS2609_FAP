/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/**
 *
 * @author Isha Quingquing
 */
public class SongDisplayer extends HttpServlet {

    Connection conn;

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
        String song = request.getParameter("song");
        session.setAttribute("songNo", song);

        if (song != null) {
            try{
            if (conn != null) {
                String querysong = "SELECT TITLE, ARTIST FROM SONGLIST WHERE NUMBER = ?";
                PreparedStatement pssongdet = conn.prepareStatement(querysong);
                pssongdet.setString(1, song);
                ResultSet details = pssongdet.executeQuery();
                
                while (details.next()){
                    session.setAttribute("title",details.getString("TITLE"));
                    session.setAttribute("artist", details.getString("ARTIST"));
                }
            }
            } catch (SQLException sqle) {
                    System.out.println("no connection :(");
                }
        }
        
        response.sendRedirect("SongPage.jsp");
        
    }

}
