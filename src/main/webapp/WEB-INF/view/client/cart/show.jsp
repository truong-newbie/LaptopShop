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
        <form id="cartForm" action="/confirm-checkout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Tên</th>
                        <th>Giá cả</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                        <th>Xử lí</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartDetails}" var="cartDetail" varStatus="status">
                        <tr>
                            <td>
                                <img src="images/product/${cartDetail.products.image}" class="img-fluid rounded-circle"
                                     style="width:80px;height:80px;">
                            </td>
                            <td>${cartDetail.products.name}</td>
                            <td>
                                <fmt:formatNumber type="number" value="${cartDetail.price}"/> đ
                            </td>
                            <td>
                                <div class="input-group quantity" style="width:100px;">
                                    <div class="input-group-btn">
                                        <button type="button"
                                                class="btn btn-sm btn-minus rounded-circle bg-light border">
                                            <i class="fa fa-minus"></i>
                                        </button>
                                    </div>

                                    <!-- Input số lượng bind vào List<CartDetail> -->
                                    <input type="text" class="form-control form-control-sm text-center border-0"
                                           name="cartDetails[${status.index}].quantity"
                                           value="${cartDetail.quantity}"
                                           data-cart-detail-id="${cartDetail.id}"
                                           data-cart-detail-price="${cartDetail.price}"/>

                                    <!-- Hidden để bind id -->
                                    <input type="hidden" name="cartDetails[${status.index}].id"
                                           value="${cartDetail.id}"/>

                                    <div class="input-group-btn">
                                        <button type="button"
                                                class="btn btn-sm btn-plus rounded-circle bg-light border">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <p data-cart-detail-id="${cartDetail.id}">
                                    <fmt:formatNumber type="number" value="${cartDetail.price * cartDetail.quantity}"/>
                                    đ
                                </p>
                            </td>
                            <td>
                                <form method="post" action="/delete-cart-product/${cartDetail.id}">
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
        </form>
        <div class="mt-5 row g-4 justify-content-start">
            <div class="col-12 col-md-8"></div>
            <div class="bg-light rounded">
                <div class="p-4">
                    <h1 class="display-6 mb-4">Thông Tin <span class="fw-normal">Đơn Hàng</span></h1>
                    <div class="d-flex justify-content-between mb-4">
                        <h5 class="mb-0 me-4">Tạm tính</h5>
                        <p class="m-0">
                        <p class="mb-0" data-cart-total-price="${totalPrice}">
                            <fmt:formatNumber type="number" value="${totalPrice}"/> đ
                        </p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h5 class="mb-0 me-4">Phí vận chuyển</h5>
                        <div class="">
                            <p class="mb-0">0 đ</p>
                        </div>
                    </div>

                </div>
                <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                    <h5 class="mb-0 ps-4 me-4">Tổng số tiền</h5>
                    <p class="mb-0" data-cart-total-price="${totalPrice}">
                        <fmt:formatNumber type="number" value="${totalPrice}"/> đ
                    </p>
                </div>
                <form:form action="/confirm-chechout" method="post" modelAttribute="cart">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div style="display:none;">
                        <c:forEach var="cartDetail" items="${cart.cartDetails}"
                                   varStatus="status">
                            <div class="mb-3">
                                <div class="form-group">
                                    <label>Id:</label>
                                    <form:input class="form-control"
                                                value="${cartDetails.id}"
                                                path="cartDetails[${status.index}].id"/>
                                </div>
                                <div class="form-group">
                                    <label>Quantity:</label>
                                    <form:input class="form-control"
                                                value="${cartDetails.quantity}"
                                                path="cartDetails[${status.index}].quantity"/>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </form:form>
                <a href="${pageContext.request.contextPath}/checkout"
                   class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4">
                    Xác nhận đơn hàng
                </a>

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
<%--js tang giam so luong--%>
<script>
    (function ($) {
        "use strict";

        // --- helper: format tiền theo vi-VN (không hiển thị thập phân) ---
        function formatCurrency(value) {
            const formatter = new Intl.NumberFormat('vi-VN', {
                style: 'decimal',
                minimumFractionDigits: 0,
            });
            return formatter.format(Math.round(value)); // integer
        }

        // Lấy CSRF token (nếu Spring Security bật CSRF)
        function getCsrf() {
            const param = $('meta[name="_csrf_parameter"]').attr('content') || $("input[name='_csrf']").attr('name');
            const token = $('meta[name="_csrf"]').attr('content') || $("input[name='_csrf']").val();
            return {param, token};
        }

        // Remove any previous bindings on .quantity button để tránh double-binding
        $(document).off('click', '.quantity button');

        // Gắn 1 lần duy nhất
        $(document).on('click', '.quantity button', function (e) {
            e.preventDefault();

            const button = $(this);
            // tìm input số lượng nằm trong cùng .quantity (an toàn hơn dùng closest)
            const parent = button.closest('.quantity');
            const input = parent.find("input[name*='quantity']").first(); // chắc chắn chọn đúng input
            if (!input || input.length === 0) return;

            // Parse cẩn thận — bỏ dấu không số nếu có
            let oldVal = parseInt((input.val() + '').replace(/[^\d-]/g, ''), 10);
            if (isNaN(oldVal) || oldVal < 1) oldVal = 1;

            const isPlus = button.hasClass('btn-plus');
            const newVal = isPlus ? oldVal + 1 : Math.max(1, oldVal - 1);

            // Cập nhật input (giá trị sẽ được submit hoặc dùng cho AJAX)
            input.val(newVal);

            // Lấy data từ input
            const price = parseFloat(input.attr("data-cart-detail-price")) || 0;
            const id = input.attr("data-cart-detail-id");

            // Cập nhật thành tiền của từng dòng
            const priceElement = $(`p[data-cart-detail-id='${id}']`);
            if (priceElement.length) {
                const newPrice = price * newVal;
                priceElement.text(formatCurrency(newPrice) + " đ");
            }

            // Tính tổng từ tất cả input còn lại trên client
            let total = 0;
            $("input[data-cart-detail-price]").each(function () {
                const p = parseFloat($(this).attr("data-cart-detail-price")) || 0;
                let q = parseInt(($(this).val() + '').replace(/[^\d-]/g, ''), 10);
                if (isNaN(q) || q < 1) q = 1;
                total += p * q;
            });

            // Cập nhật các chỗ hiển thị tổng
            $("p[data-cart-total-price]").each(function () {
                $(this).text(formatCurrency(total) + " đ");
                $(this).attr("data-cart-total-price", total);
            });

            // Gọi AJAX để cập nhật DB ngay (tùy bạn có muốn update on change hay chờ submit)
            // Thực hiện nhẹ: gửi id & newVal tới /cart/update
            const csrf = getCsrf();
            const data = {};
            data['id'] = id;
            data['quantity'] = newVal;

            // If CSRF param name present, thêm param
            if (csrf.param && csrf.token) {
                data[csrf.param] = csrf.token;
            }

            $.ajax({
                url: "/cart/update",
                method: "POST",
                data: data,
                success: function (res) {
                    // res có thể trả về tổng tiền (server hiện trả về total)
                    if (!isNaN(res)) {
                        const totalFromServer = parseFloat(res);
                        $("p[data-cart-total-price]").text(formatCurrency(totalFromServer) + " đ");
                        $("p[data-cart-total-price]").attr("data-cart-total-price", totalFromServer);
                    }
                },
                error: function (err) {
                    console.error("Lỗi khi cập nhật số lượng lên server:", err);
                    // Optionally: rollback giá trị UI nếu server lỗi
                    // input.val(oldVal);
                    // Cập nhật lại thành tiền và tổng theo oldVal nếu rollback cần thiết
                }
            });

        });

    })(jQuery);

</script>
</body>
</html>