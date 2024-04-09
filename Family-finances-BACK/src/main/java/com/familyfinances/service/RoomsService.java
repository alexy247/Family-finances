package com.familyfinances.service;

import com.familyfinances.model.Finance;
import com.familyfinances.model.Room;
import com.familyfinances.model.User;
import com.familyfinances.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoomsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FinancesService financesService;

    @Autowired
    private RoomRepository roomRepository;

    @Deprecated
    public List<Room> getRoomsForUser(User user) {
        return entityManager.createQuery("SELECT r FROM Room r", Room.class).getResultList();
    }

    public Room createRoomByUser(String name, String password, String userName) {
        User user = usersService.loadUserByUsername(userName);
        Room room = new Room(name, password, user);
        Finance finance = financesService.getDefaultFinances(user, room);
        user.setFinances(finance);
        room.setFinances(finance);
        return roomRepository.save(room);
    }

    public Room getRoomByUser(String name, String password, String userName) {
        Room room = roomRepository.findByRoomName(name);
        System.out.println("room Room " + room);
        if (room == null || !room.getPassword().equals(password)) {
            return null;
        }

        User user = usersService.loadUserByUsername(userName);
        System.out.println("user Room " + room);
        if (!room.getUsers().contains(user)) {
            room.setUsers(user);
            roomRepository.save(room);
        }
        return room;
    }

    public boolean deleteRoom(String name) {
        Room room = roomRepository.findByRoomName(name);
        System.out.println("deleteRoom room is " + room);
        if (room == null) {
            return false;
        }
        roomRepository.delete(room);
        return true;
    }

    public Room getDefaultRoom(User user) {
        return new Room("Ваша первая комната. (" + user.getUsername() + ").", user.getUsername(), user);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public Room getRoomByName(String name) {
        return roomRepository.findByRoomName(name);
    }

    public int getBudget(String name) {
        return this.getRoomByName(name).getBudget();
    }

    public boolean setBudget(String room, String user, int count) {
        if (!usersService.userIsAdmin(usersService.loadUserByUsername(user))) {
            return false;
        }
        this.getRoomByName(room).setBudget(count);
        roomRepository.save(this.getRoomByName(room));
        return true;
    }
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room search(String str) {
        return roomRepository.findByRoomName(str);
    }
}
