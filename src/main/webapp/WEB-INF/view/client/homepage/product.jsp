<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Trang chủ - Laptopshop</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
          rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

    <link href="/client/css/style.css" rel="stylesheet">
</head>
<body>

<div id="spinner"
     class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<jsp:include page="../layout/header.jsp"/>

<div class="container-fluid fruite py-5">
    <div class="container py-5">

        <div class="row g-4">

            <div class="col-lg-3">
                <div class="row g-4">


                    <div class="col-12" id="factoryFilter">
                        <div class="mb-2"><b>Hãng sản xuất</b></div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="factory-1" value="APPLE">
                            <label class="form-check-label" for="factory-1">Apple</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="factory-2" value="ASUS">
                            <label class="form-check-label" for="factory-2">Asus</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="factory-3" value="LENOVO">
                            <label class="form-check-label" for="factory-3">Lenovo</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="factory-4" value="DELL">
                            <label class="form-check-label" for="factory-4">Dell</label>
                        </div>
                    </div>

                    <hr class="my-4">

                    <div class="col-12" id="targetFilter">
                        <div class="mb-2"><b>Mục đích sử dụng</b></div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="target-1" value="GAMING">
                            <label class="form-check-label" for="target-1">Gaming</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="target-2" value="SINHVIEN-VANPHONG">
                            <label class="form-check-label" for="target-2">Sinh viên - Văn phòng</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="target-3" value="THIET-KE-DO-HOA">
                            <label class="form-check-label" for="target-3">Thiết kế đồ họa</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="target-4" value="MONG-NHE">
                            <label class="form-check-label" for="target-4">Mỏng nhẹ</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="target-5" value="DOANH-NHAN">
                            <label class="form-check-label" for="target-5">Doanh nhân</label>
                        </div>
                    </div>

                    <hr class="my-4">
                    <div class="col-12" id="priceFilter">
                        <div class="mb-2"><b>Mức giá</b></div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="price-1" value="duoi-10-trieu">
                            <label class="form-check-label" for="price-1">Dưới 10 triệu</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="price-2" value="10-15-trieu">
                            <label class="form-check-label" for="price-2">Từ 10 - 15 triệu</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="price-3" value="15-20-trieu">
                            <label class="form-check-label" for="price-3"> Từ 15 - 20 triệu</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="price-4" value="tren-20-trieu">
                            <label class="form-check-label" for="price-4">Trên 20 triệu</label>
                        </div>

                    </div>

                    <hr class="my-4">

                    <div class="col-12">
                        <div class="mb-2"><b>Sắp xếp</b></div>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="radio-sort" id="sort-1"
                                   value="gia-tang-dan">
                            <label class="form-check-label" for="sort-1">
                                Giá tăng dần
                            </label>
                        </div>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="radio-sort" id="sort-2"
                                   value="gia-giam-dan">
                            <label class="form-check-label" for="sort-2">
                                Giá giảm dần
                            </label>
                        </div>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="radio-sort" id="sort-3" checked
                                   value="gia-nothing">
                            <label class="form-check-label" for="sort-3">
                                Không sắp xếp
                            </label>
                        </div>
                        <div class="col-12">
                            <button class="btn btn-warning mt-3" id="btnFilter">
                                LỌC SẢN PHẨM
                            </button>
                        </div>

                    </div>

                </div>
            </div>
            <div class="col-lg-9">
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane fade show p-0 active">
                        <div class="row g-4">
                            <div class="col-lg-12">
                                <div class="row g-4">
                                    <c:forEach var="product" items="${products}">
                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src="/images/product/${product.image}"
                                                         class="img-fluid w-100 rounded-top"
                                                         style="height: 200px; object-fit: cover;" alt="">
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                     style="top: 10px; left: 10px;">Laptop
                                                </div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4 style="font-size:15px;">
                                                        <a href="/product/${product.id}">
                                                                ${product.name}
                                                        </a>
                                                    </h4>
                                                    <p style="font-size : 13px;">${product.shortDesc}</p>
                                                    <div class="d-flex justify-content-center flex-column">
                                                        <p style="font-size: 15px; text-align: center; width: 100%;"
                                                           class="text-dark fw-bold mb-3">
                                                            <fmt:formatNumber type="number" value="${product.price}"/> đ
                                                        </p>
                                                        <form action="/add-product-to-cart/${product.id} "
                                                              method="post">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                   value="${_csrf.token}"/>
                                                            <button
                                                                    class="mx-auto btn border border-secondary rounded-pill px-3
                                                                text-primary">
                                                                <i class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                Add to cart
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%-- Pagination Start --%>
                <div class="pagination d-flex justify-content-center mt-5">

                    <li class="page-item ${1 eq currentPage ? 'disabled' : ''}">
                        <a class="${1 eq currentPage ? 'rounded disabled' : 'rounded'}"
                           href="${1 eq currentPage ? 'javascript:void(0)' : '/products?page='.concat(currentPage - 1)}"
                           aria-label="Previous">
                            «
                        </a>
                    </li>

                    <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                        <li class="page-item">
                            <a class="${(loop.index + 1) eq currentPage ? 'active rounded' : 'rounded'}"
                               href="/products?page=${loop.index + 1}">
                                    ${loop.index + 1}
                            </a>
                        </li>
                    </c:forEach>

                    <li class="page-item ${totalPages eq currentPage ? 'disabled' : ''}">
                        <a class="${totalPages eq currentPage ? 'rounded disabled' : 'rounded'}"
                           href="${totalPages eq currentPage ? 'javascript:void(0)' : '/products?page='.concat(currentPage + 1)}"
                           aria-label="Next">
                            »
                        </a>
                    </li>

                </div>
                <%-- Pagination End --%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/feature.jsp"/>
