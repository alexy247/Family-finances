package com.familyfinances.service;

import com.familyfinances.model.Type;
import com.familyfinances.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TypeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TypeRepository typeRepository;

    public Type getTypeByName(String name) {
        return typeRepository.findByTypeName(name);
    }
}
