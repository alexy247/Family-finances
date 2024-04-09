package com.familyfinances.service;

import com.familyfinances.model.Finance;
import com.familyfinances.model.Role;
import com.familyfinances.model.Room;
import com.familyfinances.model.User;
import com.familyfinances.repository.RoleRepository;
import com.familyfinances.repository.UserRepository;
import com.familyfinances.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private FinancesService financesService;

    @Autowired
    private CategoryService categoryService;

    public List<User> getUserList() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUser() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getSingleResult();
    }

    public User getUserById(int id) {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getSingleResult();
    }

    public boolean checkUserLogin(String name, String password) {
        System.out.println("checkUserLogin");
        UserDetails user = this.loadUserByUsername(name);
        if (user == null) {
            return false;
        }

        System.out.println("checkUserLogin is " + user.getPassword().equals(password));
        return user.getPassword().equals(password);
    }

    public boolean createUser(String name, String password, String date) throws ParseException {
        System.out.println("createUser");
        if (this.loadUserByUsername(name) != null) {
            return false;
        } else {
            // YYYY-MM-DD

            Role role;
            if (DateUtils.isChild(date)) {
                System.out.println("isChild");
                role = roleRepository.findByRoleName("ROLE_USER");
            } else {
                System.out.println("not isChild");
                role = roleRepository.findByRoleName("ROLE_MODERATOR");
            }

            User user = new User(role, name, date, password, "tokens");

            System.out.println("Создали юзера");
            Room room = roomsService.getDefaultRoom(user);
            System.out.println("Создали комнату");
            Finance finance = financesService.getDefaultFinances(user, room);
            user.setRooms(room);

            user.setFinances(finance);
            room.setFinances(finance);

            userRepository.save(user);
            System.out.println("Сохранили юзера");
            roomsService.saveRoom(room);
            System.out.println("Сохранили комнату");
            return true;
        }
    }

    public boolean userIsAdmin(User user) {
        System.out.println("userIsAdmin " + user.getRole().getRole().equals("ROLE_ADMIN"));
        return user.getRole().getRole().equals("ROLE_ADMIN");
    }

    public boolean userIsChield(User user) {
        return user.getRole().getRole().equals("ROLE_USER");
    }

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUsername(name);
    }
}
