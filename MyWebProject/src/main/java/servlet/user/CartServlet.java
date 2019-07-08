package servlet.user;

import dao.CartDao;
import dao.GoodDao;
import dao.impl.CartDaoHibImpl;
import dao.impl.GoodDaoHibImpl;
import model.Good;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/cart")
public class CartServlet extends HttpServlet {
    private static final CartDao cartDao = new CartDaoHibImpl();
    private static final GoodDao goodDao = new GoodDaoHibImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double toPay = 0.0;
        User user = (User) request.getSession().getAttribute("user");
        List<Item> allItems = cartDao.getAllByNotPayed(user.getId());
        request.setAttribute("allItems", allItems);
        List<Good> goodsFromCart = new ArrayList<>();
        for (Item item : allItems) {
            Good good = goodDao.getById(Good.class, item.getGoodId());
            goodsFromCart.add(good);
            toPay += item.getQuantity() * good.getPrice();
        }
        request.setAttribute("allGoodsInCart", goodsFromCart);
        request.setAttribute("toPay", toPay);
        request.getRequestDispatcher("user/cart.jsp").forward(request, response);
    }
}