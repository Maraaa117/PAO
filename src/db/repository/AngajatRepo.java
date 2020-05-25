package db.repository;

import db.connection.DatabaseConnection;
import objects.Angajat;
import objects.Designer;
import objects.Programator;
import objects.Vanzator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AngajatRepo {

    private static AngajatRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO angajati (nume, prenume, experienta, salariu, specializare, comision, caracteristica) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM angajati WHERE id = ?";
    private static final String UPDATE_STATEMENT = "UPDATE angajati SET name = ? WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM angajati WHERE id=?";

    private AngajatRepo() {
    }

    public static AngajatRepo getInstance() {
        if (instance == null) {
            instance = new AngajatRepo();
        }

        return instance;
    }

    public Angajat saveAngajat(Angajat angajat) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getPrenume());
            statement.setInt(3, angajat.getExperienta());
            statement.setFloat(4, angajat.getSalariu());

            if (angajat instanceof Programator) {
                statement.setString(5, "Programator");
                statement.setInt(6, 0);
                statement.setString(7, ((Programator) angajat).getSpecializare());
            } else if (angajat instanceof Designer) {
                statement.setString(5, "Designer");
                statement.setInt(6, 0);
                statement.setString(7, ((Designer) angajat).getSpecializare());
            } else {
                statement.setString(5, "Vanzator");
                statement.setInt(6, ((Vanzator) angajat).getComision());
                statement.setString(7, "");
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Angajat was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new Angajat: " + e.getMessage());
            return new Angajat();
        }
        return angajat;
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
