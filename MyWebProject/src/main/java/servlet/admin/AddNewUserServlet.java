package servlet.admin;

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

@WebServlet(value = "/admin/allUsers/addNewUser")
public class AddNewUserServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddNewUserServlet.class);
    private static final UserDao userDao = new UserDaoHibImpl();
    private static final RoleDao roleDao = new RoleDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin/addNewUser.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Role role = roleDao.getRoleByName(request.getParameter("role"));
        User user = new User(login, password, email, role);
        User admin = (User) request.getSession().getAttribute("user");
        logger.debug("Start add new user \"" + login + "\" by administrator " + admin.getLogin());

        if (userDao.check(user)) {
            request.setAttribute("registration", false);
            logger.debug("Add new user \"" + login + "\" by administrator " + admin.getLogin() + " fail");
            getServletContext().getRequestDispatcher("/admin/addNewUser.jsp").forward(request, response);
        } else {
            userDao.add(user);
            request.setAttribute("registration", true);
            logger.debug("Add new user \"" + login + "\" by administrator " + admin.getLogin() + " successful");
            getServletContext().getRequestDispatcher("/admin/allUsers").forward(request, response);
        }
    }
}