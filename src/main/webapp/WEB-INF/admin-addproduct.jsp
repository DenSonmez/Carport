<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>



<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
          Tilføje Produkt
    </jsp:attribute>


    <jsp:body>

        <div class="container">
            <h1>Tilføj produkt</h1>
            <div class="text-end mt-4">
                <a href="adminhub" class="btn btn-primary">Tilbage</a>
            </div>

        <div class="h-48 p-5 bg-light border rounded-3 mt-2">
        <table class="table table-striped mt-4">
            <c:if test="${requestScope.success != null}">
                <p>
                    Success: ${requestScope.success}
                </p>

            </c:if>

            <thead>
            <tr>
                <th class="text-start"><label for="name" class="form-label">Navn</label>
                    <input type="text" class="form-control" id="name" name="name" required>
              </th>
                <th class="text-center"><label for="description" class="form-label">Beskrivelse</label>
                    <input type="text" class="form-control" id="description" name="description" required>
              </th>
                <th class="text-center"><label for="unit" class="form-label">Enhed</label>
                    <input type="text" class="form-control" id="unit" name="unit" required>
                </th>
                <th class="text-center"><label for="pricePrUnit" class="form-label">Pris pr. enhed</label>
                    <input type="text" class="form-control" id="pricePrUnit" name="pricePrUnit" required>
              </th>
                <th class="text-center"><label for="type" class="form-label">Type</label>
                    <input type="text" class="form-control" id="type" name="type" required>
                </th>
                <th class="text-center"><label for="length" class="form-label">Længde</label>
                    <input type="text" class="form-control" id="length" name="length" required>
               </th>
                <th class="text-center"><label for="width" class="form-label">Bredde</label>
                    <input type="text" class="form-control" id="width" name="width" required>
               </th>
                <th class="text-end"><label for="height" class="form-label">Højde</label>
                    <input type="text" class="form-control" id="height" name="height" required>
               </th>
            </tr>
            </thead>
        </table>


            <div class="text-end">
            <button type="submit" class="btn btn-info mt-1">Tilføj</button>
            </div>
        </div>
        </form>
        </div>



    </jsp:body>

</t:pagetemplate>