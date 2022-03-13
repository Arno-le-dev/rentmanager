<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">Ajouter</a>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                           <form class="form-horizontal" method="post" action="/rentmanager/users">
                            <table class="table table-striped">
                            
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                    <c:forEach items="${listUsers}" var="user">
                                    <tr>
                                    	<td>${user.idClient}</td>
                                    	<td>${user.nom}</td>
                                    	<td>${user.prenom}</td>
                                    	<td>${user.email}</td>
                                    	<td>${user.naissance}</td>  
                                    	
                                    	<td>
                                        <a class="btn btn-primary" href="#">
                                            <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success" href="${pageContext.request.contextPath}/users/edit?id=${user.idClient}">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <button type="submit" class="btn btn-danger" id="idClient" name="idClient" value="${user.idClient}">
                                            <i class="fa fa-trash"></i>
                                        </button>
                                   
                                    </td>                                    	                           
                                </tr>
                             		</c:forEach>
                            </table>
                             </form>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
