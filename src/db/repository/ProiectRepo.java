package db.repository;

import db.connection.DatabaseConnection;
import objects.Angajat;
import objects.Lider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProiectRepo {

    private static ProiectRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO proiecte (`index`, `i`) VALUES (?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM proiecte WHERE id = ?";
    private static final String UPDATE_STATEMENT = "UPDATE proiecte SET name = ? WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM proiecte WHERE id=?";

    private ProiectRepo() {
    }

    public static ProiectRepo getInstance() {
        if (instance == null) {
            instance = new ProiectRepo();
        }

        return instance;
    }

    public void saveProiect(int index, int i) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setInt(1, index);
            statement.setInt(2, i);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Proiect was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new Proiect: " + e.getMessage());
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
