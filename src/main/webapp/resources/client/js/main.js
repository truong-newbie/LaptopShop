(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', 0);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            },
            1200: {
                items: 4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });

    //add active class to header
    const navElement = $("navbarCollapse")
    const currentUrl = window.location.pathname;
    navElement.find('a.nav-link').each(function () {
        const link = $(this); // get the current link in the loop
        const href = link.attr('href');

        if (href === currentUrl) {
            link.addClass('active');
        } else {
            link.removeClass('active');
        }
    });

    $('.quantity button').on('click', function () {
        var button = $(this);
        var input = button.parent().parent().find('input');
        var oldValue = parseFloat(input.val());
        var newVal = button.hasClass('btn-plus') ? oldValue + 1 : Math.max(1, oldValue - 1);
        input.val(newVal);

        const price = +input.attr("data-cart-detail-price");
        const id = input.attr("data-cart-detail-id");

        // Cập nhật thành tiền cho sản phẩm
        const priceElement = $(`p[data-cart-detail-id='${id}']`);
        if (priceElement.length) {
            const newPrice = price * newVal;
            priceElement.text(formatCurrency(newPrice.toFixed(2)) + "đ");
        }

        // Cập nhật tổng giỏ hàng
        let total = 0;
        $("input[data-cart-detail-price]").each(function () {
            const p = +$(this).attr("data-cart-detail-price");
            const q = +$(this).val();
            total += p * q;
        });

        $("p[data-cart-total-price]").each(function () {
            $(this).text(formatCurrency(total.toFixed(2)) + "đ");
            $(this).attr("data-cart-total-price", total);
        });
    });

    //set form index
    const index = input.attr("data-cart-detail-index")
    const el = document.getElementById(`cartDetails${index}.quantity`);
    $(el).val(newVal);

    function formatCurrency(value) {
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'decimal',
            minimumFractionDigits: 0,
        });
        let formatted = formatter.format(value);
        formatted = formatted.replace(/\./g, ',');
        return formatted;
    }


    //handle filter products
    // $('#btnFilter').click(function (event) {
    //     event.preventDefault();
    //     let factoryArr = [];
    //     let targetArr = [];
    //     let priceArr = [];
    //
    //     //factory filter
    //     $('#factoryFilter .form-check-input:checked').each(function () {
    //         factoryArr.push($(this).val());
    //     });
    //
    //     //target filter
    //     $('#targetFilter .form-check-input:checked').each(function () {
    //         targetArr.push($(this).val());
    //     });
    //
    //     //price filter
    //     $('#priceFilter .form-check-input:checked').each(function () {
    //         priceArr.push($(this).val());
    //     });
    //
    //     //sort order
    //     let sortValue = $('input[name="radio-sort"]:checked').val();
    //
    //     const currentUrl = new URL(window.location.href);
    //     const searchParams = currentUrl.searchParams;
    //
    //     //add or update query parameters
    //     searchParams.set('page', '1');
    //     searchParams.set('sort', sortValue);
    //
    //     if (factoryArr.length > 0) {
    //         searchParams.set('factory', factoryArr.join(','));
    //     }
    //     if (targetArr.length > 0) {
    //         searchParams.set('target', targetArr.join(','));
    //     }
    //     if (priceArr.length > 0) {
    //         searchParams.set('price', priceArr.join(','));
    //     }
    //
    //     //update the url and reload the page
    //     window.location.href = currentUrl.toString();
    // });

    
})(jQuery);

