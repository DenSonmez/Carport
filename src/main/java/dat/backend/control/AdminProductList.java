package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Product;
import dat.backend.model.entities.ProductAndProductVariant;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ProductFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductList", value = "/admin-productlist")
public class AdminProductList extends HttpServlet {

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
                request.setAttribute("adminProductList", products);
                request.getRequestDispatcher("WEB-INF/admin-productlist.jsp").forward(request, response);

            }
        }

        request.setAttribute("errormessage", "Du er ikke en admin");
        request.getRequestDispatcher("error.jsp").forward(request, response);


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User _user = (User) request.getSession().getAttribute("user");
        if (!_user.getRole().equalsIgnoreCase("admin")) {
            request.setAttribute("besked", "Du er ikke en admin");
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        try {
            List<ProductAndProductVariant> productList = ProductFacade.getAllProducts(connectionPool);
            request.setAttribute("adminProductList", productList); // Gem produktlisten i request
            request.getRequestDispatcher("admin-productlist.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }


        response.sendRedirect("admin-productlist");
    }

}

