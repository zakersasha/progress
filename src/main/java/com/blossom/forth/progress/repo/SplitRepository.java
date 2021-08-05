package com.blossom.forth.progress.repo;

import com.blossom.forth.progress.models.Split;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SplitRepository extends JpaRepository<Split, Long> {
    List<Split> findByName(String name);
}
