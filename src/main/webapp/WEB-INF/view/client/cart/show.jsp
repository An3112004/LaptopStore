<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%> <%@taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Cart</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
      rel="stylesheet"
    />

    <!-- Icon Font Stylesheet -->
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
      rel="stylesheet"
    />

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
    <link
      href="/client/lib/owlcarousel/assets/owl.carousel.min.css"
      rel="stylesheet"
    />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet" />
  </head>

  <body>
    <!-- Spinner Start -->
    <div
      id="spinner"
      class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50 d-flex align-items-center justify-content-center"
    >
      <div class="spinner-grow text-primary" role="status"></div>
    </div>
    <!-- Spinner End -->

    <!-- Navbar start -->
    <jsp:include page="../layout/header.jsp" />
    <!-- Navbar End -->

    <!-- Cart Page Start -->
    <div class="container-fluid py-5">
      <div class="container py-5">
        <div class="table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Products</th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Quantity</th>
                <th scope="col">Total</th>
                <th scope="col">Handle</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="cartDetail" items="${cartDetails}">
                <tr>
                  <th scope="row">
                    <div class="d-flex align-items-center">
                      <img
                        src="/images/product/${cartDetail.product.image}"
                        class="img-fluid me-5 rounded-circle"
                        style="width: 80px; height: 80px"
                        alt=""
                      />
                    </div>
                  </th>
                  <td>
                    <p class="mb-0 mt-4">
                      <a
                        href="/client/product/${cartDetail.product.id}"
                        target="_blank"
                        >${cartDetail.product.name}</a
                      >
                    </p>
                  </td>
                  <td>
                    <p class="mb-0 mt-4">
                      <fmt:formatNumber
                        value="${cartDetail.product.price}"
                        type="number"
                      />
                      đ
                    </p>
                  </td>
                  <td>
                    <form method="post" action="/cart/update">
                      <div>
                        <input
                          type="hidden"
                          name="${_csrf.parameterName}"
                          value="${_csrf.token}"
                        />
                      </div>
                      <input
                        type="hidden"
                        name="cartDetailId"
                        value="${cartDetail.id}"
                      />
                      <div
                        class="input-group quantity mt-4"
                        style="width: 100px"
                      >
                        <button
                          type="submit"
                          name="quantity"
                          value="${cartDetail.quantity - 1}"
                          class="btn btn-sm btn-minus rounded-circle bg-light border"
                        >
                          <i class="fa fa-minus"></i>
                        </button>
                        <input
                          type="text"
                          class="form-control form-control-sm text-center border-0"
                          value="${cartDetail.quantity}"
                          readonly
                        />
                        <button
                          type="submit"
                          name="quantity"
                          value="${cartDetail.quantity + 1}"
                          class="btn btn-sm btn-plus rounded-circle bg-light border"
                        >
                          <i class="fa fa-plus"></i>
                        </button>
                      </div>
                    </form>
                  </td>
                  <td>
                    <p class="mb-0 mt-4">
                      <fmt:formatNumber
                        value="${cartDetail.price * cartDetail.quantity}"
                        type="number"
                      />
                      đ
                    </p>
                  </td>
                  <td>
                    <form
                      method="post"
                      action="/cart/delete-cart-product/${cartDetail.id}"
                    >
                      <input
                        type="hidden"
                        name="${_csrf.parameterName}"
                        value="${_csrf.token}"
                      />
                      <button
                        class="btn btn-md rounded-circle bg-light border mt-4"
                      >
                        <i class="fa fa-times text-danger"></i>
                      </button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="row g-4 justify-content-end">
          <div class="col-8"></div>
          <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
            <div class="bg-light rounded">
              <div class="p-4">
                <h1 class="display-6 mb-4">
                  Cart <span class="fw-normal">Total</span>
                </h1>
                <div class="d-flex justify-content-between mb-4">
                  <h5 class="mb-0 me-4">Subtotal:</h5>
                  <p class="mb-0" data-cart-total-price="${totalPrice}">
                    <fmt:formatNumber value="${totalPrice}" type="number" />
                    đ
                  </p>
                </div>
                <div class="d-flex justify-content-between">
                  <h5 class="mb-0 me-4">Shipping</h5>
                  <div class="">
                    <p class="mb-0">$0</p>
                  </div>
                </div>
              </div>
              <div
                class="py-4 mb-4 border-top border-bottom d-flex justify-content-between"
              >
                <h5 class="mb-0 ps-4 me-4">Total</h5>
                <p class="mb-0 pe-4" data-cart-total-price="${totalPrice}">
                  <fmt:formatNumber value="${totalPrice}" type="number" />
                  đ
                </p>
              </div>
              <form:form
                action="/confirm-checkout"
                method="post"
                modelAttribute="cart"
              >
                <input
                  type="hidden"
                  name="${_csrf.parameterName}"
                  value="${_csrf.token}"
                />
                <div>
                  <c:forEach
                    var="cartDetail"
                    items="${cart.cartDetails}"
                    varStatus="status"
                  >
                    <div class="mb-3">
                      <div class="form-group">
                        <label>Id:</label>
                        <form:input
                          class="form-control"
                          type="text"
                          value="${cartDetail.id}"
                          path="cartDetails[${status.index}].id"
                          id="cartDetails[${status.index}].id"
                        />
                      </div>
                      <div class="form-group">
                        <label>Quantity:</label>
                        <form:input
                          class="form-control"
                          type="text"
                          value="${cartDetail.quantity}"
                          path="cartDetails[${status.index}].quantity"
                          id="cartDetails[${status.index}].id"
                        />
                      </div>
                    </div>
                  </c:forEach>
                </div>
                <button
                  class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
                >
                  Xác nhận thanh toán
                </button>
              </form:form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Cart Page End -->

    <!-- Footer Start -->
    <jsp:include page="../layout/footer.jsp" />
    <!-- Footer End -->

    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/client/lib/easing/easing.min.js"></script>
    <script src="/client/lib/waypoints/waypoints.min.js"></script>
    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="/client/js/main.js"></script>
  </body>
</html>
