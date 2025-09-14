<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>LaptopShop_DoDangTruong</title>
    <link href="/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Manage Users</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin"> Dashboard</a></li>
                    <li class="breadcrumb-item active">Users</li>
                </ol>
                <div class="container mt-5">
                    <div class="d-flex justify-content-start">
                        <h1>Table User</h1>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a href="/admin/user/create" class="btn btn-primary">Create new user</a>
                    </div>
                    <table class="table table-hover">
                        <tr>
                            <td>Id</td>
                            <td>Email</td>
                            <td>Fullname</td>
                            <td>Action</td>
                        </tr>
                        <c:forEach var="user" items="${users}">

                            <tr>
                                <td>${user.id}</td>
                                <td>${user.email}</td>
                                <td>${user.fullName}</td>
                                <td>
                                    <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                                        <%--                    <button type="button" class="btn btn-success" > View</button>--%>
                                    <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
                                        <%--                    <button type="button" class="btn btn-warning">Update </button>--%>
                                        <%--                    <button type="button" class="btn btn-danger">Delete</button>--%>
                                    <a href="/admin/user/delete/${user.id}" class="btn btn-danger">Delete</a>
                                </td>
                            </tr>

                        </c:forEach>

                    </table>
                </div>

            </div>
        </main>
        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>

</body>
</html>
