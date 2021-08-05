package com.blossom.forth.progress.controllers;

import com.blossom.forth.progress.enums.BodyType;
import com.blossom.forth.progress.models.Exercise;
import com.blossom.forth.progress.models.Split;
import com.blossom.forth.progress.repo.ExerciseRepository;
import com.blossom.forth.progress.repo.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SplitController {

    @Autowired
    private SplitRepository splitRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/split")
    public String splitShow(Model model) {
        Iterable<Split> splits = splitRepository.findAll();
        model.addAttribute("splits", splits);
        return "splits";
    }

    @GetMapping("/split/add")
    public String split(Model model) {
        Iterable<Exercise> exercises = exerciseRepository.findAll();
        model.addAttribute("exercises", exercises);
        model.addAttribute("split", new Split());
        return "split-add";
    }

    @PostMapping("/split/add")
    public String splitAdd(Split split) {
        splitRepository.save(split);
        return "redirect:/split";
    }

    @GetMapping("/split/{id}")
    public String splitDetails(@PathVariable(value = "id") long id, Model model) {
        if (!splitRepository.existsById(id)) {
            return "redirect:/splits";
        }

        Optional<Split> split = splitRepository.findById(id);
        ArrayList<Split> result = new ArrayList<>();
        split.ifPresent(result::add);
        model.addAttribute("split", result);

        return "split-details";
    }

    @GetMapping("/split/{id}/edit")
    public String splitEdit(@PathVariable(value = "id") long id, Model model) {
        if (!splitRepository.existsById(id)) {
            return "redirect:/split";
        }

        Optional<Split> split = splitRepository.findById(id);
        ArrayList<Split> result = new ArrayList<>();
        split.ifPresent(result::add);
        model.addAttribute("split", result);

        return "split-edit";
    }

    @PostMapping("/split/{id}/edit")
    public String splitDataUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam List<Exercise> exercises, @RequestParam String description, Model model) {
        Split split = splitRepository.findById(id).orElseThrow();
        split.setName(name);
        split.setExercises(exercises);
        split.setDescription(description);
        splitRepository.save(split);

        return "redirect:/split";
    }


    @PostMapping("/split/{id}/remove")
    public String splitDataRemove(@PathVariable(value = "id") long id, Model model) {
        Split split = splitRepository.findById(id).orElseThrow();
        splitRepository.delete(split);

        return "redirect:/split";
    }

    @PostMapping("finder")
    public String finder(@RequestParam String finder, Map<String, Object> model) {
        Iterable<Split> splits;
        if (finder != null && !finder.isEmpty()) {
            splits = splitRepository.findByName(finder);
        } else {
            splits = splitRepository.findAll();
        }

        model.put("splits", splits);
        return "splits";
    }
}
