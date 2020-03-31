package DAO;
import User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO  {

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;
    private final String SQL_CREATE_USER = "INSERT INTO users (name, email) VALUES (?, ?)";
    private final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private final String SQL_UPDATE_USER = "UPDATE users SET name=?, email=?  WHERE id=?";
    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";



    public void addUser(User user)  throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet result;
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL_GET_ALL_USERS);
            result = statement.getResultSet();
            while (result.next()){
                Long id = result.getLong("id");
                String name = result.getString("name");
                String email = result.getString("email");
                User user = new User(id, name, email) ;
                list.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }
    public void deleteUser(long id)  {

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)){

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public User getUserById(Long id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        String name = result.getString("name");
        String email = result.getString("email");
        User user = new User(id, name, email);
        result.close();
        preparedStatement.close();
        return user;

    }

    public void editUser(long id, String newName, String newEmail) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
        statement.setString(1, newName);
        statement.setString(2, newEmail);
        statement.setLong(3, id);
        statement.executeUpdate();
        statement.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }




}
