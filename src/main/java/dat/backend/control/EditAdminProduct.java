package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ProductFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditAdminProduct", value = "/editadminproduct")
public class EditAdminProduct extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                ProductAndProductVariant productAndProductVariant = null;
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    productAndProductVariant = ProductFacade.getProduct(id, connectionPool);

                } catch (DatabaseException e) {
                    request.setAttribute("errormessage", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("product", productAndProductVariant);
                request.getRequestDispatcher("WEB-INF/admin-editproduct.jsp").forward(request, response);

            }
        }

        request.setAttribute("errormessage", "Du er ikke en admin");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {

            String editName = request.getParameter("editName");
            String editDescription = request.getParameter("editDescription");
            Unit editUnit = Unit.valueOf(request.getParameter("editUnit"));
            float editPricePrUnit = Float.parseFloat(request.getParameter("editPricePrUnit"));
            ProductType editType = ProductType.valueOf(request.getParameter("editType"));
            float editLength = Float.parseFloat(request.getParameter("editLength"));
            float editWidth = Float.parseFloat(request.getParameter("editWidth"));
            float editHeight = Float.parseFloat(request.getParameter("editHeight"));

            String[] editButton = request.getParameter("editButton").split("-");
            // Split prøver at finde tegnet i .jsp'en og splitter det op i et array, så index 0 holder produktvariantId og index 1 holder produkt id som en String.

            int productVariantId = Integer.parseInt(editButton[0]);
            int productId = Integer.parseInt(editButton[1]);

            ProductFacade.editProduct(editName, productId,editDescription,editUnit,editPricePrUnit,editType, connectionPool);
            ProductFacade.editProductVariant(editHeight,editWidth,editLength,productId,productVariantId,connectionPool);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        response.sendRedirect("admin-productlist");


    }
}
