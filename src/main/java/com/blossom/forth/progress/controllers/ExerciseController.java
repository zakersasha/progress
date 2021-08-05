package com.blossom.forth.progress.controllers;

import com.blossom.forth.progress.enums.BodyType;
import com.blossom.forth.progress.models.Exercise;
import com.blossom.forth.progress.repo.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class ExerciseController {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/exercise")
    public String exercise(Model model) {
        Iterable<Exercise> exercises = exerciseRepository.findAll();
        model.addAttribute("exercises", exercises);
        return "exercise";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Exercise> exercises;
        if (filter != null && !filter.isEmpty()) {
            exercises = exerciseRepository.findByName(filter);
        } else {
            exercises = exerciseRepository.findAll();
        }

        model.put("exercises", exercises);
        return "exercise";
    }

    @GetMapping("/exercise/add")
    public String addExercisePage(Model model) {
        model.addAttribute(new Exercise());
        model.addAttribute("bodyTypes", BodyType.values());
        return "exercise-add";
    }

    @PostMapping("/exercise/add")
    public String addExercise(@RequestParam BodyType type, String name, String description, Model model) {
        Exercise exercise = new Exercise(type, name, description);
        exerciseRepository.save(exercise);
        return "redirect:/exercise";
    }


    @GetMapping("/exercise/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model) {
        if (!exerciseRepository.existsById(id)) {
            return "redirect:/exercise";
        }

        Optional<Exercise> person = exerciseRepository.findById(id);
        ArrayList<Exercise> result = new ArrayList<>();
        person.ifPresent(result::add);
        model.addAttribute("exercise", result);

        return "exercise-details";
    }

    @GetMapping("/exercise/{id}/edit")
    public String exerciseEdit(@PathVariable(value = "id") long id, Model model) {
        if (!exerciseRepository.existsById(id)) {
            return "redirect:/exercise";
        }

        Optional<Exercise> exercise = exerciseRepository.findById(id);
        ArrayList<Exercise> result = new ArrayList<>();
        exercise.ifPresent(result::add);
        model.addAttribute("exercise", result);

        return "exercise-edit";
    }

    @PostMapping("/exercise/{id}/edit")
    public String exerciseDataUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam BodyType type, @RequestParam String description, Model model) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        exercise.setName(name);
        exercise.setType(type);
        exercise.setDescription(description);
        exerciseRepository.save(exercise);

        return "redirect:/exercise";
    }


    @PostMapping("/exercise/{id}/remove")
    public String userDataRemove(@PathVariable(value = "id") long id, Model model) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        exerciseRepository.delete(exercise);

        return "redirect:/exercise";
    }
}
