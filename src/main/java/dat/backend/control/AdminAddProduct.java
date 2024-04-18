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



@WebServlet(name = "AdminAddProduct", value = "/adminAddProduct")
public class AdminAddProduct extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String unitString = request.getParameter("unit");
        Unit unit = Unit.valueOf(unitString);
        float pricePrUnit = Float.parseFloat(request.getParameter("pricePrUnit"));
        String typeString = request.getParameter("type");
        ProductType type = ProductType.valueOf(typeString);
         float length =Integer.parseInt(request.getParameter("length"));
         float width = Integer.parseInt(request.getParameter("width"));
         float height =Integer.parseInt(request.getParameter("height"));

       int productId = -1;

        try {
            productId = ProductFacade.addProduct(name, description, unit, pricePrUnit, type, connectionPool);
            ProductFacade.addProductVariant(productId,length, width, height, connectionPool);
            request.setAttribute("success","Tilføjelse af produktet er fuldført");

            request.getRequestDispatcher("WEB-INF/admin-addproduct.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {

                request.getRequestDispatcher("WEB-INF/admin-addproduct.jsp").forward(request, response);

            }
        }

        request.setAttribute("errormessage", "Du er ikke en admin");
        request.getRequestDispatcher("error.jsp").forward(request, response);


    }

}