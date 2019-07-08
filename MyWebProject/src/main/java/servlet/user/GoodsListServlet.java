package servlet.user;

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
import java.util.List;

@WebServlet(value = "/goodsList")
public class GoodsListServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GoodsListServlet.class);
    private static final GoodDao goodsDao = new GoodDaoHibImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> allGoods = goodsDao.getAll(Good.class);
        request.setAttribute("allGoods", allGoods);
        request.getRequestDispatcher("user/allGoods.jsp").forward(request, response);
    }
}