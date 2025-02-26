<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%> <%@taglib
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
    <title>Dashboard-User-Create</title>
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
            <h1 class="mt-4">User</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item active">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">
                <a href="/admin/user">User</a>
              </li>
              <li class="breadcrumb-item active">Create</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                  <h3>Create User</h3>
                  <hr />
                  <form:form
                    action="/admin/user/add"
                    method="post"
                    modelAttribute="newUser"
                    class="row"
                  >
                    <div class="mb-3 col-12">
                      <c:set var="errorName">
                        <form:errors path="name" cssClass="invalid-feedback" />
                      </c:set>
                      <label for="name" class="form-label">Name</label>
                      <form:input
                        type="text"
                        class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                        id="name"
                        path="name"
                      />
                      ${errorName}
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <c:set var="errorPassword">
                        <form:errors
                          path="password"
                          cssClass="invalid-feedback"
                        />
                      </c:set>
                      <label for="password" class="form-label">Password</label>
                      <form:input
                        type="password"
                        class="form-control ${not empty errorPassword ? 'is-invalid' : ''}"
                        id="password"
                        path="password"
                      />
                      ${errorPassword}
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <c:set var="errorEmail">
                        <form:errors path="email" cssClass="invalid-feedback" />
                      </c:set>
                      <label for="email" class="form-label">Email</label>
                      <form:input
                        type="email"
                        class="form-control ${not empty errorEmail ? 'is-invalid' : ''}"
                        id="email"
                        path="email"
                      />
                      ${errorEmail}
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <c:set var="errorAddress">
                        <form:errors
                          path="address"
                          cssClass="invalid-feedback"
                        />
                      </c:set>
                      <label for="address" class="form-label">Address</label>
                      <form:input
                        type="text"
                        class="form-control ${not empty errorAddress ? 'is-invalid' : ''}"
                        id="address"
                        path="address"
                      />
                      ${errorAddress}
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <c:set var="errorPhone">
                        <form:errors path="phone" cssClass="invalid-feedback" />
                      </c:set>
                      <label for="phone" class="form-label">Phone</label>
                      <form:input
                        type="text"
                        class="form-control ${not empty errorPhone ? 'is-invalid' : ''}"
                        id="phone"
                        path="phone"
                      />
                      ${errorPhone}
                    </div>
                    <div class="mb-3 col-12">
                      <c:set var="errorRole">
                        <form:errors
                          path="role.id"
                          cssClass="invalid-feedback"
                        />
                      </c:set>
                      <label for="role" class="form-label">Role</label>
                      <form:select
                        class="form-select ${not empty errorRole ? 'is-invalid' : ''}"
                        path="role.id"
                        id="role"
                        name="role.id"
                      >
                        <c:forEach var="role" items="${roles}">
                          <form:option
                            value="${role.id}"
                            label="${role.name}"
                          />
                        </c:forEach>
                      </form:select>
                      ${errorRole}
                    </div>
                    <button type="submit" class="btn btn-primary">
                      Create
                    </button>
                  </form:form>
                </div>
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
