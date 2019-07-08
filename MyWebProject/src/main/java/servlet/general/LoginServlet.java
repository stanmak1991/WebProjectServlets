package servlet.general;

import dao.UserDao;
import dao.impl.UserDaoHibImpl;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final UserDao USER_DAO = new UserDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String passwordFromForm = request.getParameter("password");
        LOGGER.debug("User " + login + " start login");
        User user = USER_DAO.getByLogin(login);

        if (user != null) {
            boolean checkPass = checkPassword(user, passwordFromForm);

            if (checkPass) {
                request.getSession().setAttribute("user", user);
                request.setAttribute("user", user);
                String checkRole = checkRole(user);
                request.getRequestDispatcher(checkRole).forward(request, response);
            } else {
                request.setAttribute("login", false);
                LOGGER.debug("User " + login + " - fail login");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("login", false);
            LOGGER.debug("User " + login + " - fail login");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private boolean checkPassword(User user, String passwordFromForm) {
        String salt = user.getSalt();
        String password = HashUtil.getSHA512SecurePassword(passwordFromForm, salt);
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private String checkRole(User user) {
        if (user.getRole().getRole().equals("ADMIN")) {
            LOGGER.debug("User " + user.getLogin() + " login by administrator successful");
            return "/admin";
        } else {
            LOGGER.debug("User " + user.getLogin() + " - login by user successful");
            return "/goodsList";
        }
    }
}

