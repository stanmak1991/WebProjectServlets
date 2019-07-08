package servlet.user;

import dao.CartDao;
import dao.impl.CartDaoHibImpl;
import model.Item;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteFromCart")
public class DeleteFromCartServlet extends HttpServlet {
    private static final CartDao cartDao = new CartDaoHibImpl();
    private static final Logger LOGGER = Logger.getLogger(DeleteFromCartServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Item item = cartDao.getById(Item.class, Long.valueOf(request.getParameter("id")));
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartDao.update(item);
        } else {
            cartDao.delete(Item.class, item.getId());
        }
        request.setAttribute("delete", true);
        response.sendRedirect("cart");
    }
}
