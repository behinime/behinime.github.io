package com.codecool.servlet;

import com.codecool.model.Greeting;
import com.codecool.model.User;
import com.codecool.model.UserRole;
import com.codecool.service.GreetingService;
import com.codecool.service.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/loggedin")
public class LoginServlet extends HttpServlet {

    UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        //needs Session handling.

    }
}
