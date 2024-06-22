
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
  <title>Dashboard - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

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
</head>

<body>

  <!-- ======= Header ======= -->

  <!-- ======= Sidebar ======= -->


  <main id="main" class="main">


    <!--//// Form_annonce-->
<div class="container">
        <div class="card p-5">
            <div class="card-body">
                 <h5 class="card-title">INFORMATION DU PATIENT</h5>
                 <form method="GET" action="InsertPatient">
                    
                    <div class="row ">
                        <div class="input-group mb-3  col-lg-12">
                            <label class="input-group-text" for="nom">Nom</label>
                            <input type="text" name="nom" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required>
                       </div>
                    </div>
                    <div class="row ">
                        <div class="input-group mb-3  col-lg-12">
                            <label class="input-group-text" for="nom">Age</label>
                            <input type="text" name="age" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required>
                       </div>
                    </div>
                      <div class="col-12 ">
                        <button type="submit" class="btn btn-primary col-12 mt-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Suivant</button>
                    </div>
                                 
                </form>
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
