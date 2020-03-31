package DAO;

import User.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

     void addUser(User user) throws SQLException;
     User getUserById(int UserId);
     ArrayList<User> getAllUsers();
     void updateUser(User user);
     void deleteUser(int UserId);

}
