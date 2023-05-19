/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.sql.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Isha Quingquing
 */
public class SignUp extends HttpServlet {

    Connection conn;
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
        HttpSession session = request.getSession();

        String newUsername = request.getParameter("username");
        String reUsername = request.getParameter("reusername");
        String newPassword = request.getParameter("password");
        String rePassword = request.getParameter("repassword");
        String captchaAnswer = (String) session.getAttribute("captchaAnswer");
        String encryptedPassword = null;
        String captchaGuess = request.getParameter("captchaGuess");
        String exists = null;
        int checknumber = 0;

        if (captchaGuess.equals(captchaAnswer)) {
            if (newUsername.equals(reUsername)) {
                if (newPassword.equals(rePassword)) {
                    try {
                        Cipher cipher = Cipher.getInstance(cipherString);
                        final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                        encryptedPassword = Base64.encodeBase64String(cipher.doFinal(newPassword.getBytes()));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                    try {
                        if (conn != null) {
                            String querydupe = "SELECT USER_ID FROM USERS WHERE EMAIL = ?";
                            PreparedStatement psdupe = conn.prepareStatement(querydupe);
                            psdupe.setString(1, newUsername);
                            ResultSet rsdupe = psdupe.executeQuery();
                            
                            /* checks if username is already used*/
                            while (rsdupe.next()) {
                                exists = rsdupe.getString("USER_ID");
                                System.out.println(exists);
                            }

                            psdupe.close();

                            if (exists == null) {
                                 /* checks number of rows for the userid of the new account*/
                                Statement stmt = conn.createStatement();
                                ResultSet checkNoRows = stmt.executeQuery("SELECT COUNT(*) FROM USERS");

                                while (checkNoRows.next()) {
                                    checknumber = checkNoRows.getInt(1);
                                }

                                checkNoRows.close();
                                
                                checknumber++;
                                String userId = "USER_" + checknumber;
                                String queryadd = "INSERT INTO USERS (USER_ID, EMAIL, PASSWORD) VALUES (?, ?, ?)";
                                PreparedStatement psadd = conn.prepareStatement(queryadd);
                                psadd.setString(1, userId);
                                psadd.setString(2, newUsername);
                                psadd.setString(3, encryptedPassword);
                                psadd.executeUpdate();

                                psadd.close();

                                session.setAttribute("approvedSignUp", "Success! Data was added to database.");
                                session.setAttribute("username", newUsername);
                                session.setAttribute("userid", userId);

                                if (session.getAttribute("warningSignUp") != null) {
                                    session.removeAttribute("warningSignUp");
                                }

                                System.out.println("added (supposedly)");
                                response.sendRedirect("MemberAccount.jsp");
                            } else {
                                session.setAttribute("warningSignUp", "Username already in use! Please try another.");
                                response.sendRedirect("SignUp.jsp");
                            }

                        } else {
                            response.sendRedirect("ConnectionError.jsp");
                        }
                    } catch (SQLException sqle) {
                        response.sendRedirect("ConnectionError.jsp");
                    }
                } else {
                    session.setAttribute("warningSignUp", "Password does not match! Please try again.");
                    response.sendRedirect("SignUp.jsp");
                }
            } else {
                session.setAttribute("warningSignUp", "Username does not match! Please try again.");
                response.sendRedirect("SignUp.jsp");
            }
        } else {
            session.setAttribute("warningSignUp", "Captcha guess incorrect! Please try again.");
            System.out.println("warning: Captcha guess incorrect! " + captchaGuess + " " + captchaAnswer);
            response.sendRedirect("SignUp.jsp");
        }

    }
}
