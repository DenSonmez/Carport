package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.CarportBuilderHelper;
import dat.backend.model.services.DownloadHelper;
import dat.backend.model.services.ThreeDBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ItemListServlet", value = "/itemlist")
public class ItemListServlet extends HttpServlet {

    private ConnectionPool connectionPool;
    private List<CompleteProduct> itemList;
    private byte[] stlModelData;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("download")){
            String csv = DownloadHelper.convertItemListToCSV(itemList);
            DownloadHelper.sendDownloadCSVFileFromText(csv, response);
        }
        if(action.equalsIgnoreCase("model")){
            if(itemList != null){
                DownloadHelper.sendDownloadSTLFileFromBytes(stlModelData, response);
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        itemList = new ArrayList<>();
        HttpSession session = request.getSession();
        String dimensions = (String)session.getAttribute("dimensions");
        Orders order = (Orders)session.getAttribute("order");
        if(dimensions == null){
            dimensions = ((String)request.getParameter("dimensions"));
        }

        String[] split = dimensions.split(":");
        String widthString = split[0];
        String lengthString = split[1];
        String orderIDString = "";
        int orderID = -1;
        if(split.length > 2){
            orderIDString = split[2];
            orderID = Integer.parseInt(orderIDString);
        }

        int width = Integer.parseInt(widthString);
        int length = Integer.parseInt(lengthString);



        itemList = null;
        try {
            if(order == null){
                order = OrderFacade.getOrderById(orderID, connectionPool);
            }
            itemList = CarportBuilderHelper.generateItemList(width, length, order);
            stlModelData = ThreeDBuilder.getModel(width, length, itemList, connectionPool);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("itemList", itemList);

        request.getRequestDispatcher("WEB-INF/itemList.jsp").forward(request, response);
    }
}
