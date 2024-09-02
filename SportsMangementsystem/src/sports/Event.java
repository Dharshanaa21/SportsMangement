package sports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Event {
    private Connection connection;

    public Event() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void addEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Event ID: ");
        int event_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if event ID already exists
        String checkQuery = "SELECT COUNT(*) FROM Event WHERE event_id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, event_id);
            ResultSet resultSet = checkStmt.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                System.out.println("Event with ID " + event_id + " already exists.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error checking event ID: " + e.getMessage());
            return;
        }

        System.out.println("Enter Event Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Event Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.println("Enter Venue: ");
        String venue = scanner.nextLine();
        System.out.println("Enter Type: ");
        String type = scanner.nextLine();

        String query = "INSERT INTO Event (event_id, name, date, venue, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, event_id);
            statement.setString(2, name);
            statement.setString(3, date);
            statement.setString(4, venue);
            statement.setString(5, type);
            statement.executeUpdate();
            System.out.println("Event added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding event: " + e.getMessage());
        }
    }


    public void viewEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Event ID: ");
        int event_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "SELECT * FROM Event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, event_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Event ID: " + resultSet.getInt("event_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Date: " + resultSet.getDate("date"));
                System.out.println("Venue: " + resultSet.getString("venue"));
                System.out.println("Type: " + resultSet.getString("type"));
            } else {
                System.out.println("No event found with ID: " + event_id);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing event: " + e.getMessage());
        }
    }

    public void updateEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Event ID to update: ");
        int event_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new Event Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter new Event Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.println("Enter new Venue: ");
        String venue = scanner.nextLine();
        System.out.println("Enter new Type: ");
        String type = scanner.nextLine();

        String query = "UPDATE Event SET name = ?, date = ?, venue = ?, type = ? WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, venue);
            statement.setString(4, type);
            statement.setInt(5, event_id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Event updated successfully.");
            } else {
                System.out.println("No event found with ID: " + event_id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating event: " + e.getMessage());
        }
    }

    public void removeEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Event ID to remove: ");
        int event_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM Event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, event_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Event removed successfully.");
            } else {
                System.out.println("No event found with ID: " + event_id);
            }
        } catch (SQLException e) {
            System.out.println("Error removing event: " + e.getMessage());
        }
    }
}
