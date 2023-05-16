/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.itextpdf.text.BaseColor;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isha Quingquing
 */
public class PdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        Document doc = new Document();
        PdfWriter writer = null;
        HttpSession session = request.getSession();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

        try {
            writer = PdfWriter.getInstance(doc, response.getOutputStream());
        } catch (DocumentException ex) {
            Logger.getLogger(PdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            doc.open();
            Paragraph paragraph = null;
            InputStream file = getServletContext().getResourceAsStream("/WEB-INF/SongLyrics/" + session.getAttribute("songNo") + ".txt");
            Scanner reader = new Scanner(file);

            paragraph = new Paragraph((String) session.getAttribute("title"));
            doc.add(paragraph);
            paragraph = new Paragraph((String) session.getAttribute("artist"));
            doc.add(paragraph);

            while (reader.hasNextLine()) {
                paragraph = new Paragraph(reader.nextLine());
                doc.add(paragraph);
            }

            paragraph.setAlignment(Element.ALIGN_CENTER);
            
            doc.close();
        } catch (DocumentException e) {
        }

    }
}
