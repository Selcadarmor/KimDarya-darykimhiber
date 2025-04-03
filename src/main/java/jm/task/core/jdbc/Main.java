package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser( "Met", "Martin", (byte) 15);
        userService.saveUser("Kristina", "Chan", (byte) 20);
        userService.saveUser("Mark", "Kovalenko", (byte) 25);
        userService.saveUser("Darya", "Kim", (byte) 30);

        for (User user : userService.getAllUsers()) {
            System.out.println("User with name - " + "name" + " added to the database");
        }
        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
