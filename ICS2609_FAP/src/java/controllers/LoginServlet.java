/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.*;

/**
 *
 * @author Isha Quingquing
 */
public class LoginServlet extends HttpServlet {
    
    /*private static byte[] key = {'i', 'c', 's', '_', '2', '6', '0', '9', 'i', 'c', 's', '_', '2', '6', '0', '9',};*/
    Connection conn;
    int attempt = 0;
    private static byte[] key;
    String cipherString;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        key = config.getServletContext().getInitParameter("cryptoKey").getBytes();
        cipherString = config.getServletContext().getInitParameter("cryptoCipher");

        try {
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
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = "x";
        String userroleConfirm = "x";
        String decryptedString = null;

        try {
            if (conn != null) {
                String querypassword = "SELECT PASSWORD FROM USERS WHERE EMAIL = ?";
                PreparedStatement pspassword = conn.prepareStatement(querypassword);
                pspassword.setString(1, username);
                ResultSet rspassword = pspassword.executeQuery();
                while (rspassword.next()) {
                    passwordConfirm = rspassword.getString("Password");
                }

                pspassword.close();
            } else {
                response.sendRedirect("ConnectionError.jsp");
            }
        } catch (SQLException sqle) {
            response.sendRedirect("ConnectionError.jsp");
        }

        try {
            Cipher cipher = Cipher.getInstance(cipherString);
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedString = new String(cipher.doFinal(Base64.decodeBase64(passwordConfirm)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        HttpSession session = request.getSession();
        boolean passwordTrial = password.equals(decryptedString);

        if (passwordTrial == true) {
            if (attempt >= 2) {
                response.sendRedirect("ThreeErrorPassword.jsp");
            } else {
                session.setAttribute("username", username);
                response.sendRedirect("MemberAccount.jsp");
            } 
            
        } else {
            if (attempt >= 2){
                response.sendRedirect("ThreeErrorPassword.jsp");
                System.out.println(session.getAttribute("counter") + " " + attempt);
            }
            
            else if (session.getAttribute("counter") == null){
                attempt = 1;
                session.setAttribute("counter", "1");
                response.sendRedirect("ErrorPassword.jsp");
                System.out.println(session.getAttribute("counter") + " " + attempt);
            }
            
            else {
                attempt++;
                session.setAttribute("counter", attempt);
                response.sendRedirect("ErrorPassword.jsp");
                System.out.println(session.getAttribute("counter") + " " + attempt);
            }
        }
    }
}
