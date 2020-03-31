package Servlet;

import DAO.UserJdbcDAO;
import Service.UserService;
import User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("id", id);
        try {
            User user = userService.getUserById(id);
            if( user != null){
                req.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
            }
            else{
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        long id = (Long)httpSession.getAttribute("id");
        httpSession.removeAttribute("id");
        String newName = req.getParameter("name");
        String newEmail = req.getParameter("email");

        try {
            userService.editUser(id, newName, newEmail);
            String path = req.getContextPath() + "/list";
            resp.sendRedirect(path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
