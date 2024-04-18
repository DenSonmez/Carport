package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.OrderView;
import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveAdminOrder", value = "/removeadminorder")
public class RemoveAdminOrder extends HttpServlet {
    // Denne klasse skal give admin mulighed for at fjerne en order fra admin siden.
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<OrderView> orders = null;

        try {

            orders = AdminFacade.getAllOrdersAndUserInfo(connectionPool);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("orderList", orders);

        request.getRequestDispatcher("login").forward(request, response);
        // Prøver man som normal bruger eller bare generelt komme hen til /removeadminorder bliver du redirecter over til login.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            List<OrderView> orderList = AdminFacade.getAllOrdersAndUserInfo(connectionPool);

            request.setAttribute("orderList", orderList); // Gem orderlisten i request
            ItemListFacade.removeItemListOrder(id, connectionPool);
            OrderFacade.removeOrderById(id, connectionPool);


        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        response.sendRedirect("admin-orders");
    }
}
