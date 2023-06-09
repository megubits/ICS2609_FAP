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
public class UserFave extends HttpServlet {

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

        if (session.getAttribute("userid") == null) {
            response.sendRedirect("SignUp.jsp");
        } else if (session.getAttribute("userid") != null) {
            try {
                if (conn != null) {
                    String table = (String) session.getAttribute("userid") + "_FAVES";

                    DatabaseMetaData dbm = conn.getMetaData();
                    ResultSet tables = dbm.getTables(null, null, table, null);

                    if (!tables.next()) {
                        Statement querytableadd = conn.createStatement();
                        querytableadd.executeUpdate("CREATE TABLE " + table + "(SONG_ID VARCHAR(200))");
                        querytableadd.close();
                    }
                    
                    Statement queryfaveadd = conn.createStatement();
                    queryfaveadd.executeUpdate("INSERT INTO "
                            + (String) session.getAttribute("userid") + "_FAVES"
                            + " VALUES('" + (String) request.getParameter("songFaveId") + "')");
                    queryfaveadd.close();
                    
                    response.sendRedirect("ArtistPage.jsp");

                } else {
                    System.out.println("no connection :(");
                }
            } catch (SQLException sqle) {
                System.out.println("no connection :(");
            }
        }
        System.out.println(request.getParameter("songFaveId") + " " + session.getAttribute("userid") + "_FAVES");
    }
}
