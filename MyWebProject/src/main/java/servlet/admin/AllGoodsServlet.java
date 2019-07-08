package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoHibImpl;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/allGoods")
public class AllGoodsServlet extends HttpServlet {
    private static final GoodDao goodsDao = new GoodDaoHibImpl();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> allGoods = goodsDao.getAll(Good.class);
        request.setAttribute("allGoods", allGoods);
        request.getRequestDispatcher("/admin/allGoods.jsp").forward(request, response);
    }
}
