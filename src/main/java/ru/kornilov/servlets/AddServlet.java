package ru.kornilov.servlets;

import ru.kornilov.entities.Message;
import ru.kornilov.model.MessageDAO;
import sun.security.util.MessageDigestSpi2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =req.getRequestDispatcher("views/add.ftlh");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("author");
        String text = req.getParameter("text");
        Message message = new Message(author, text);

        MessageDAO.getInstance().insertMessage(message);

        resp.sendRedirect("list");

    }
}
