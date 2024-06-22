

<%@page import="model.Combinaison"%>
<%@page import="medicament.DetailMedicament"%>
<%@page import="java.util.List"%>
<%@page import="medicament.ListMedicament"%>
<%@page import="medicament.Medicament"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
  // ArrayList<Medicament> m = (ArrayList<Medicament>) request.getAttribute("medicaments");
   ListMedicament  list_med = (ListMedicament) request.getAttribute("list_med");
    Vector<Combinaison> maladie = (Vector<Combinaison>)request.getAttribute("maladie");
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Dashboard - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
<!--  <link href="https://fonts.gstatic.com" rel="preconnect">-->

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: May 30 2023 with Bootstrap v5.3.0
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
      color: #333; /* Text color */
    }

    .main {
      padding: 20px;
    }

    .card {
      margin-bottom: 20px;
      border: 1px solid #ddd; /* Border color */
      border-radius: 8px; /* Border radius */
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Box shadow */
      background-color: #fff; /* Card background color */
    }

    .card-title {
      color: #007bff; /* Card title color */
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 15px;
    }

    th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd; /* Table cell border color */
    }

    th {
      background-color: #007bff; /* Table header background color */
      color: #fff; /* Table header text color */
    }

    h4 {
      color: #28a745; /* Success color for price total */
    }

    /* Add more custom styles as needed */
</style>

</head>

<body>

  <!-- ======= Header ======= -->

  <!-- ======= Sidebar ======= -->


  <main id="main" class="main">


    <!--//// Form_annonce-->
<div class="container">
        <div class="card p-5 col-10">
             <div class="card p-5 col-10">
            <div class="card-body">
                 <h5 class="card-title">MALADIES  POSSIBLES</h5>
                  
                    <%  for(Combinaison comb : maladie){ %>
                  <h6>Maladie </h6>
                 <table class="table table-hover">
                     <thead>
                         <tr>
                             <th scope="col">Nom</th>
                             <th scope="col">Parametres</th>
                         </tr>
                     </thead>
                     <tbody>
                        <% for(int i = 0;i<comb.getMaladie().size();i++) { %>
                         <tr>
                             <td><%=comb.getMaladie().get(i).getNom() %></td>
                             
<!--                             <% for(int j =0;j<comb.getMaladie().get(i).getParametre().size();j++) { %>
                             <td>
                                 <%= comb.getMaladie().get(i).getParametre().get(j).getDesignation() %>
                             </td>
                             <% } %>        -->
                         </tr>
                         <% } %>
                     </tbody>
                  
                 </table>
                         <% } %>
                     

            </div>
        </div>
    </div>
  </main><!-- End #main -->
  <main>
  <div class="container">
        <div class="card p-5 col-10">
            <div class="card-body">
                 <h5 class="card-title">LISTE DES MEDICAMENT</h5>
                 
                 <table class="table table-hover">
                     <thead>
                         <tr>
                             <th scope="col">Nom</th>
                             <th scope="col">Quantite</th>
                             <th scope="col">Prix Unitaire</th>
                             <th scope="col">Prix total</th>
                         </tr>
                     </thead>
                     <tbody>
                         <% for(DetailMedicament m : list_med.getD_medicaments()){ %>
                         <tr>
                             <td><%=m.getNom() %></td>
                             <td><%=m.getQuantite() %></td>
                             <td><%=m.getPrixunitaire() %></td>
                             <td><%=m.getPrix_total() %></td>
                         </tr>
                         <% } %>
                     </tbody>
                      
                 </table>
                       <h4>Prix total : <%=list_med.getPrix_total() %></h4> 
                       
            </div>
        </div>
    </div>
  </main><!-- End #main -->

  
  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</body>

</html>
