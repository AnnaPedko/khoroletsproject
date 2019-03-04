package com.khorolets.dao.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao {

    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/khorolets.project";
    private static final String USER = "Test";
    private static final String PASSWORD = "";

    public ClientDBDao() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS CLIENT(ID INT(10) AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), SURNAME VARCHAR(50), AGE INT(10), EMAIL VARCHAR(50), PHONE VARCHAR(13) );");

        } catch (SQLException ex) {
            System.out.println("Something wrong");
        }
    }

    @Override
    public long saveClient(Client client) {
        long result = -1;
        String insertSQL = "INSERT INTO CLIENT(NAME, SURNAME, AGE, EMAIL, PHONE) VALUES(?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, client.getName());
            st.setString(2, client.getSurname());
            st.setInt(3, client.getAge());
            st.setString(4, client.getEmail());
            st.setString(5, client.getPhone());

            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public void editClient(long id, String newName, int newAge, String newEmail) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("UPDATE CLIENT SET NAME = ?,  AGE = ?, EMAIL = ? WHERE ID = ?;")) {

            st.setString(1, newName);
            st.setInt(2, newAge);
            st.setString(3, newEmail);
            st.setLong(4, id);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("DELETE FROM CLIENT WHERE ID = ?")) {

            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM CLIENT;")) {

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String surname = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");

                clients.add(new Client(id, name, surname, age, phone, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public long getClientId(String phone) {
        long clientId = -1;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM CLIENT WHERE PHONE = '" + phone + "';")) {
            while (resultSet.next()) {
                clientId = resultSet.getLong("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientId;
    }

    @Override
    public boolean hasPhone(String phone) {
        boolean exist = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM CLIENT WHERE PHONE = '" + phone + "';")) {
            exist = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    @Override
    public Client getClientById(long clientId) {
        Client client = new Client();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM CLIENT WHERE ID = " + clientId)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String surname = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");

                client = new Client(id, name, surname, age, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }
}