/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagement;

/**
 *
 * @author USER
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/eventmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveEvent(Event event) throws SQLException {
        String query = "INSERT INTO events (event_name, event_date, location) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, event.getEventName());
            stmt.setDate(2, Date.valueOf(event.getEventDate()));
            stmt.setString(3, event.getLocation());
            stmt.executeUpdate();
        }
    }

    public static void updateEvent(Event event) throws SQLException {
        String query = "UPDATE events SET event_name = ?, event_date = ?, location = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, event.getEventName());
            stmt.setDate(2, Date.valueOf(event.getEventDate()));
            stmt.setString(3, event.getLocation());
            stmt.setInt(4, event.getId());
            stmt.executeUpdate();
        }
    }

    public static void deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM events WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("event_name"),
                        rs.getDate("event_date").toLocalDate(),
                        rs.getString("location")
                );
                events.add(event);
            }
        }
        return events;
    }
}
