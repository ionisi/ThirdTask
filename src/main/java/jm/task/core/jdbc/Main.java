package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dave", "One", (byte) 22);
        System.out.println("User с именем — Dave добавлен в базу данных");
        userService.saveUser("Dana", "Two", (byte) 22);
        System.out.println("User с именем — Dana добавлен в базу данных");
        userService.saveUser("Daren", "Three", (byte) 22);
        System.out.println("User с именем — Daren добавлен в базу данных");
        userService.saveUser("Damien", "Four", (byte) 22);
        System.out.println("User с именем — Damien добавлен в базу данных");
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.getAllUsers().forEach(System.out::println);
    }
    }
