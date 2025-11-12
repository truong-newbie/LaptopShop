<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Laptopshop</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
            rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">
</head>

<body>

<!-- Spinner Start -->
<div id="spinner"
     class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->

<jsp:include page="../layout/header.jsp"/>


<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="mb-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Chi tiết giỏ hàng</li>
                </ol>
            </nav>
        </div>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Giá cả</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Thành tiền</th>
                    <th scope="col">Xử lí</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartDetails}" var="cartDetails">
                    <tr>
                        <th scope="row">
                            <div class="d-flex align-items-center">
                                <img src="images/product/${cartDetails.products.image}"
                                     class="img-fluid me-5 rounded-circle"
                                     style="width: 80px; height: 80px;" alt="">
                            </div>
                        </th>
                        <td>
                            <p class="mb-0 mt-4">
                                <a href="/product/${cartDetails.products.id}" target="_blank"></a>
                                    ${cartDetails.products.name}</p>
                        </td>
                        <td>
                            <p class="mb-0 mt-4">
                                <fmt:formatNumber type="number"
                                                  value="${cartDetails.price}"/> đ
                            </p>
                        </td>
                        <td>
                            <div class="input-group quantity mt-4" style="width: 100px;">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control form-control-sm text-center border-0"
                                       value="${cartDetails.quantity}"
                                       data-cart-detail-id="${cartDetails.id}"
                                       data-cart-detail-price="${cartDetails.price}"
                                       data-cart-detail-index="${status.index}">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td>
                            <p class="mb-0 mt-4" data-cart-detail-id="${cartDetails.id}">
                                <fmt:formatNumber type="number"
                                                  value="${cartDetails.price * cartDetails.quantity}"/> đ
                            </p>
                        </td>
                        <td>
                            <form method="post" action="/delete-cart-product/${cartDetails.id}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn btn-md rounded-circle bg-light border mt-4">
                                    <i class="fa fa-times text-danger"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mt-5 row g-4 justify-content-start">
            <!-- Cột trái: Thông tin người nhận -->
            <div class="col-lg-7">
                <div class="bg-light rounded p-4 h-100">
                    <h1 class="display-6 mb-4">Thông Tin <span class="fw-normal">Người Nhận</span></h1>
                    <form id="checkoutForm" action="/place-order" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="mb-3">
                            <label for="receiverName" class="form-label fw-semibold">Tên người nhận</label>
                            <input type="text" class="form-control" id="receiverName" name="receiverName"
                                   placeholder="Nhập tên người nhận" required>
                        </div>

                        <div class="mb-3">
                            <label for="receiverAddress" class="form-label fw-semibold">Địa chỉ người nhận</label>
                            <input type="text" class="form-control" id="receiverAddress" name="receiverAddress"
                                   placeholder="Nhập địa chỉ giao hàng" required>
                        </div>

                        <div class="mb-3">
                            <label for="receiverPhone" class="form-label fw-semibold">Số điện thoại</label>
                            <input type="text" class="form-control" id="receiverPhone" name="receiverPhone"
                                   placeholder="Nhập số điện thoại" required>
                        </div>

                        <div class="d-flex justify-content-start mt-4">
                            <a href="${pageContext.request.contextPath}/cart"
                               class="text-decoration-none text-secondary">
                                <i class="fa fa-arrow-left me-2"></i> Quay lại giỏ hàng
                            </a>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Cột phải: Thông tin thanh toán -->
            <div class="col-lg-5">
                <div class="bg-light rounded p-4 h-100">
                    <h1 class="display-6 mb-4">Thông Tin <span class="fw-normal">Thanh Toán</span></h1>

                    <div class="d-flex justify-content-between mb-4">
                        <h5 class="mb-0 me-4">Tạm tính</h5>
                        <p class="mb-0"><fmt:formatNumber type="number" value="${totalPrice}"/> đ</p>
                    </div>

                    <div class="d-flex justify-content-between mb-4">
                        <h5 class="mb-0 me-4">Phí vận chuyển</h5>
                        <p class="mb-0">0 đ</p>
                    </div>

                    <div class="d-flex justify-content-between border-top border-bottom py-3 mb-4">
                        <h5 class="mb-0 me-4">Tổng số tiền</h5>
                        <p class="mb-0 fw-bold text-primary">
                            <fmt:formatNumber type="number" value="${totalPrice}"/> đ
                        </p>
                    </div>

                    <div class="text-center">
                        <button type="submit" form="checkoutForm"
                                class="btn btn-primary rounded-pill px-4 py-3 text-uppercase">
                            Xác nhận thanh toán
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Cart Page End -->

<jsp:include page="../layout/footer.jsp"/>

<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
        class="fa fa-arrow-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/client/lib/easing/easing.min.js"></script>
<script src="/client/lib/waypoints/waypoints.min.js"></script>
<script src="/client/lib/lightbox/js/lightbox.min.js"></script>
<script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="/client/js/main.js"></script>

</body>

</html>