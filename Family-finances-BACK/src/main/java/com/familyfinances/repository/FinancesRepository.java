package com.familyfinances.repository;

import com.familyfinances.model.Category;
import com.familyfinances.model.Finance;
import com.familyfinances.model.Room;
import com.familyfinances.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface FinancesRepository extends JpaRepository<Finance, Long> {
    @Query("SELECT f.category FROM Finance f  WHERE f.room = ?1")
    Set<Category> findCategoriesForRoom(Room room);

    @Query("SELECT f FROM Finance f WHERE f.room = ?1 and f.type = ?2")
    List<Finance> findFinancesByTypeForRoom(Room room, Type type);

    @Query("SELECT f FROM Finance f WHERE f.room = ?1 order by f.date")
    List<Finance> findFinancesForRoom(Room room);

    @Query(value = "SELECT sum(amount), Types.name FROM Finances, Types WHERE Finances.id_type = Types.id and id_room = ?1 and year(date) = ?2 and month(date) = ?3 GROUP BY id_type", nativeQuery = true)
    List<Object> calculateAnalysisConsumption(int id_room, String year, String month);

    @Query(value = "SELECT sum(amount), Categories.name, Categories.icon FROM Finances, Categories WHERE Finances.id_category = Categories.id and id_room = ?1 and id_type = ?4 and year(date) = ?2 and month(date) = ?3 GROUP BY id_category", nativeQuery = true)
    List<Object> calculateAnalysisConsumptionByCategory(int id_room, String year, String month, int id_type);
}


//    SELECT sum(amount), Categories.name FROM Finances, Categories WHERE Finances.id_type = 2 and Finances.id_category = Categories.id and id_room = 13 and year(date) = 2021 and month(date) = 06 GROUP BY id_category;