package com.codecool.classicalregistry.service;


import com.codecool.classicalregistry.dao.repository.ClassicalPeriodRepository;
import com.codecool.classicalregistry.exceptions.DatabaseIsEmptyException;
import com.codecool.classicalregistry.model.ClassicalPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassicalPeriodService {

    @Autowired
    private ClassicalPeriodRepository classicalPeriodRepository;

    public List<ClassicalPeriod> listAllClassicalPeriods() {
        return classicalPeriodRepository.findAll();
    }

    public ClassicalPeriod getClassicalPeriodById(long id) {
        return classicalPeriodRepository.findById(id).
                orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public void addClassicalPeriod(ClassicalPeriod classicalPeriod) {
        classicalPeriodRepository.save(classicalPeriod);
    }

    public void updateClassicalPeriodById(ClassicalPeriod classicalPeriod, long id) {
        classicalPeriod.setId(id);
        classicalPeriodRepository.save(classicalPeriod);
    }

    public void deleteClassicalPeriodById(long id) {
        classicalPeriodRepository.deleteById(id);
    }
}
