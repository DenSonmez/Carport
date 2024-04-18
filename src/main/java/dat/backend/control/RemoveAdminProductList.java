package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Product;
import dat.backend.model.entities.ProductAndProductVariant;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveAdminProductList", value = "/removeadminproductlist")
public class RemoveAdminProductList extends HttpServlet {
    // Denne klasse skal kunne fjerne ting på produktlisten, og fjerne det fra productvariant.
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
                List<ProductAndProductVariant> products = null;
                try {
                    products = ProductFacade.getAllProducts(connectionPool);

                } catch (DatabaseException e) {
                    request.setAttribute("errormessage", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("productList", products);
                request.getRequestDispatcher("WEB-INF/admin-productlist.jsp").forward(request, response);

            }
        }

        request.setAttribute("errormessage", "Du er ikke en admin");
        request.getRequestDispatcher("error.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            List<ProductAndProductVariant> productList = ProductFacade.getAllProducts(connectionPool);

            request.setAttribute("productList", productList); // Gem produktlisten i request
            ProductFacade.removeProductVariant(id, connectionPool);
            ProductFacade.removeProductList(id, connectionPool);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        response.sendRedirect("admin-productlist");
    }
}

