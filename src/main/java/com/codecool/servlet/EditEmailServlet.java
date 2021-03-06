package com.codecool.servlet;

import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.user.User;
import com.codecool.service.dao.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/editedMail")
public class EditEmailServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);

            User user =  getCurrentUser(request);
            String newEmail = request.getParameter("email");
            user.setEmail(newEmail);
            userService.updateEmail(user.getId(), newEmail);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return (User) session.getAttribute("user");
    }
}
