package com.blossom.forth.progress.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 45, nullable = false, unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "split", cascade = CascadeType.PERSIST)
    private List<Exercise> exercises;

    public Split() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
