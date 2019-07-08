package servlet.admin;

import dao.CartDao;
import dao.UserDao;
import dao.impl.CartDaoHibImpl;
import dao.impl.UserDaoHibImpl;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/allUsers/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DeleteUserServlet.class);
    private static final UserDao userDao = new UserDaoHibImpl();
    private static final CartDao cartDao = new CartDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        cartDao.deleteByUser(id);
        userDao.delete(User.class, id);
        logger.debug("User " + id + " delete successful");
        request.setAttribute("delete", true);
        getServletContext().getRequestDispatcher("/admin/allUsers").forward(request, response);
    }
}
