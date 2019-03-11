package server.auth;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {
    public Map<String, String> users = new HashMap<>();

    public AuthServiceImpl() {
        //users.put("ivan", "123");
        //users.put("petr", "345");
        //users.put("julia", "789");

        try{
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:chat.db")) {
                Statement statement = connection.createStatement();

                for(Client client : readAllClients(statement)) {
                    System.out.println(client);
                    users.put(client.getLogin(), client.getPassword());
                }

            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private static Collection<Client> readAllClients(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");

        Map<Integer, Client> clientById = new HashMap<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            if (clientById.get(id) == null) {
                clientById.put(id, createClient(resultSet, id));
            }
        }
        return clientById.values();
    }

    private static Client createClient(ResultSet resultSet, int id) throws SQLException {
        String login = resultSet.getString(2);
        String password = resultSet.getString(3);

        Client client = new Client();
        client.setId(id);
        client.setLogin(login);
        client.setPassword(password);
        return client;
    }

    @Override
    public boolean authUser(String username, String password) {
        String pwd = users.get(username);
        return pwd != null && pwd.equals(password);
    }
}