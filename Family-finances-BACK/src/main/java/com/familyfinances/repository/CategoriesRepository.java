package com.familyfinances.repository;

import com.familyfinances.model.Category;
import com.familyfinances.model.Role;
import com.familyfinances.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.isDefault > 0")
    Set<Category> findByDefault();

    @Query("SELECT c FROM Category c WHERE c.isDefault > 0")
    Category findOneByDefault();

    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    Category findByName(String name);
}
