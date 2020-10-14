package ru.kornilov.model;

import ru.kornilov.entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/JavaECrud";
    private String name = "root";
    private String password = "1234";

    private static MessageDAO instance;

    public static synchronized MessageDAO getInstance() {
        if (instance == null) instance = new MessageDAO();
        return instance;
    }

    private static final String INSERT_MESSAGE_SQL = "INSERT INTO messages" + " (author, text) VALUES" + " (?, ?);";
    private static final String SELECT_ALL_MESSAGES_SQL = "SELECT * FROM messages";
    private static final String DELETE_MESSAGES_SQL = "DELETE FROM messages WHERE id = ?";
    private static final String UPDATE_MESSAGE_SQL = "UPDATE messages SET author = ?, text = ?  WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, name, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insertMessage(Message message) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL)) {
            preparedStatement.setString(1, message.getAuthor());
            preparedStatement.setString(2, message.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> selectAllMessage() throws SQLException {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MESSAGES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String author = rs.getString("author");
                String text = rs.getString("text");
                messages.add(new Message(id, author, text));
            }
        }
        return messages;
    }
}
