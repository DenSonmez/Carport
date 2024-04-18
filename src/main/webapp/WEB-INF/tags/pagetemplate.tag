<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <c:if test="${sessionScope.user == null }">
            <a class="navbar-brand" href="login.jsp">
                </c:if>
                <c:if test="${sessionScope.user != null }">
                <a class="navbar-brand" href="carportbuilderlogin">
                    </c:if>
                    <!-- Her sender vi den over til servleten carportbuilderlogin, hvor den bar redirecter hvis man er logget ind. Læs mere på selve servleten. -->
                    <img src="${pageContext.request.contextPath}/images/logo.svg" width="100px;" class="img-fluid"/>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-start" id="navbarNavAltMarkup">

                    <div class="navbar-nav">
                        <c:if test="${sessionScope.user != null }">
                            <a class="nav-item nav-link"
                               href="${pageContext.request.contextPath}/carportbuilderlogin"><b>Carport</b></a>
                        </c:if>
                        <c:if test="${sessionScope.user == null }">
                            <a class="nav-item nav-link"
                               href="${pageContext.request.contextPath}/login.jsp"><b>Carport</b></a>
                        </c:if>

                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/bolig-design" target="_blank">
                            Bolig &
                            design</a>
                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/byggematerialer" target="_blank">Byggematerialer</a>
                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/el-belysning/" target="_blank">El
                            &
                            belysning</a>
                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/have-fritid" target="_blank">Have
                            &
                            fritid</a>
                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/vaerktoej"
                           target="_blank">Værktøj</a>
                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/maling" target="_blank">Maling</a>
                        <a class="nav-item nav-link" href="https://www.johannesfog.dk/restmarked"
                           target="_blank">RESTMARKED</a>
                        <a class="nav-item nav-link" href="https://fogpro.johannesfog.dk/pro"
                           target="_blank">Erhverv</a>
                    </div>
                    <!-- target="_blank" betyder at den vil åbne siden på en ny fane-->
                </div>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                   
                    <div class="navbar-nav d-flex justify-content-between">
                        <c:if test="${sessionScope.user == null }">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                        </c:if>

                        <c:if test="${sessionScope.user != null }">
                            <div>
                                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/logout">Log
                                    out</a>
                            </div>
                        </c:if>

                        <c:if test="${sessionScope.user.role.equalsIgnoreCase(\"admin\")}">
                            <div class="mt-2" style="margin-left:10px;" >
                                <a href="adminhub">
                                    <h5>Admin</h5></a>
                            </div>
                        </c:if>
                    </div>
                </div>

        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1>
        <jsp:invoke fragment="header"/>
    </h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3">
    <hr/>
    <div class="row mt-3">
        <div class="col text-start">
            <b>Kundeservice</b><br/>
            <a class="link-black" href="https://www.johannesfog.dk/om-fog/forretninger/kontakt" target="_blank">Kontakt
                Fog</a><br/>
            <a class="link-black" href="https://www.johannesfog.dk/om-fog/kundeservice/fortrydelse-og-returnering"
               target="_blank">Fortrydelse og returnering</a>
        </div>
        <div class="col text-center">
            <b>Aktuelt</b><br/>
            <a class="link-black" href="https://www.johannesfog.dk/om-fog/aktuelt/aviser-og-kataloger" target="_blank">Aviser
                og kataloger</a><br/>
            <a class="link-black" href="https://www.johannesfog.dk/om-fog/nyhedsbrev" target="_blank">Tilmeld
                nyhedsbrev</a>
        </div>
        <div class="col text-center">
            <b>Om Fog</b><br/>
            <a class="link-black" href="https://www.johannesfog.dk/om-fog/forretninger" target="_blank">Åbningstider</a><br/>
            <a class="link-black" href="https://www.johannesfog.dk/om-fog" target="_blank">Om Fog</a>
        </div>
        <div class="col text-end">
            <img src="${pageContext.request.contextPath}/images/logo.svg" alt="Fog logo" width="50px;"
                 class="img-fluid"/> <br/>
            <b>Johannes Fog A/S</b><br/>
            Firskovvej 20<br/>
            2800 Lyngby<br/>
            CVR-nr. 16314439

        </div>
    </div>

</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>