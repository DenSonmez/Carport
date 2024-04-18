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
            Edit Product
    </jsp:attribute>


    <jsp:body>

        <div class="container">
            <h1>Edit Product</h1>
            <div class="row justify-content-end">
                <div class="col-md-6">
                    <form>
                        <div class="text-end">
                            <input type="button" value="Tilbage" onclick="history.back()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <form action="editadminproduct" method="post">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-6">
                        <table class="table table-striped mt-1">
                            <tr>
                                <td><label for="name" class="form-label">Produktnavn</label></td>
                                <td><input name="editName" style="width: 300px" type="text" id="name" placeholder="${requestScope.product.name}"></td>
                            </tr>
                            <tr>
                                <td><label for="description" class="form-label">Beskrivelse af produktet</label></td>
                                <td><input name="editDescription" style="width: 300px" type="text" id="description" placeholder="${requestScope.product.description}"></td>
                            </tr>
                            <tr>
                                <td><label for="unit" class="form-label">Enhed</label></td>
                                <td><input name="editUnit" style="width: 300px" type="text" id="unit" placeholder="${requestScope.product.unit}"></td>
                            </tr>
                            <tr>
                                <td><label for="pricePrUnit" class="form-label">Pris pr. enhed</label></td>
                                <td><input name="editPricePrUnit" style="width: 300px" type="text" id="pricePrUnit" placeholder="${requestScope.product.pricePrUnit}"></td>
                            </tr>
                            <tr>
                                <td><label for="type" class="form-label">Type</label></td>
                                <td><input name="editType" style="width: 300px" type="text" id="type" placeholder="${requestScope.product.type}"></td>
                            </tr>
                            <tr>
                                <td><label for="length" class="form-label">Længde</label></td>
                                <td><input name="editLength" style="width: 300px" type="text" id="length" placeholder="${requestScope.product.length}"></td>
                            </tr>
                            <tr>
                                <td><label for="width" class="form-label">Bredde</label></td>
                                <td><input name="editWidth" style="width: 300px" type="text" id="width" placeholder="${requestScope.product.width}"></td>
                            </tr>
                            <tr>
                                <td><label for="height" class="form-label">Højde</label></td>
                                <td><input name="editHeight" style="width: 300px" type="text" id="height" placeholder="${requestScope.product.height}"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><button name="editButton" value="${requestScope.product.productVariantId}-${requestScope.product.productId}" type="submit" class="btn btn-info">Opdater</button></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>


    </jsp:body>

</t:pagetemplate>