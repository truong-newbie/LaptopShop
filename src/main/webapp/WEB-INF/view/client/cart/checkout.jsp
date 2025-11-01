<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <title>Thanh toán - Laptopshop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap + Icon -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" rel="stylesheet">

    <!-- CSS riêng -->
    <link href="/client/css/style.css" rel="stylesheet">

    <style>
        body {
            background-color: #fff;
            font-family: 'Open Sans', sans-serif;
        }

        /* --- Tiêu đề phần --- */
        h5.section-title {
            background-color: #5b4ef1;
            color: white;
            display: inline-block;
            padding: 6px 14px;
            border-radius: 6px;
            font-weight: 600;
            font-size: 1.05rem;
        }

        /* --- Bảng sản phẩm --- */
        .table thead {
            background-color: #f8f9fa;
            color: #444;
            text-align: center;
        }

        .table tbody tr td,
        .table tbody tr th {
            vertical-align: middle;
        }

        /* --- Form input --- */
        .form-control {
            border-radius: 50px;
            padding: 10px 15px;
        }

        /* --- Box hiển thị thanh toán --- */
        .checkout-box {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        .checkout-box h5 {
            font-weight: 600;
        }

        /* --- Nút xác nhận --- */
        .btn-confirm {
            border: 2px solid #28a745;
            color: #28a745;
            font-weight: 600;
            border-radius: 50px;
            transition: all 0.3s ease;
        }

        .btn-confirm:hover {
            background-color: #28a745;
            color: #fff;
        }

        /* --- Quay lại giỏ hàng --- */
        .back-cart {
            color: #28a745;
            font-weight: 600;
            text-decoration: none;
        }

        .back-cart:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<!-- Header -->
<jsp:include page="../layout/header.jsp"/>

<!-- Nội dung chính -->
<div class="container my-5">

    <h2 class="text-success fw-bold mb-4">Thông tin thanh toán</h2>

    <!-- Bảng sản phẩm -->
    <div class="table-responsive mb-5">
        <table class="table table-bordered align-middle text-center">
            <thead>
            <tr>
                <th>Sản phẩm</th>
                <th>Tên</th>
                <th>Giá cả</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cartDetails}">
                <tr>
                    <td>
                        <img src="images/product/${item.products.image}" class="rounded"
                             style="width:80px;height:80px;">
                    </td>
                    <td>${item.products.name}</td>
                    <td><fmt:formatNumber value="${item.price}" type="number"/> đ</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.price * item.quantity}" type="number"/> đ</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Thông tin người nhận & thanh toán -->
    <div class="row g-4">
        <!-- Bên trái -->
        <div class="col-md-6">
            <h5 class="section-title">Thông Tin Người Nhận</h5>

            <form:form action="/confirm-checkout" method="post" modelAttribute="checkoutInfo" class="mt-3">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mb-3">
                    <label class="form-label">Tên người nhận</label>
                    <form:input path="receiverName" class="form-control" placeholder="Nhập tên người nhận"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Địa chỉ người nhận</label>
                    <form:input path="receiverAddress" class="form-control" placeholder="Nhập địa chỉ nhận hàng"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Số điện thoại</label>
                    <form:input path="receiverPhone" class="form-control" placeholder="Nhập số điện thoại"/>
                </div>

                <div class="mt-4">
                    <a href="/cart" class="back-cart"><i class="fa fa-arrow-left me-2"></i>Quay lại giỏ hàng</a>
                </div>
            </form:form>
        </div>

        <!-- Bên phải -->
        <div class="col-md-6">
            <div class="checkout-box">
                <h5 class="mb-4">Thông Tin Thanh Toán</h5>

                <div class="d-flex justify-content-between mb-3">
                    <span>Phí vận chuyển:</span>
                    <strong>0 đ</strong>
                </div>

                <div class="d-flex justify-content-between mb-3">
                    <span>Hình thức:</span>
                    <strong>Thanh toán khi nhận hàng (COD)</strong>
                </div>

                <hr>

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h6 class="fw-bold mb-0">Tổng số tiền</h6>
                    <h5 class="text-success fw-bold mb-0">
                        <fmt:formatNumber value="${totalPrice}" type="number"/> đ
                    </h5>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-confirm px-5 py-2">
                        XÁC NHẬN THANH TOÁN
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
