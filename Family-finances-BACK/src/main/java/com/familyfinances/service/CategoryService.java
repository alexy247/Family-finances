package com.familyfinances.service;

import com.familyfinances.model.Category;
import com.familyfinances.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Service
public class CategoryService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Category getDefaultCategory() {
        Category category = categoriesRepository.findByName("Продукты");
        System.out.println("category is " + category);
        return category;
    }

    public Set<Category> getDefaultCategories() {
        return categoriesRepository.findByDefault();
    }

    public Category getCategoryByName(String name) {
        return categoriesRepository.findByName(name);
    }
}
