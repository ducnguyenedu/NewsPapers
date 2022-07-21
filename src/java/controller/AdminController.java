/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.NewsDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.News;

/**
 *
 * @author duchi
 */
@MultipartConfig
public class AdminController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin/add.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
      
        News news = new News();
        String title = request.getParameter("title");
        System.out.println(title);
        news.setTitle(title);
        Collection<Part> filesPart = request.getParts();
        String content = request.getParameter("content");
        news.setContent(content);
        String writer = request.getParameter("writer");
        news.setWriter(writer);
        Date date = Date.valueOf(request.getParameter("date"));
        news.setDate(date);
        for (Part part : filesPart) {
            System.out.println(part.getName());
            if (part.getName().equals("image")) {
                
                InputStream fios = part.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                
                int nRead;
                byte[] data = new byte[1024];
                
                while ((nRead = fios.read(data, 0, data.length)) != -1) {
                    baos.write(data, 0, nRead);
                }
               
                news.setImage(baos.toByteArray());
            }
            
        }
        NewsDAO newsDAO = new  NewsDAO();
        newsDAO.insertNews(news);
        ArrayList<News> newses = newsDAO.getNewsPapers();
        request.setAttribute("newses", newses);
        
        request.getRequestDispatcher("admin/display.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
