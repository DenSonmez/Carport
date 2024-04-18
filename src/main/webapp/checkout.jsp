<%--
  Created by IntelliJ IDEA.
  User: rasmu
  Date: 11-05-2023
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Checkout
    </jsp:attribute>

    <jsp:body>

        <div class="container py-4">

            <div class="row align-items-md-stretch">
                <div class="col-md-8">
                    <div class="h-100 p-5 bg-light border rounded-3">

                        <h2>Din kurv:</h2>
                        <form method="post" action="submit">
                            <table class="table table-striped mt-4">
                                <thead>


                                <tr>
                                    <td class="text-start align-middle" style="color: #6f42c1"><h5>Type</h5>
                                    </td>
                                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Mål</h5>
                                    </td>
                                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Pris*</h5>
                                    </td>
                                </tr>
                                </thead>

                                <tr>
                                    <td class="text-start align-middle"><img src="images/Carport.png" width="120px;" class="img-fluid align-left"/>${requestScope.order} </td>
                                    <td class="text-center align-middle">${requestScope.width} cm x ${requestScope.length} cm</td>
                                    <td class="text-center align-middle">${sessionScope.order.price} kr</td>
                                    <td class="text-center align-middle"><button formaction="removechosenorder" formmethod="get" type="submit" class="btn btn-info btn-sm">Fjern</button></td>

                                </tr>

                            </table>
                        </form>


                        <p><em>En ordrebekræftelse sendes til din email.</em></p>
                        <p><em>*Bemærk at prisen vil variere afhængigt af endelig<br> bestilling efter kontakt med salgsafdeling.<br>
                            Du vil blive sat i kontakt med disse så snart din bestilling er valgt bekræftet.</em></p>

                    </div>

                </div>
                <div class="col-md-4">
                    <div class="h-48 p-5 bg-light border rounded-3">
                        <h5>Har du brug for hjælp?</h5><br>
                        <div class="col">
                            <p><img src="images/mail.png" width="35px;" class="img-fluid align-left"/><em> Kontakt@Fog.dk</em></p>
                            <p><img src="images/tlf.png" width="35px;" class="img-fluid align-left"/><em> +45 40404040</em></p>
                            <p><em>Bemærk at der <b>altid</b> er gratis fragt hos Fog!</em></p>
                        </div>
                    </div>
                    <form method="post" action="carportbuilder" class="text-center mt-3">
                        <button type="submit" class="btn btn-primary" name="dimensions" value="${requestScope.width}:${requestScope.length}">Bekræft bestilling</button>
                    </form>
                </div>

            </div>

            <footer class="pt-3 mt-4 text-muted border-top">
            </footer>
        </div>

    </jsp:body>

</t:pagetemplate>
