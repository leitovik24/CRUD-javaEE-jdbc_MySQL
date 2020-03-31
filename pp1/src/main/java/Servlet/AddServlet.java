package Servlet;

import DAO.UserJdbcDAO;
import Service.UserService;
import User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, email);
        try {
            userService.addUser(user);
            resp.setStatus(200);
            String path = req.getContextPath() + "/list";
            resp.sendRedirect(path);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(404);
        }


    }
}
