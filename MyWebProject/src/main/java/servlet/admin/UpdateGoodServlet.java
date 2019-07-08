package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoHibImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/allGoods/updateGood")
public class UpdateGoodServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UpdateGoodServlet.class);
    private static final GoodDao goodDao = new GoodDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("id", id);
        request.getRequestDispatcher("/admin/updateGood.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = null;
        if (!request.getParameter("price").equals("")) {
            price = Double.parseDouble(request.getParameter("price"));
        }
        String description = request.getParameter("description");
        logger.debug("start update good '" + name + "' information");
        goodDao.updateGood(id, name, price, description);
        request.setAttribute("update", true);
        logger.debug("Good id=" + id + ", name=" + name + " update information successful");
        getServletContext().getRequestDispatcher("/admin/allGoods").forward(request, response);
    }
}
