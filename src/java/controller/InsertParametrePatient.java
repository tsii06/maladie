/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import medicament.ListMedicament;
import medicament.Medicament;
import model.Combinaison;
import model.CombinaisonMedicament;
import model.Maladie;
import model.MaladieP;
import model.MedicamentParametre;
import model.Parametre;
import model.ParametrePatient;
import model.Patient;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class InsertParametrePatient extends HttpServlet {

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
            out.println("<title>Servlet InsertParametrePatient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertParametrePatient at " + request.getContextPath() + "</h1>");
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
      //  processRequest(request, response);
      String patient_id = request.getParameter("patient_id");
      String [] parametres = request.getParameterValues("parametre[]");
      String [] valeur =  request.getParameterValues("valeur[]");
      Connection c;
          PrintWriter out = response.getWriter();
        try {
            c = connexion.Connexion.connect();
      Patient p  = new Patient();
      p = p.getPatientById(c, patient_id);
      Vector<Parametre> params = new Vector<Parametre>();
      
      for(int i=0;i<parametres.length;i++){
          Parametre param = new Parametre();
          param = param.getParametreById(c,parametres[i]);
         
          param.setCoefficient(Integer.parseInt(valeur[i]));
          params.add(param);
      }
            p.setParametres(params);
            out.println(p.getParametres().size());
            p.insertParametrePatient(c);
        Map<MaladieP,Vector<Parametre>> res = p.GetMaladiePossibleV2();
        Vector<MaladieP> mald = p.changeMapToVector(res);
        Vector<Combinaison> combP = p.getCombinaison(mald);
        Vector<Combinaison> comb = p.getMaladiePatient(combP);
         c.close();
           // int idPatient = Integer.parseInt(request.getParameter("id"));
           int idPatient = 3; 
           Medicament m = new Medicament();
       
           ArrayList<Medicament>medicament =  m.getMedicament(1);
            ListMedicament list_med  = new ListMedicament();
            list_med.getMedicaments(medicament);
            
           request.setAttribute("medicaments", medicament);
           request.setAttribute("list_med", list_med);
            request.setAttribute("maladie",comb);
            RequestDispatcher dis = request.getRequestDispatcher("patient.jsp");
            dis.forward(request, response);
        } catch (Exception ex) {
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
