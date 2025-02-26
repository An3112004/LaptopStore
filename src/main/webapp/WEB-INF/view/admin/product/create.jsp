<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib
prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title>Dashboard-Product-Create</title>
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
            <h1 class="mt-4">Product</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item active">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">
                <a href="/admin/product">Product</a>
              </li>
              <li class="breadcrumb-item active">Create</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                  <h3>Create Product</h3>
                  <hr/>
                  <form:form
                action="/admin/product/add"
                method="post"
                modelAttribute="newProduct"
                enctype="multipart/form-data"
                class="row"
              >
                <div class="mb-3">
                  <c:set var="errorName">
                        <form:errors path="name" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="name" class="form-label">Name</label>
                  <form:input type="text" class="form-control ${not empty errorName ? 'is-invalid' : ''}" path="name" />
                  ${errorName}
                </div>
                <div class="mb-3 col-12">
                  <c:set var="errorDetailDesc">
                        <form:errors path="detailDesc" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="detailDesc" class="form-label">Detail Desc</label>
                  <form:textarea type="text" class="form-control ${not empty errorDetailDesc ? 'is-invalid' : ''}" path="detailDesc"></form:textarea>
                  ${errorDetailDesc}
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <c:set var="errorPrice">
                        <form:errors path="price" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="price" class="form-label">Price</label>
                  <form:input type="text" class="form-control ${not empty errorPrice ? 'is-invalid' : ''}" path="price" />
                  ${errorPrice}
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <c:set var="errorQuantity">
                        <form:errors path="quantity" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="quantity" class="form-label">Quantity</label>
                  <form:input type="text" class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}" path="quantity"/>
                  ${errorQuantity}
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <c:set var="errorShortDesc">
                        <form:errors path="shortDesc" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="shortDesc" class="form-label">Short Desc</label>
                  <form:input type="text" class="form-control ${not empty errorShortDesc ? 'is-invalid' : ''}" path="shortDesc" />
                  ${errorShortDesc}
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <c:set var="errorSold">
                        <form:errors path="sold" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="sold" class="form-label">Sold</label>
                  <form:input type="text" class="form-control ${not empty errorSold ? 'is-invalid' : ''}" path="sold" />
                  ${errorSold}
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <c:set var="errorFactory">
                        <form:errors path="factory" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="factory" class="form-label">Factory</label>
                  <form:input type="text" class="form-control ${not empty errorFactory ? 'is-invalid' : ''}" path="factory" />
                  ${errorFactory}
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <c:set var="errorTarget">
                        <form:errors path="target" cssClass="invalid-feedback" />
                      </c:set>
                  <label for="target" class="form-label">Target</label>
                  <form:input type="text" class="form-control ${not empty errorTarget ? 'is-invalid' : ''}" path="target" />
                  ${errorTarget}
                </div>
                <div class="mb-3 col-12">
                  <label for="image" class="form-label">Image</label>
                  <input type="file" class="form-control" id="image" name="imageFile" />
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
              </form:form>
              </div>
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
