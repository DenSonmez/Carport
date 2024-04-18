<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">



       </jsp:attribute>

    <jsp:attribute name="footer">
        Velkommen til Fog
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8">
                    <h3 class="text-center"> <b>Bestil Quick-Byg tilbud - carport med fladt tag</b></h3><br>
                    <p class="text-center">
                        Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram.
                        Tilbud og skitsetegning fremsendes med post hurtigst muligt.
                        Ved bestilling medfølger standardbyggevejledning.
                        Udfyld formularen omhyggeligt og klik på "Bestil tilbud".
                        Felter markeret med * SKAL udfyldes!
                    </p>

                </div>
            </div>
        </div>
<br>
        <br>
        <div style="display: flex; justify-content: center;">
            <div><form method="get" action="carportbuilder">

                <select class="form-select" style="color: #6f42c1; width: 600px; text-align-last: center;" aria-label="Default select example" name="width" required>
                    <option value="" disabled selected>Carport bredde</option>
                    <option value="240"> 240 cm </option>
                    <option value="270"> 270 cm </option>
                    <option value="300"> 300 cm </option>
                    <option value="330"> 330 cm </option>
                    <option value="360"> 360 cm </option>
                    <option value="390"> 390 cm </option>
                    <option value="420"> 420 cm </option>
                    <option value="450"> 450 cm </option>
                    <option value="480"> 480 cm </option>
                    <option value="510"> 510 cm </option>
                    <option value="540"> 540 cm </option>
                    <option value="570"> 570 cm </option>
                    <option value="600"> 600 cm </option>

                </select>


                <select class="form-select" style="color: #6f42c1; width: 600px; text-align-last: center;" aria-label="Default select example" name="length" required>
                    <option value="" disabled selected>Carport længde</option>
                    <option value="240"> 240 cm </option>
                    <option value="270"> 270 cm </option>
                    <option value="300"> 300 cm </option>
                    <option value="330"> 330 cm </option>
                    <option value="360"> 360 cm </option>
                    <option value="390"> 390 cm </option>
                    <option value="420"> 420 cm </option>
                    <option value="450"> 450 cm </option>
                    <option value="480"> 480 cm </option>
                    <option value="510"> 510 cm </option>
                    <option value="540"> 540 cm </option>
                    <option value="570"> 570 cm </option>
                    <option value="600"> 600 cm </option>
                    <option value="630"> 630 cm </option>
                    <option value="660"> 660 cm </option>
                    <option value="690"> 690 cm </option>
                    <option value="750"> 750 cm </option>
                    <option value="780"> 780 cm </option>
                </select>
                <br>

                <div class="container d-flex justify-content-center align-items-center vh-50">
                    <div class="btn-group" role="group">
                        <button type="submit" class="btn btn-primary">Bestil tilbud</button>
                    </div>
                </div>

            </form>
            </div>
        </div>



    </jsp:body>

</t:pagetemplate>