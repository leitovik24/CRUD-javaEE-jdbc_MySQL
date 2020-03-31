package Service;
import DAO.UserJdbcDAO;
import User.User;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserJdbcDAO userJdbcDAO = new UserJdbcDAO(getMySqlConnection());

    private static Connection getMySqlConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("users?").          //db name
                    append("user=root&").          //login
                    append("password=root").    //password
                    append("&serverTimezone=UTC");   //setup server time")
            System.out.println("URL: " + url + "\n");
            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }

    }
    public void addUser(User user) throws SQLException {
        userJdbcDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userJdbcDAO.getAllUsers();
    }

    public User getUserById(Long id) throws SQLException{
        User user = userJdbcDAO.getUserById(id);
        return user;
    }
    public void editUser(long id, String newName, String newEmail) throws SQLException {
        userJdbcDAO.editUser(id, newName, newEmail);
    }
    public void deleteUser(long id) throws SQLException {
        userJdbcDAO.deleteUser(id);
    }

}
