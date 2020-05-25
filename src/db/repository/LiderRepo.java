package db.repository;

import db.connection.DatabaseConnection;
import objects.Angajat;
import objects.Lider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LiderRepo {

    private static LiderRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO lideri (nume, prenume, experienta, salariu) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM lideri";
    private static final String UPDATE_STATEMENT = "UPDATE lideri SET name = ? WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM lideri WHERE id=?";

    private LiderRepo() {
    }

    public static LiderRepo getInstance() {
        if (instance == null) {
            instance = new LiderRepo();
        }

        return instance;
    }

    public Lider saveLider(Lider prop) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, prop.getNume());
            statement.setString(2, prop.getPrenume());
            statement.setInt(3, prop.getExperienta());
            statement.setFloat(4, prop.getSalariu());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Lider was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new Lider: " + e.getMessage());
            return new Lider();
        }
        return prop;
    }

    public List<Lider> getLideri() {
        List<Lider> lista = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return lista;
                }

                Lider lider = new Lider();
                lider.setNume(result.getString("nume"));
                lider.setPrenume(result.getString("prenume"));
                lider.setExperienta(result.getInt("experienta"));
                lider.setSalariu(result.getFloat("salariu"));
                lider.setProiect(new ArrayList<>());
                lista.add(lider);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return lista;
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
