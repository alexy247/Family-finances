package com.familyfinances.repository;

import com.familyfinances.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query("SELECT t FROM Type t WHERE t.name = ?1")
    Type findByTypeName(String name);
}
