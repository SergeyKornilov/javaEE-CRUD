package ru.kornilov.servlets;

import ru.kornilov.entities.Message;
import ru.kornilov.model.MessageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messages = null;

        try {
            messages = MessageDAO.getInstance().selectAllMessage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("messages", messages);
        RequestDispatcher requestDispatcher =req.getRequestDispatcher("views/list.ftlh");
        requestDispatcher.forward(req, resp);
    }
}