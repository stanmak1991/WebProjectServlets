package servlet.general;

import dao.GoodDao;
import dao.RoleDao;
import dao.UserDao;
import dao.impl.GoodDaoHibImpl;
import dao.impl.RoleDaoHibImpl;
import dao.impl.UserDaoHibImpl;
import model.Good;
import model.Role;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        UserDao userDao = new UserDaoHibImpl();
        GoodDao goodDao = new GoodDaoHibImpl();
        RoleDao roleDao = new RoleDaoHibImpl();

        Role admin = new Role("ADMIN");
        Role user = new Role("USER");

        roleDao.add(admin);
        roleDao.add(user);

        User administrator = new User("admin",
                "root",
                "MyWebProjectMA@gmail.com",
                admin);
        User stasmak = new User("stasmak",
                "123",
                "stanmak1991@gmail.com",
                user);
        userDao.add(administrator);
        userDao.add(stasmak);

        Good BMW = new Good("BMW", "X5", 1000000);
        Good mercedes = new Good("Mercedes", "600", 100000);
        goodDao.add(BMW);
        goodDao.add(mercedes);
    }
}
