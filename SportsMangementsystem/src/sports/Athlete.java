package sports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Athlete {
    private Connection connection;

    public Athlete() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void addAthlete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Athlete ID: ");
        int athlete_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Country: ");
        String country = scanner.nextLine();
        System.out.println("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Sport: ");
        String sport = scanner.nextLine();

        String query = "INSERT INTO Athlete (athlete_id, name, country, age, sport) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, athlete_id);
            statement.setString(2, name);
            statement.setString(3, country);
            statement.setInt(4, age);
            statement.setString(5, sport);
            statement.executeUpdate();
            System.out.println("Athlete added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding athlete: " + e.getMessage());
        }
    }

    public void viewAthlete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Athlete ID: ");
        int athlete_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "SELECT * FROM Athlete WHERE athlete_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, athlete_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Athlete ID: " + resultSet.getInt("athlete_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Country: " + resultSet.getString("country"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Sport: " + resultSet.getString("sport"));
            } else {
                System.out.println("No athlete found with ID: " + athlete_id);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing athlete: " + e.getMessage());
        }
    }

    public void updateAthlete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Athlete ID to update: ");
        int athlete_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter new Country: ");
        String country = scanner.nextLine();
        System.out.println("Enter new Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new Sport: ");
        String sport = scanner.nextLine();

        String query = "UPDATE Athlete SET name = ?, country = ?, age = ?, sport = ? WHERE athlete_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, country);
            statement.setInt(3, age);
            statement.setString(4, sport);
            statement.setInt(5, athlete_id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Athlete updated successfully.");
            } else {
                System.out.println("No athlete found with ID: " + athlete_id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating athlete: " + e.getMessage());
        }
    }

    public void removeAthlete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Athlete ID to remove: ");
        int athlete_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM Athlete WHERE athlete_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, athlete_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Athlete removed successfully.");
            } else {
                System.out.println("No athlete found with ID: " + athlete_id);
            }
        } catch (SQLException e) {
            System.out.println("Error removing athlete: " + e.getMessage());
        }
    }
}
