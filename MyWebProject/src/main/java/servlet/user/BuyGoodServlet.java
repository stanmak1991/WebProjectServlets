package servlet.user;

import dao.CartDao;
import dao.CodeDao;
import dao.impl.CartDaoHibImpl;
import dao.impl.CodeDaoHibImpl;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import service.MailService;
import utils.RandomHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/buy")
public class BuyGoodServlet extends HttpServlet {
    private static final MailService MAIL_SERVICE = new MailService();
    private static final CodeDao codeDao = new CodeDaoHibImpl();
    private static final CartDao cartDao = new CartDaoHibImpl();
    private static final Logger LOGGER = Logger.getLogger(BuyGoodServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Long codeValue = RandomHelper.getRandomCode();
        MAIL_SERVICE.sendMail(user.getEmail(), codeValue);
        Code code = new Code(codeValue, user);
        LOGGER.debug("start add confirmation code into database");
        codeDao.add(code);
        request.getRequestDispatcher("user/buyConfirmation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long codeValue = Long.valueOf(request.getParameter("code"));
        User user = (User) request.getSession().getAttribute("user");
        Code code = new Code(codeValue, user);
        boolean isPayed = false;
        LOGGER.debug("start check confirm code from user with database code");
        if (codeDao.check(code)) {
            LOGGER.debug("check confirm code complete");
            cartDao.confirmUserPayment(user.getId());
            isPayed = true;
            codeDao.delete(Code.class, code.getId());
            request.setAttribute("isPayed", isPayed);
            request.getRequestDispatcher("user/payment.jsp").forward(request, response);
        } else {
            LOGGER.debug("check confirm code complete");
            request.setAttribute("isPayed", isPayed);
            request.getRequestDispatcher("user/payment.jsp").forward(request, response);
        }
    }
}
