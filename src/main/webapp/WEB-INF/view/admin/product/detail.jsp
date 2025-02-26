<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard-Product-Detail</title>
    <link
      href="${pageContext.request.contextPath}/css/style.css"
      rel="stylesheet"
    />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <!-- header -->
    <jsp:include page="../layout/header.jsp" />
    <!-- end header -->

    <!-- sidebar -->
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Product - Detail</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item active">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">
                <a href="/admin/product">User</a>
              </li>
              <li class="breadcrumb-item active">Detail</li>
            </ol>
            <div class="container mt-8">
              <ul class="list-group mt-4 w-50 mx-auto">
                <li class="list-group-item">Id: ${id}</li>
                <li class="list-group-item">Name: ${product.name}</li>
                <li class="list-group-item">Price: ${product.price}</li>
                <li class="list-group-item">Quantity: ${product.quantity}</li>
                <li class="list-group-item">
                  DetailDesc: ${product.detailDesc}
                </li>
                <li class="list-group-item">ShortDesc: ${product.shortDesc}</li>
                <li class="list-group-item">Sold: ${product.sold}</li>
                <li class="list-group-item">Factory: ${product.factory}</li>
                <li class="list-group-item">Target: ${product.target}</li>
                <li class="list-group-item">
                  <img
                    style="width: 100%; height: auto"
                    src="/images/product/${product.image}"
                    alt="/images/product/${product.image}"
                  />
                </li>
              </ul>
              <a href="/admin/product" class="btn btn-primary">Back</a>
            </div>
          </div>
        </main>
      </div>
    </div>
    <!-- end sidebar -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
  </body>
</html>
