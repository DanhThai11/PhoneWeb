package Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import Entity.Account;


@WebServlet("/logindumamay")
public class LoginControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        DAO dao = new DAO();
        Account a = dao.login(user,pass);
        if (a == null) {
            req.setAttribute("mess", "Wrong user or pass");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("acc", a);
            // Check if user is admin
            boolean isAdmin = dao.isAdmin(user, pass);
            session.setAttribute("isAdmin", isAdmin);
            // Redirect to different pages based on user role
            if (isAdmin) {
                resp.sendRedirect("managerall.jsp");
            } else {
                resp.sendRedirect("home");
            }
        }
    }
}