/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Isha Quingquing
 */
public class SongNav extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        session.setAttribute("link", (Boolean) false);
        session.setAttribute("nav", (Boolean) true);
        session.setAttribute("songDirect", (String) request.getParameter("songDirect"));
        response.sendRedirect("SongDisplayer");

    }
}
