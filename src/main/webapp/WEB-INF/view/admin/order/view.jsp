<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                <h1 class="mt-4">Manage Order</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin"> Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/order">Order</a></li>
                    <li class="breadcrumb-item active">View</li>
                </ol>

            </div>

            <h3 class="fw-bold mb-4">
                Order detail with id = ${id}
            </h3>

            <table class="table align-middle">
                <thead class="table-light">
                <tr class="text-center">
                    <th>Sản phẩm</th>
                    <th>Tên</th>
                    <th>Giá cả</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                </tr>
                </thead>

                <tbody>


                <c:forEach var="orderDetail" items="${orderDetails}">
                    <tr class="text-center">
                        <td>
                            <img src="/images/product/${orderDetail.products.image}" class="rounded-circle" width="70"
                                 height="70">
                        </td>
                        <td class="text-start">
                            <a href="/product/${orderDetail.products.id}"
                               class="text-decoration-none">${orderDetail.products.name}</a>
                        </td>
                        <td>
                            <fmt:formatNumber value="${orderDetail.price}" type="number" pattern="#,###"/> đ
                        </td>
                        <td>${orderDetail.quantity}</td>
                        <td>
                            <fmt:formatNumber value="${orderDetail.price * orderDetail.quantity}" type="number"
                                              pattern="#,###"/> đ
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <a href="/admin/order" class="btn btn-success px-4">Back</a>
        </main>
        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>

</body>
</html>
