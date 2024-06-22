/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Parametre;
import model.Patient;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class InsertPatient extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertPatient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertPatient at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
      
        String nom = request.getParameter("nom");
        int age = Integer.parseInt(request.getParameter("age"));
        PrintWriter out = response.getWriter();
        try {
            Connection c = connexion.Connexion.connect();
             Patient p = new Patient();
             p.setNom(nom);
             p.setAge(age);
             p.setDate_consultation(Date.valueOf(LocalDate.now()));
             p.insert(c);
             
             Parametre param = new Parametre();
             Vector<Parametre> params = param.getAllParametre(c);
             request.setAttribute("parametres",params);
             request.setAttribute("patient_id",p.getLastPatient(c));
              HttpSession session = request.getSession();          
            session.setAttribute("patient_id",p.getLastPatient(c));
            
             c.close();
             RequestDispatcher dis = request.getRequestDispatcher("addParametrePatient.jsp");
             
              dis.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertPatient.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex.getMessage());
        }
       
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
