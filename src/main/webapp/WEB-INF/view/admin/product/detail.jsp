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
    <title>Create User</title>
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
                <h1 class="mt-4">Manage Products</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin"> Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/product"> Product</a></li>
                    <li class="breadcrumb-item active">View</li>
                </ol>
                <div class = "container mt-5" >
                        <h1>Product Detail with ${product.id}</h1>
                        <hr>
                    <img src="<c:url value='/images/product/${product.image}' />"
                         alt="Chưa có ảnh minh họa"
                         style="width:400px; height:300px;" />
                    <h3>Product Information</h3>
                        <ul>
                            <li>ID: ${product.id}</li>
                            <li>Name : ${product.name}</li>
                            <li>Price : ${product.price}</li>
                        </ul>
                        <a class="btn btn-primary" href="/admin/product" role="button">Back</a>
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
