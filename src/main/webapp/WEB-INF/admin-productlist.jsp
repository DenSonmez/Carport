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
            Admin Produktliste
    </jsp:attribute>


    <jsp:body>

        <div class="container">
            <h1>Produkter</h1>
            <div class="d-flex justify-content-between mt-4">
                <div>
                    <a href="adminAddProduct" class="btn btn-primary">Tilføj produkt</a>
                </div>
                <div>
                    <a href="adminhub" class="btn btn-primary">Tilbage</a>
                </div>
            </div>
        </div>



        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th class="text-start">Navn</th>
                <th class="text-center">Beskrivelse</th>
                <th class="text-center">Unit</th>
                <th class="text-center">Pris pr. enhed</th>
                <th class="text-center">Type</th>
                <th class="text-center">Id</th>
                <th class="text-center">Produkt Id</th>
                <th class="text-center">Længde</th>
                <th class="text-end">Bredde</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.adminProductList}">
                <tr>
                    <td class="text-start align-middle"> ${product.name}</td>
                    <td class="text-center align-middle">${product.description}</td>
                    <td class="text-center align-middle">${product.unit}</td>
                    <td class="text-center align-middle">${product.pricePrUnit}</td>
                    <td class="text-center align-middle">${product.type}</td>
                    <td class="text-center align-middle">${product.productVariantId}</td>
                    <td class="text-center align-middle">${product.productId}</td>
                    <td class="text-center align-middle">${product.length}</td>
                    <td class="text-end align-middle">${product.width}</td>

                    <td class="text-end align-middle">
                        <div class="d-flex justify-content-start">
                            <form method="get" action="editadminproduct">
                                <button type="submit" class="ms-2 btn btn-info btm-sm" name="id"
                                        value="${product.productVariantId}">Opdater
                                </button>
                            </form>
                            <form method="post" action="removeadminproductlist">
                                <button type="submit" class="ms-2 btn btn-info btm-sm" name="id"
                                        value="${product.productVariantId}">Fjern
                                </button>
                            </form>

                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </jsp:body>

</t:pagetemplate>