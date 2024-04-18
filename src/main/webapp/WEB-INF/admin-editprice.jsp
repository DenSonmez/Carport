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
            Edit Price
    </jsp:attribute>


    <jsp:body>

        <h1>Rediger pris</h1>
        <div class="text-end mt-4">
            <a href="admin-orders" class="btn btn-primary">Tilbage</a>
        </div>
        <form action="editorderprice" method="post">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-6">
                        <table class="table table-striped mt-1">
                            <tr>
                                <td><label for="name" class="form-label">Rediger pris</label></td>
                                <td><input name="price" style="width: 300px" type="text" id="name" placeholder="${requestScope.order.price}"></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center">
                                    <button type="submit" class="ms-2 btn btn-info btm-sm" name="id" value="${requestScope.order.orderId}">Rediger pris</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>




    </jsp:body>

</t:pagetemplate>