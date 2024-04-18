<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>


        <jsp:body>

            <div class="h-48 p-5 bg-light border rounded-3 mt-2">




                <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-4">
                        <form method="post" action="login">
                            <div class="mb-2">
                                <p><em>Log venligst ind for at kunne bestille samt<br> se din nuværende ordre hos Fog. </em></p>
                                <label for="Email" class="form-label">Email</label>
                                <input type="text" class="form-control" id="Email" name="Email" required>

                            </div>
                            <form method="post" action="login">
                            <div class="mb-2">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <p> Glemt password? Klik <a href="https://www.johannesfog.dk/om-fog/forretninger/kontakt" target="_blank">her</a></p>
                            <button type="submit" class="btn btn-primary">Login</button>
                        </form><br/>
                        <p>Er du ikke oprettet hos Fog, så fortsæt venligst her for at oprette dig som <a href="register.jsp">ny kunde</a></p>
                    </div>
                </div>
            </div>

           <%-- <div style="position:relative; right:200px; top:2px;" class="h-48 p-5 bg-light border rounded-3 mt-2">
           <h5>Log venligst ind for at kunne bestille samt<br> se din nuværende ordre hos Fog. </h5>--%>
            </div>


                </div>


            <%--     <form action="login" method="post">
                     <label for="username">Username: </label>
                     <input type="text" id="username" name="username"/>
                     <label for="password">Password: </label>
                     <input type="password" id="password" name="password"/>
                     <input type="submit"  value="Log in"/>
                 </form>--%>




        </jsp:body>

</t:pagetemplate>