<jsp:include page="../layout/footer.jsp"/>

<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
        class="fa fa-arrow-up"></i></a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/client/lib/easing/easing.min.js"></script>
<script src="/client/lib/waypoints/waypoints.min.js"></script>
<script src="/client/lib/lightbox/js/lightbox.min.js"></script>
<script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

<script src="/client/js/main.js"></script>
<script>
    $('#btnFilter').click(function (event) {
        event.preventDefault();
        let factoryArr = [];
        let targetArr = [];
        let priceArr = [];

        //factory filter
        $('#factoryFilter .form-check-input:checked').each(function () {
            factoryArr.push($(this).val());
        });

        //target filter
        $('#targetFilter .form-check-input:checked').each(function () {
            targetArr.push($(this).val());
        });

        //price filter
        $('#priceFilter .form-check-input:checked').each(function () {
            priceArr.push($(this).val());
        });

        //sort order
        let sortValue = $('input[name="radio-sort"]:checked').val();

        const currentUrl = new URL(window.location.href);
        const searchParams = currentUrl.searchParams;

        //add or update query parameters
        searchParams.set('page', '1');
        searchParams.set('sort', sortValue);

        if (factoryArr.length > 0) {
            searchParams.set('factory', factoryArr.join(','));
        }
        if (targetArr.length > 0) {
            searchParams.set('target', targetArr.join(','));
        }
        if (priceArr.length > 0) {
            searchParams.set('price', priceArr.join(','));
        }

        //update the url and reload the page
        window.location.href = currentUrl.toString();


        // Khởi tạo trạng thái checkbox dựa trên URL
        function initializeFilters() {
            const currentUrl = new URL(window.location.href);
            const searchParams = currentUrl.searchParams;

            // 1. Xử lý Hãng sản xuất (factory)
            const factoryParam = searchParams.get('factory');
            if (factoryParam) {
                const factoryValues = factoryParam.split(',');
                factoryValues.forEach(function (val) {
                    // Tìm checkbox có value khớp trong div factoryFilter và đặt là checked
                    $(`#factoryFilter .form-check-input[value="${val}"]`).prop('checked', true);
                });
            }

            // 2. Xử lý Mục đích sử dụng (target)
            const targetParam = searchParams.get('target');
            if (targetParam) {
                const targetValues = targetParam.split(',');
                targetValues.forEach(function (val) {
                    $(`#targetFilter .form-check-input[value="${val}"]`).prop('checked', true);
                });
            }

            // 3. Xử lý Mức giá (price)
            const priceParam = searchParams.get('price');
            if (priceParam) {
                const priceValues = priceParam.split(',');
                priceValues.forEach(function (val) {
                    $(`#priceFilter .form-check-input[value="${val}"]`).prop('checked', true);
                });
            }

            // 4. Xử lý Sắp xếp (sort)
            const sortParam = searchParams.get('sort');
            if (sortParam) {
                // Đặt thuộc tính checked cho radio button có value khớp
                $(`input[name="radio-sort"][value="${sortParam}"]`).prop('checked', true);
            }

        }

        // Gọi hàm này khi DOM đã tải xong
        initializeFilters();
    });
</script>
</body>
</html>