package com.familyfinances.controller;

import com.familyfinances.model.User;
import com.familyfinances.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * Этот код использует новую в Spring 4 @RestController аннотацию,
 * помечая класс как контроллер, где каждый метод возвращает объект
 * вместо представления(view). Это сокращение для @Controller и
 * @ResponseBody, вместе взятых.
 */

@RestController
public class LoginController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value="/login")
    @ResponseBody
    private boolean login(@RequestParam("name") String name, @RequestParam("password") String password) {
        System.out.println("login " + name + " " + password);
        return usersService.checkUserLogin(name, password);
    }

    @RequestMapping(value="/registration")
    @ResponseBody
    private boolean registration(@RequestParam("name") String name, @RequestParam("password") String password,  @RequestParam("date") String date) throws ParseException {
        System.out.println("registration " + name + " " + password);
        return usersService.createUser(name, password, date);
    }

    @RequestMapping("/users")
    private List<User> users() {
        return usersService.getUserList();
    }


    @RequestMapping(value="/user", method = RequestMethod.GET)
    private User user() {
        return usersService.getUser();
    }
}
