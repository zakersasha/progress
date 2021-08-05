package com.blossom.forth.progress.repo;

import com.blossom.forth.progress.models.Exercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    List<Exercise> findByName(String name);
}
