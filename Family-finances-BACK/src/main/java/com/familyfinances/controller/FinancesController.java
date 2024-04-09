package com.familyfinances.controller;

import com.familyfinances.model.Category;
import com.familyfinances.model.Finance;
import com.familyfinances.model.Type;
import com.familyfinances.service.CategoryService;
import com.familyfinances.service.FinancesService;
import com.familyfinances.service.RoomsService;
import com.familyfinances.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class FinancesController {
    @Autowired
    FinancesService financesService;

    @Autowired
    RoomsService roomsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UsersService usersService;

    @RequestMapping(value="/finance/all")
    @ResponseBody
    private Set<Finance> financeForRoom(@RequestParam("roomName") String name) {
        System.out.println("finance " + name);
        return financesService.getFinancesForRoom(name);
    }

    @RequestMapping(value="/finance/types")
    @ResponseBody
    private List<Type> getTypes() {
        System.out.println("Отправляем типы");
        return financesService.getTypes();
    }

    @RequestMapping(value="/finance/categories")
    @ResponseBody
    private Set<Category> getCategoriesForRoom(@RequestParam("roomName") String roomName) {
        System.out.println("Отправляем категории для этой комнаты:" + roomName);
        return financesService.getCategories(roomsService.getRoomByName(roomName));
    }

    @RequestMapping(value="/finance/categories/create")
    @ResponseBody
    private boolean createCategoryByRoom(@RequestParam("roomName") String roomName, @RequestParam("categoryName") String categoryName, @RequestParam("userName") String userName) {
        System.out.println("Создаем новую категорию для комнаты:" + roomName);
        return usersService.userIsAdmin(usersService.loadUserByUsername(userName));
    }


    @RequestMapping(value="/finance/create")
    @ResponseBody
    private Set<Finance> getCategoriesForRoom(
            @RequestParam("roomName") String roomName,
            @RequestParam("userName") String userName,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("typeName") String typeName,
//            @RequestParam("amount") float amount,
            @RequestParam("amount") String amount,
            @RequestParam("date") String date,
            @RequestParam("comment") String comment) {
        System.out.println("Записываем новый финанс");
        return financesService.createFinance(roomName, userName, categoryName, typeName, amount, date, comment);
    }

    /**
     * Доход / Расход
     */
    @RequestMapping(value="/finance")
    @ResponseBody
    private List<Finance> financeIncomeForRoom(@RequestParam("roomName") String roomName, @RequestParam("typeName") String typeName) {
        System.out.println("financeIncomeForRoom: " + roomName);
        return financesService.getFinancesByTypeForRoom(roomName, typeName);
    }

    @RequestMapping(value="/finance/formated")
    @ResponseBody
    private Set<String> financeIncomeForRoomFormated(@RequestParam("roomName") String roomName, @RequestParam("typeName") String typeName) throws ParseException {
        System.out.println("financeIncomeForRoom: " + roomName);
        return financesService.getDataGroupByRoomByType(roomName, typeName);
    }

    @RequestMapping(value="/finance/date")
    @ResponseBody
    private Map<String, List<Object>> financeIncomeForRoomDate(@RequestParam("roomName") String roomName, @RequestParam("typeName") String typeName, @RequestParam("date") String date) throws ParseException {
        return financesService.getDataGroupByRoomByTypeByDateV2(roomName, typeName, date);
    }

    @RequestMapping(value="/finance/analysis")
    @ResponseBody
    private Map<String, List<Object>> financeConsumptionAnalysis(@RequestParam("roomName") String roomName) throws ParseException {
        System.out.println("financeConsumptionAnalysis: " + roomName);
        return financesService.getDataAnalysisConsumption(roomName);
    }
}
