package db.repository;

import db.connection.DatabaseConnection;
import objects.Angajat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EchipaRepo {

    private static EchipaRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO echipe (`id`, `index`) VALUES (?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM echipe WHERE id = ?";
    private static final String UPDATE_STATEMENT = "UPDATE echipe SET name = ? WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM echipe WHERE id=?";

    private EchipaRepo() {
    }

    public static EchipaRepo getInstance() {
        if (instance == null) {
            instance = new EchipaRepo();
        }

        return instance;
    }

    public void saveEchipa(int index) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setInt(1, index);
            statement.setInt(2, index);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Echipa was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new Echipa: " + e.getMessage());
        }
    }

    public Angajat findAngajat(int id) {
        Angajat user = new Angajat();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return user;
                }

                System.out.println("User was found!");
                user.setNume(result.getString("nume"));
                user.setPrenume(result.getString("prenume"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return user;
    }

    public Angajat updateAngajat(Angajat angajat) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getPrenume());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User was updated successfully!");
                return angajat;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
            return new Angajat();
        }

        System.out.println("Something went wrong when trying to update user: User was not found!");
        return new Angajat();
    }

    public boolean deleteAngajat(int id) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete user: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete user: User was not found!");
        return false;
    }
}
