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

@WebServlet(value = "/admin/allGoods/deleteGood")
public class DeleteGoodServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DeleteGoodServlet.class);
    private static final GoodDao goodDao = new GoodDaoHibImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        goodDao.delete(Good.class, id);
        logger.debug("Good Id " + id + " delete successful");
        request.setAttribute("delete", true);
        getServletContext().getRequestDispatcher("/admin/allGoods").forward(request, response);
    }
}
