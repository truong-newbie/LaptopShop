<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cảm ơn bạn đã mua hàng</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .thank-container {
            margin-top: 80px;
        }
    </style>
</head>

<body>

<div class="container thank-container text-center">
    <div class="card shadow p-5">
        <h1 class="text-success mb-3">🎉 Cảm ơn bạn đã mua hàng!</h1>

        <p class="lead mb-4">
            Đơn hàng của bạn đã được tiếp nhận.<br>
            Chúng tôi sẽ xử lý và liên hệ với bạn trong thời gian sớm nhất.
        </p>

        <!-- Nếu bạn muốn truyền mã đơn hàng từ backend -->
        <c:if test="${not empty orderCode}">
            <p class="fw-bold fs-5">Mã đơn hàng: <span class="text-primary">${orderCode}</span></p>
        </c:if>

        <div class="mt-4">
            <a href="/" class="btn btn-primary px-4 me-2">Quay về trang chủ</a>
            <a href="/orders" class="btn btn-outline-secondary px-4">Xem đơn hàng</a>
        </div>
    </div>
</div>

</body>
</html>
