package com.codecool.servlet;

import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.UserList;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.service.AttendanceService;
import com.codecool.service.IDGeneratorService;
import com.codecool.service.SolutionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> students = new ArrayList<>();
        for (User user : UserList.getInstance().getUsers()) {
            if (user instanceof Student) {
                students.add(user);
            }
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            AttendanceService attendanceService = new AttendanceService(attendanceDao);
            IDGeneratorService idGeneratorService = new IDGeneratorService();
            String generatedID = idGeneratorService.generateID();

            String date = request.getParameter("datefield");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
            String[] attending = request.getParameterValues("attending");

            List<User> students = new ArrayList<>();
            for (String name : attending) {
                Student student = findStudentByName(name);
                if (student != null) {
                    student.setAttendance(localDate, true);
                    students.add(student);
                    attendanceService.addAttendance(generatedID, student.getId(), localDate, student.getAttendance().get(localDate));
                }
            }
            request.setAttribute("students", students);
            request.getRequestDispatcher("attendance.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private Student findStudentByName(String name) {
        for (User user: UserList.getInstance().getUsers()) {
            if (user.getName().equals(name)) {
                return (Student) user;
            }
        }
        return null;
    }
}