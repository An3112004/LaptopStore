<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Dashboard-User-Detail</title>
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
            <h1 class="mt-4">User - Detail</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item active">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">
                <a href="/admin/user">User</a>
              </li>
              <li class="breadcrumb-item active">Detail</li>
            </ol>
            <div class="container mt-8">
              <ul class="list-group mt-4 w-50 mx-auto">
                <li class="list-group-item">Id: ${user.id}</li>
                <li class="list-group-item">Name: ${user.name}</li>
                <li class="list-group-item">Email: ${user.email}</li>
                <li class="list-group-item">Address: ${user.address}</li>
                <li class="list-group-item">Phone: ${user.phone}</li>
                <li class="list-group-item">Role: ${user.role.name}</li>
              </ul>
              <a href="/admin/user" class="btn btn-primary">Back</a>
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
