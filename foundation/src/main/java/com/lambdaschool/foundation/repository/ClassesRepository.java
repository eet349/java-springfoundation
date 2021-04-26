package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.FitnessClass;
import org.springframework.data.repository.CrudRepository;

public interface ClassesRepository extends CrudRepository<FitnessClass, Long> {
//    @Override
//    FitnessClass save(FitnessClass fitnessClass);
}
