package ru.kornilov.model;

public class MessageDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:8080/JavaECrud";
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
    private static final String UPDATE_MESSAGE_SQL = "UPDATE users SET author = ?, text = ?  WHERE id = ?";




}
