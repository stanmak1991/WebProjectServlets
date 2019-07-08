package servlet.user;

import dao.CartDao;
import dao.impl.CartDaoHibImpl;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/addToCart")
public class AddToCartServlet extends HttpServlet {
    private static final CartDao cartDao = new CartDaoHibImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long goodId = Long.parseLong(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        Item item = new Item(goodId, 1, false, user);
        List<Item> itemsFromBase = cartDao.getAllByUserAndGood(item.getUser().getId(), goodId);
        if (itemsFromBase.size() == 0) {
            cartDao.add(item);
        } else {
            Item itemFromBase = itemsFromBase.get(0);
            if (itemFromBase.getGoodId().equals(item.getGoodId())) {
                itemFromBase.setQuantity(itemFromBase.getQuantity() + 1);
                cartDao.update(itemFromBase);
            }
        }
        request.setAttribute("goodAdded", true);
        getServletContext().getRequestDispatcher("/goodsList").forward(request, response);
    }
}
