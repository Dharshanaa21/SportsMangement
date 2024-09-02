package sports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Result {
    private Connection connection;

    public Result() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void addResult() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Result ID: ");
        int result_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if result ID already exists
        String checkQuery = "SELECT COUNT(*) FROM Result WHERE result_id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, result_id);
            ResultSet resultSet = checkStmt.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                System.out.println("Result with ID " + result_id + " already exists.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error checking result ID: " + e.getMessage());
            return;
        }

        System.out.println("Enter Event ID: ");
        int event_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Athlete ID: ");
        int athlete_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if athlete ID exists
        String checkAthleteQuery = "SELECT COUNT(*) FROM Athlete WHERE athlete_id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkAthleteQuery)) {
            checkStmt.setInt(1, athlete_id);
            ResultSet resultSet = checkStmt.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                System.out.println("Athlete with ID " + athlete_id + " does not exist.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error checking athlete ID: " + e.getMessage());
            return;
        }

        System.out.println("Enter Result: ");
        String result = scanner.nextLine();
        System.out.println("Enter Result Date (YYYY-MM-DD): ");
        String result_date = scanner.nextLine();

        String query = "INSERT INTO Result (result_id, event_id, athlete_id, result, result_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, result_id);
            statement.setInt(2, event_id);
            statement.setInt(3, athlete_id);
            statement.setString(4, result);
            statement.setString(5, result_date);
            statement.executeUpdate();
            System.out.println("Result added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding result: " + e.getMessage());
        }
    }


    public void viewResult() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Result ID: ");
        int result_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "SELECT * FROM Result WHERE result_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, result_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Result ID: " + resultSet.getInt("result_id"));
                System.out.println("Event ID: " + resultSet.getInt("event_id"));
                System.out.println("Athlete ID: " + resultSet.getInt("athlete_id"));
                System.out.println("Result: " + resultSet.getString("result"));
                System.out.println("Result Date: " + resultSet.getDate("result_date"));
            } else {
                System.out.println("No result found with ID: " + result_id);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing result: " + e.getMessage());
        }
    }

    public void updateResult() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Result ID to update: ");
        int result_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new Event ID: ");
        int event_id = scanner.nextInt();
        System.out.println("Enter new Athlete ID: ");
        int athlete_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new Result: ");
        String result = scanner.nextLine();
        System.out.println("Enter new Result Date (YYYY-MM-DD): ");
        String result_date = scanner.nextLine();

        String query = "UPDATE Result SET event_id = ?, athlete_id = ?, result = ?, result_date = ? WHERE result_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, event_id);
            statement.setInt(2, athlete_id);
            statement.setString(3, result);
            statement.setString(4, result_date);
            statement.setInt(5, result_id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Result updated successfully.");
            } else {
                System.out.println("No result found with ID: " + result_id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating result: " + e.getMessage());
        }
    }

    public void removeResult() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Result ID to remove: ");
        int result_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM Result WHERE result_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, result_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Result removed successfully.");
            } else {
                System.out.println("No result found with ID: " + result_id);
            }
        } catch (SQLException e) {
            System.out.println("Error removing result: " + e.getMessage());
        }
    }
}


