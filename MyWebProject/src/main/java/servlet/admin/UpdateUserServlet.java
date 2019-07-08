package servlet.admin;

import dao.RoleDao;
import dao.UserDao;
import dao.impl.RoleDaoHibImpl;
import dao.impl.UserDaoHibImpl;
import model.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/allUsers/updateUser")
public class UpdateUserServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UpdateUserServlet.class);
    private static final UserDao userDao = new UserDaoHibImpl();
    private static final RoleDao roleDao = new RoleDaoHibImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("id", id);
        request.getRequestDispatcher("/admin/updateUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Role role = roleDao.getRoleByName(request.getParameter("role"));
        logger.debug("UserId " + id + " start update personal information");
        userDao.update(id, password, email, role);
        request.setAttribute("update", true);
        logger.debug("User " + request.getParameter("login") + " update personal information successful");
        getServletContext().getRequestDispatcher("/admin/allUsers").include(request, response);
    }
}
