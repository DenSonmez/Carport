package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CarportBuilderLogin", value = "/carportbuilderlogin")
public class CarportBuilderLogin extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/carportbuilder.jsp").forward(request, response);
        // Her redirecter vi til carportbuilder, og grunden til servletten findes er fordi den ligger i WEB-INF, så vi er nødt til at bruge en servlet, for at komme derover.
        //Vi kan ikke bare bruge CarportBuilder til at redirect med da den klasses get metode tager os videre til checkout, så den her klasse er bare et mellemled.

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
