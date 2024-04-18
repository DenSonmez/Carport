<%--
 this.name = name;
        this.length = length;
        this.amount = amount;
        this.unit = unit;
        this.description = description;

--%>


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

        <div class="container">
            <h1>Tak for dit køb!</h1>
            <p>Herunder kan du se en skræddersyet stykliste til netop din carport: </p>
        </div>

        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th class="text-start">Navn</th>
                <th class="text-center">Længde</th>
                <th class="text-center">Antal</th>
                <th class="text-center">Enhed</th>
                <th class="text-end">Beskrivelse</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.itemList}">
                <tr>
                    <td class="text-start align-middle"> ${item.name}</td>
                    <td class="text-center align-middle"> ${item.length}</td>
                    <td class="text-center align-middle">${item.unit}</td>
                    <td class="text-center align-middle">${item.amount}</td>
                    <td class="text-end align-middle">${item.description}</td>


                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form method="get" action="itemlist">
            <button type="submit" class="btn btn-primary" name="action" value="download">Download CSV</button>
            <button type="submit" class="btn btn-primary" name="action" value="model">Download STL</button>
        </form>



    </jsp:body>

</t:pagetemplate>