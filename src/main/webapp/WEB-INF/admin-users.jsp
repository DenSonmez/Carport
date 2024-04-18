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
            Admin Users
    </jsp:attribute>


    <jsp:body>

        <h1>Brugere</h1>
        <div class="text-end mt-4">
            <a href="adminhub" class="btn btn-primary">Tilbage</a>
        </div>


        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th class="text-start">Fornavn</th>
                <th class="text-center">Efternavn</th>
                <th class="text-center">E-mail</th>
                <th class="text-end">Telefonnummer</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.userList}">
                <tr>
                    <td class="text-start align-middle"> ${user.firstname}</td>
                    <td class="text-center align-middle">${user.lastname}</td>
                    <td class="text-center align-middle">${user.email}</td>
                    <td class="text-end align-middle">+45 ${user.phonenumber}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </jsp:body>

</t:pagetemplate>