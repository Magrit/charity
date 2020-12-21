<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="../../../resources/sb-admin-2/vendor/fontawesome-free/css/all.min.css" />"
          rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="../../../resources/sb-admin-2/css/sb-admin-2.min.css" />" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <%@include file="header.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Zmień dane swojego konta</h6>
                    </div>
                    <div class="card-body">
                        <form method="post" action="/admin/profile/edit" modelAttribute="user">
                            <div class="form-group">
                                <input type="text" name="firstName" value="${user.firstName}" placeholder="Imię"/>
                            </div>
                            <div class="form-group">
                                <input type="text" name="lastName" value="${user.lastName}" placeholder="Nazwisko"/>
                            </div>
                            <div class="form-group">
                                <input type="text" name="email" value="${user.email}" placeholder="Email"/>
                            </div>
                            <div class="form-group">
                                <input type="text" name="phoneNumber" value="${user.phoneNumber}" placeholder="Numer telefonu"/>
                            </div>
                            <div class="form-group">
                                <label>
                                    <c:forEach items="${errors}" var="error">
                                        <div class="alert-danger">${error.message}</div>
                                    </c:forEach>
                                </label>
                            </div>
                            <div class="form-group">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" class="btn btn-success">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->

    <%@include file="footer.jsp" %>

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="../../../resources/sb-admin-2/vendor/jquery/jquery.min.js"></script>
<script src="../../../resources/sb-admin-2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../../../resources/sb-admin-2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../../../resources/sb-admin-2/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="../../../resources/sb-admin-2/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="../../../resources/sb-admin-2/js/demo/chart-area-demo.js"></script>
<script src="../../../resources/sb-admin-2/js/demo/chart-pie-demo.js"></script>

</body>

</html>
