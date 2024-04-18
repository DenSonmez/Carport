<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<!-- Vi skriver ../ foran error fordi vi er i WEB-INF mappen, og error ligger kun i webapp mappen, så vi går én mappe tilbage ved at skrive ../ -->

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
            Admin Hub
    </jsp:attribute>


    <jsp:body>

        <div class="container">
        <h1 class="text-center">Admin Hub</h1>
        <div class="row justify-content-center">

            <div class="col-md-auto">
                <a href="${pageContext.request.contextPath}/admin-users" class="h3"><b>Kundeliste</b></a><br/>
            </div>

            <div class="col-md-auto">
                <a href="${pageContext.request.contextPath}/admin-orders" class="h3"><b>Ordre</b></a><br/>
            </div>

            <div class="col-md-auto">
                <a href="${pageContext.request.contextPath}/admin-productlist" class="h3"><b>Produktliste</b></a><br/>
            </div>

            <div class="col-md-auto">
                <a href="${pageContext.request.contextPath}/adminAddProduct" class="h3"><b>Tilføj Produkt</b></a><br/>
            </div>

        </div>
        <div class="container py-4">

            <div class="row align-items-md-stretch">
                <div>
                    <div class="h-100 p-5 bg-light border rounded-3">
                        <p>
                        <div class="text-center fw-bold">
                            På Admin Hub kan du håndtere brugerkonti, ordrer og produktlisten. Ved at klikke på de
                            forskellige
                            menuer herunder, vil du kunne tilgå dem og se de forskellige ting i databasen.
                            <br>
                        </div>
                        <br>
                        Hvis du vil administrere ordrer og salg, kan du klikke på "Admin orders". Her kan du se
                        en oversigt over eksisterende ordrer, ændre pris for ordrer og håndtere eventuelle
                        problemer eller spørgsmål fra kunder.<br>
                        <br>
                        Hvis du har brug for at opdatere eller tilføje nye produkter til systemet, kan du klikke
                        på "Produktliste". Dette giver dig mulighed for at se en liste over tilgængelige produkter
                        og redigere deres egenskaber, såsom pris eller beskrivelse.<br>
                        <br>
                        Admin Hub gør det nemt for dig at udføre administrative opgaver uden at skulle have
                        tekniske færdigheder eller kendskab til programmet. Klik blot på de relevante links
                        for at få adgang til de ønskede funktioner og forenkle din daglige arbejdsgang.<br>

                        </p>
                    </div>
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>