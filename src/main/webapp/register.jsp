<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Register
    </jsp:attribute>

    <jsp:attribute name="footer">
            Register
    </jsp:attribute>

    <jsp:body>

        <h3>Du kan registrere dig her</h3>

        <c:if test="${requestScope.message != null}">
            <p>${requestScope.message}</p>
        </c:if>

        <div class="h-48 p-5 bg-light border rounded-3 mt-2">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <form method="post" action="register">
                        <div class="mb-2">
                            <label for="firstname" class="form-label">Fornavn</label>
                            <input type="text" class="form-control" id="firstname" name="firstname" required>
                        </div>
                        <div class="mb-2">
                            <label for="lastname" class="form-label">Efternavn</label>
                            <input type="text" class="form-control" id="lastname" name="lastname" required>
                        </div>
                        <div class="mb-2">
                            <label for="address" class="form-label">Adresse</label>
                            <input type="text" class="form-control" id="address" name="address" required>
                        </div>
                        <div class="mb-2">
                            <label for="zipcode" class="form-label">Postnummer</label>
                            <input type="text" class="form-control" id="zipcode" name="zipcode" required>
                        </div>
                        <div class="mb-2">
                            <label for="phonenumber" class="form-label">Telefon nr</label>
                            <input type="text" class="form-control" id="phonenumber" name="phonenumber" required>
                        </div>

                        <div class="mb-2">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-2">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-2">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmpassword" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form><br/>


                </div>
            </div>
        </div>
        </div>

        <br>


    </jsp:body>
</t:pagetemplate>