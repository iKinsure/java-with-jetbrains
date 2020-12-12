package org.ikinsure.banking.model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Database {

    private static Database instance;
    private final SQLiteDataSource dataSource;

    private Database() {
        this.dataSource = new SQLiteDataSource();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void createTable(String fileName) {
        String root = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        System.out.println(root);
        dataSource.setUrl("jdbc:sqlite:" + root + fileName);
        try (Connection con = dataSource.getConnection()) {
            con.createStatement().execute("CREATE TABLE IF NOT EXISTS card" +
                    "(id INTEGER, number TEXT, pin TEXT, balance INTEGER DEFAULT 0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Card card) {
        String sql = "INSERT INTO card VALUES(?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, card.hashCode());
            statement.setString(2, card.getNumber());
            statement.setString(3, card.getPin());
            statement.setInt(4, card.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBalance(Card card) {
        String sql = "UPDATE card SET balance = ? WHERE id = ?";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, card.getBalance());
            statement.setInt(2, card.hashCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Card card) {
        String sql = "DELETE FROM card WHERE id = ?";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, card.hashCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Card> selectByNumber(String number) {
        String sql = "SELECT * FROM card WHERE number = ?";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, number);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                String pin = set.getString(3);
                int balance = set.getInt(4);
                return Optional.of(new Card(number, pin, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Set<String> selectAllNumbers() {
        Set<String> result = new HashSet<>();
        try (Connection con = dataSource.getConnection()) {
            ResultSet set = con.prepareStatement("SELECT number FROM card").executeQuery();
            while (set.next()) {
                String number = set.getString(1);
                System.out.println(number);
                result.add(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
