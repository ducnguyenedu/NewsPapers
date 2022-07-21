/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JTextArea;
import model.News;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author duchi
 */
public class HomePageController extends HttpServlet {

    public boolean isHave(String Regex_Pattern, String data) {

        String Test_String = data;
        Pattern p = Pattern.compile(Regex_Pattern.toLowerCase());

        Matcher m = p.matcher(Test_String.toLowerCase());
        //System.out.println(m.regionStart()+""+m.regionEnd());
        return m.find();

    }

    private String formatFindString(String find) {
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
        StringBuffer findFormat = new StringBuffer();
        for (int i = 0; i < find.length(); i++) {
            String charac = find.substring(i, i + 1);
            if (regex.matcher(charac).find()) {
                findFormat.append("\\" + charac);
            } else {
                findFormat.append(charac);
            }
        }
        return findFormat.toString();
    }

    public String ReplaceAll(String find, String data) {
        //String replace = "<span style=\"background-color:red;\">" + find + "</span>";
        StringBuffer buffer = new StringBuffer();
        Pattern p = Pattern.compile(find.toLowerCase());

        
        int start = 0;
        int end = find.length();
        Set<Integer> startList = new ArraySet<>();
        Set<Integer> endList = new ArraySet<>();
        while (end < data.length()) {
          Matcher m=p.matcher(data.substring(start, end).toLowerCase());
          if(m.find()){
              startList.add(start);
              endList.add(end);
          }
            start++;
            end++;
        }

        for (int i = 0; i < data.length(); i++) {
            if (startList.contains(i)) {
                buffer.append("<span style=\"background-color:red;\">");
            }
            if (endList.contains(i)) {
                buffer.append("</span>");
            }
            buffer.append(data.charAt(i));
        }
        //data.replaceAll(formatFindString(find), replace);
        // data.replaceAll(formatFindString(find), replace);
        return buffer.toString();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ArrayList<News> newsPapers = (ArrayList<News>) session.getAttribute("newsPapers");
        if (request.getParameter("search") == null) {
            ArrayList<News> searchNewsPapers = new ArrayList<>();
            for (News newsPaper : newsPapers) {

                News newsPaperSer = new News();
                newsPaperSer.setId(newsPaper.getId());
                newsPaperSer.setTitle(newsPaper.getTitle());
                newsPaperSer.setImage(newsPaper.getImage());
                System.out.println(newsPaper.getContent().length());
                newsPaperSer.setContent((newsPaper.getContent().length() > 500 ? (newsPaper.getContent().substring(0, 500)) + "..." : (newsPaper.getContent())));
                newsPaperSer.setDate(newsPaper.getDate());
                newsPaperSer.setWriter(newsPaper.getWriter());
                searchNewsPapers.add(newsPaperSer);

            }
            request.setAttribute("newsPapers", searchNewsPapers);
            request.setAttribute("keyword", request.getParameter("search"));
        } else {
            ArrayList<News> searchNewsPapers = new ArrayList<>();
            for (News newsPaper : newsPapers) {

                if (isHave(request.getParameter("search"), newsPaper.getTitle())) {

                    News newsPaperSer = new News();
                    newsPaperSer.setId(newsPaper.getId());
                    newsPaperSer.setTitle(ReplaceAll(request.getParameter("search"), newsPaper.getTitle()));
                    newsPaperSer.setImage(newsPaper.getImage());
                    System.out.println(newsPaper.getContent().length());
                    newsPaperSer.setContent((newsPaper.getContent().length() > 500 ? (newsPaper.getContent().substring(0, 500)) + "..." : (newsPaper.getContent())));
                    newsPaperSer.setDate(newsPaper.getDate());
                    newsPaperSer.setWriter(newsPaper.getWriter());
                    searchNewsPapers.add(newsPaperSer);
                }
            }
            request.setAttribute("newsPapers", searchNewsPapers);
            request.setAttribute("keyword", request.getParameter("search"));
        }
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
