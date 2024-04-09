package com.familyfinances.controller;

import com.familyfinances.model.Category;
import com.familyfinances.model.Room;
import com.familyfinances.model.User;
import com.familyfinances.service.RoomsService;
import com.familyfinances.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomsController {
    @Autowired
    RoomsService roomsService;
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/room")
    @ResponseBody
    private List<Room> getRooms(@RequestParam("name") String name) {
        return roomsService.getRoomsForUser(usersService.loadUserByUsername(name));
    }

    @RequestMapping(value = "/room/create")
    @ResponseBody
    private Room createRoom(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("userName") String user) {
        System.out.println("USER " + user);
        return roomsService.createRoomByUser(name, password, user);
    }

    @RequestMapping(value = "/room/enter")
    @ResponseBody
    private Room enterRoom(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("userName") String userName) {
        System.out.println("enter Room " + name + " " + password + " " + userName);
        return roomsService.getRoomByUser(name, password, userName);
    }

    @RequestMapping(value = "/room/delete")
    @ResponseBody
    private boolean deleteRoom(@RequestParam("name") String name) {
        System.out.println("deleteRoom name is " + name);
        return roomsService.deleteRoom(name);
    }

    @RequestMapping(value = "/room/search")
    @ResponseBody
    private Room search(@RequestParam("str") String str) {
        return roomsService.search(str);
    }

    @RequestMapping(value="/room/budget")
    @ResponseBody
    private int getCategoriesForRoom(@RequestParam("roomName") String roomName) {
        System.out.println("Отправляем допустимый бюджет для этой комнаты:" + roomName);
        return roomsService.getBudget(roomName);
    }


    @RequestMapping(value="/room/budget/update")
    @ResponseBody
    private boolean getCategoriesForRoom(@RequestParam("roomName") String roomName, @RequestParam("userName") String userName, @RequestParam("count") int count) {
        System.out.println("Отправляем допустимый бюджет для этой комнаты:" + roomName);
        return roomsService.setBudget(roomName, userName, count);
    }

    @RequestMapping(value = "/rooms")
    @ResponseBody
    private List<Room> getRooms() {
        return roomsService.getAllRooms();
    }
}
