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
            Admin Orders
    </jsp:attribute>


    <jsp:body>

        <h1>Ordre</h1>
        <div class="text-end mt-4">
            <a href="adminhub" class="btn btn-primary">Tilbage</a>
        </div>

        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th class="text-start">Email</th>
                <th class="text-center">User ID</th>
                <th class="text-center">Timestamp</th>
                <th class="text-center">Price</th>
                <th class="text-center">Length</th>
                <th class="text-end">Width</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orderList}">
                <tr>
                    <td class="text-start align-middle"> ${order.email}</td>
                    <td class="text-center align-middle"> ${order.user_id}</td>
                    <td class="text-center align-middle">${order.timestamp}</td>
                    <td class="text-center align-middle">${order.price}</td>
                    <td class="text-center align-middle">${order.length}</td>
                    <td class="text-end align-middle">${order.width}</td>

                    <td class="text-end align-middle">
                    <div class="d-flex justify-content-end">
                        <form method="post" action="itemlist">
                            <button name="dimensions" value="${order.width}:${order.length}:${order.orderId}" type="submit" class="ms-2 btn btn-info btm-sm">Se Styklisten</button>
                        </form>
                        <form method="post" action="removeadminorder">
                            <button type="submit" class="ms-2 btn btn-info btm-sm" name="id" value="${order.orderId}">Fjern</button>
                        </form>
                        <form method="get" action="editorderprice">
                            <button type="submit" class="ms-2 btn btn-info btm-sm" name="id" value="${order.orderId}">Rediger pris</button>
                        </form>

                    </div>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>

</t:pagetemplate>