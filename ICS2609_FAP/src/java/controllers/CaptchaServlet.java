/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.*;
import static java.awt.Color.*;
import javax.servlet.*;
import javax.servlet.http.*;
import nl.captcha.*;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;

/**
 *
 * @author Isha Quingquing
 */
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Captcha captcha = new Captcha.Builder(200,50)
                .addText()
                .addBackground(new GradiatedBackgroundProducer(green, blue))
                .addNoise()
                .addNoise()
                .gimp()
                .gimp()
                .addBorder()
                .build();
        session.setAttribute("captchaAnswer", captcha.getAnswer());
        response.setContentType("image/png");
        CaptchaServletUtil.writeImage(response, captcha.getImage());
    }

}
