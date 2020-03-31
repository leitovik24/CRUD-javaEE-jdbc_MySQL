import DAO.UserJdbcDAO;
import Service.UserService;
import User.User;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        

        List<User> users = userService.getAllUsers();
        for (User user :users
             ) {
            System.out.println(user.getId());
            System.out.println(user.getEmail());
            System.out.println(user.getName());



        }


}
}
