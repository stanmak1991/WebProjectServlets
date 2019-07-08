package servlet.general;

import dao.RoleDao;
import dao.UserDao;
import dao.impl.RoleDaoHibImpl;
import dao.impl.UserDaoHibImpl;
import model.Role;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private static final UserDao userDao = new UserDaoHibImpl();
    private static final RoleDao roleDao = new RoleDaoHibImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Role role = roleDao.getRoleByName("user");
        User user = new User(login, password, email, role);
        LOGGER.debug("User " + login + " start registration");
        if (userDao.check(user)) {
            request.setAttribute("registration", false);
            LOGGER.debug("User " + login + " fail registration");
        } else {
            userDao.add(user);
            request.setAttribute("registration", true);
            LOGGER.debug("User " + login + " successful registration");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("registration.jsp");
    }
}

