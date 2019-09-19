/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vieth
 */
public class Task1 extends HttpServlet {

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
        
        String parametersName = request.getQueryString();
        PrintWriter oi = response.getWriter();
        String[] parameters = null;
        if(parametersName != null){
            try {
                parameters = URLDecoder.decode(parametersName, "UTF-8").split("&"); //Decode the query string
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        String format = request.getParameter("format");
        if ("".equals(format))
            format = "html";
                    
        String method = request.getMethod();
        
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        if ("html".equals(format)) {
            response.setContentType("text/html;charset=UTF-8");
            String borderStyle = "border=\"1\"";
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Task1</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Task1 at " + request.getContextPath() + "</h1>");
                out.println("<br>");

                out.println("<table " + borderStyle + ">");
                out.println("<tr>");
                out.println("<th>Request Method: </th>");
                out.println("<td>" + format + "</td>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("<br>");
                
                out.println("<table " + borderStyle + ">");
                out.println("<tr>");
                out.println("<th>Request Headers: </th>");
                out.println("</tr>");
                for (String key : map.keySet()) {
                    out.println("<tr>");
                    out.print("<th>");
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + key + "</th>");
                    out.print("<td> " + map.get(key) + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                
                out.println("<br>");
                
                out.println("<table " + borderStyle + ">");
                out.println("<tr>");
                out.println("<th>Query String: </th>");
                out.println("</tr>");
                for (String key : parameters){
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" + key + "</td>");
                    out.print("</tr>");
                }
                out.println("</table>");
                
                out.println("</body>");
                out.println("</html>");
            }
        }
        
        if ("text".equals(format)) {
            response.setContentType("text;charset=UTF-8");
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Task1</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Task1 at " + request.getContextPath() + "</h1>");
                out.println("<br>");

                out.println("<div>Request Method: "+ method + "</div>");

                out.println("<div>Request Headers: </div>");
                for (String key : map.keySet()) {
                    out.print("<p>");
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\"" + key + "\": " + map.get(key));
                    out.print("</p>");
                }
                out.println("<div>Query String: </div>");
                out.println("<ul style=\"list-style-type:none;\">");
                for (String key : parameters){
                    out.print("<li>");
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" + key);
                    out.print("</li>");
                }
                out.println("</ul>");

                out.println("</body>");
                out.println("</html>");
            }
        }
        
        if ("xml".equals(format)) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.append("<response>");
                out.append("<response_method>Request method: ").append(format).append("</response_method>");

                out.append("<response_headers>Request headers: ");
                out.append("</response_headers>");

                out.append("<query_string>");
                out.append("</query_string>");

                out.append("</response>");

            }
        }
        
        //OTHER CASE OF FORMAT --> SERVER ERROR
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
