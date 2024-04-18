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

    </jsp:attribute>


    <jsp:body>

        <div class="container py-4">

            <div class="row align-items-md-stretch">
                <div class="col-md-8">
                    <div class="h-100 p-5 bg-light border rounded-3">
                        <h2>Tak for din bestilling!</h2><br>

                            Vi er enormt glade for at du har valgt Fog som leverandør til netop dit carport-projekt.<br>
                           <br>
                           Hos os er den personlige kontakt og rådgivning vores absolutte første prioritet, så derfor vil en af vores
                           dygtige medarbejdere kontakte dig indenfor meget kort tid, såfremt du har bestilt indenfor
                           vores bemandingstid <img src="images/tlf.png" width="17.5px;" class="img-fluid align-left"/>9-18.<br>
                           <br>
                            <em>OBS: Er din bestilling lavet udenfor arbejdstid, så vil vores medarbejder ringe dig op snarest og guide dig tilbage til dette betalingsflow.</em><br>
                            <br>

                            Når alt er på plads og Fog har modtaget betaling, så vil du over telefonen modtage din egen personlige<br> <b>8-cifrede kode</b> som bruges nederst på denne side til at låse op for styklisten. Afvent venligst koden fra medarbejderen du er i kontakt med:<br>
                            <br>
                          <%--  <label class="form-label">Fornavn</label> --%>

                            <form method="post" action="itemlist">
                                <div class="mb-3">
                                    <label for="inputPassword" class="form-label"></label>
                                    <input type="password" class="form-control" id="inputPassword" aria-describedby="passwordHelpBlock">
                                    <div id="passwordHelpBlock" class="form-text">Afslut derefter med 'Bekræft' knappen for at komme til din personlige stykliste<br> samt stl-fil, såfremt du ønsker en 3D version af din carport.</div><br>
                                    <button type="submit" class="btn btn-primary">Bekræft</button>
                                </div>
                            </form>

                    </div>
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>