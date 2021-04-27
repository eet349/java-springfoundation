package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.FitnessClass;

import java.util.List;

public interface ClassesService {
    List<FitnessClass> findAll();
    FitnessClass findFitnessClassById(Long id);
    FitnessClass save(FitnessClass fitnessClass);
    void delete(long id);

}
