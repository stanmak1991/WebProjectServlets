package servlet.admin;

import dao.UserDao;
import dao.impl.UserDaoHibImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/allUsers")
public class AllUsersServlet extends HttpServlet {
    private static final UserDao userDao = new UserDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUsers = userDao.getAll(User.class);
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("user", request.getAttribute("user"));
        request.getRequestDispatcher("/admin/allUsers.jsp").forward(request, response);
    }
}
