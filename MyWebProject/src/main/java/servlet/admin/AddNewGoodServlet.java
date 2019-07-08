package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoHibImpl;
import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/allUsers/addNewGood")
public class AddNewGoodServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddNewGoodServlet.class);
    private static final GoodDao goodDao = new GoodDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin/addNewGood.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        Good good = new Good(name, description, price);
        if (goodDao.checkGood(name)) {
            request.setAttribute("add", false);
            logger.debug("Add new good \"" + name + "\" fail");
            getServletContext().getRequestDispatcher("/admin/addNewGood.jsp").forward(request, response);
        } else {
            goodDao.add(good);
            request.setAttribute("add", true);
            logger.debug("Add new good \"" + name + "\" successful");
            getServletContext().getRequestDispatcher("/admin/allGoods").forward(request, response);
        }
    }
}
