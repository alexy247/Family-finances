package com.familyfinances.service;

import com.familyfinances.model.Category;
import com.familyfinances.model.Finance;
import com.familyfinances.model.Room;
import com.familyfinances.model.Type;
import com.familyfinances.model.User;
import com.familyfinances.repository.CategoriesRepository;
import com.familyfinances.repository.FinancesRepository;
import com.familyfinances.repository.TypeRepository;
import com.familyfinances.utils.Calculator;
import com.familyfinances.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class FinancesService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FinancesRepository financesRepository;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TypeService typeService;

    public Finance getDefaultFinances(User user, Room room) {
        System.out.println("category is " + categoryService.getDefaultCategory());
        return new Finance(this.getDefaultType(), room, categoryService.getDefaultCategory(), user, 100.0F, "Ваша первая трата", DateUtils.getDateNow(), 1);
    }

    public Type getDefaultType() {
        return typeRepository.findByTypeName("Расход");
    }

    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    public Set<Category> getCategories(Room room) {
        Set<Category> first = categoryService.getDefaultCategories();
        first.addAll(financesRepository.findCategoriesForRoom(room));
        return first;
    }

    public Set<Finance> getFinancesForRoom(String name) {
        return roomsService.getRoomByName(name).getFinances();
    }
    public List<Finance> getFinancesByTypeForRoom(String name, String type) {
        return financesRepository.findFinancesByTypeForRoom(roomsService.getRoomByName(name), typeService.getTypeByName(type));
    }

    public Set<Finance> createFinance(
            String roomName,
            String userName,
            String categoryName,
            String typeName,
//            float amount,
            String amount,
            String date,
            String comment
    ) {
        User user = usersService.loadUserByUsername(userName);
        Type type = typeService.getTypeByName(typeName);

        System.out.println("createFinance  userIsChield " + usersService.userIsChield(user) + type.isIncome());
        if (usersService.userIsChield(user) && type.isIncome()) {
            return Collections.emptySet();
        }

        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = this.createCategory(categoryName);
        }

        float f = Float.parseFloat(Objects.requireNonNull(Calculator.calc(amount)));
        Finance finance = new Finance(
                typeService.getTypeByName(typeName),
                roomsService.getRoomByName(roomName),
                category,
                user,
                f,
                comment,
                date);
        System.out.println("amounth is " + f);
        this.saveFinance(finance);
        return this.getFinancesForRoom(roomName);
    };


    public Set<String> getDataGroupByRoomByType(String name, String type) throws ParseException {
        List<Finance> data = financesRepository.findFinancesByTypeForRoom(roomsService.getRoomByName(name), typeService.getTypeByName(type));
        Set<String> dates = new HashSet<>();

        for (Finance finance : data) {
            dates.add(DateUtils.getYearMonthString(finance.getDate()));
        }

        return dates;
    }

    @Deprecated
    public Set<Finance> getDataGroupByRoomByTypeByDate(String name, String type, String date) throws ParseException {
        List<Finance> data = financesRepository.findFinancesByTypeForRoom(roomsService.getRoomByName(name), typeService.getTypeByName(type));
        Set<Finance> finances = new HashSet<>();

        for (Finance finance : data) {
            if (DateUtils.getYearMonthString(finance.getDate()).equals(date)) {
                finances.add(finance);
            }
        }

        return finances;
    }

    public Map<String, List<Object>> getDataGroupByRoomByTypeByDateV2(String name, String type, String date) throws ParseException {
        Room room = roomsService.getRoomByName(name);
        Map<String, List<Object>> result = new HashMap<>();
        System.out.println("Date is " + date);
        String convertDate = DateUtils.getYearMonthStringReverce(date);
        System.out.println("convertDate is " + convertDate);
        System.out.println("Получаем следующие данные " + room.getId() + " " + convertDate.substring(0,4) + " " + convertDate.substring(5,7));
        result.put(convertDate, financesRepository.calculateAnalysisConsumptionByCategory(room.getId(), convertDate.substring(0,4), convertDate.substring(5,7), typeService.getTypeByName(type).getId()));

        return result;
    }

    public Map<String, List<Object>> getDataAnalysisConsumption(String name) throws ParseException {
        Room room = roomsService.getRoomByName(name);
        List<Finance> data = financesRepository.findFinancesForRoom(room);
        Set<String> dates = new HashSet<>();

        Map<String, List<Object>> result = new HashMap<>();

        for (Finance finance : data) {
            dates.add(DateUtils.getYearMonth(finance.getDate()));
        }

        for (String date : dates) {
            result.put(date, financesRepository.calculateAnalysisConsumption(room.getId(), date.substring(3,7), date.substring(0,2)));
        }
        return result;
    }

    public Category createCategory(String categoryName) {
        Category category = new Category(categoryName, false);
        return categoriesRepository.save(category);
    }

    public Finance saveFinance(Finance finance) {
        return financesRepository.save(finance);
    }
}
