package com.example.workout.controller;

import com.example.workout.model.Workout;
import com.example.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService service;

    @GetMapping
    public List<Workout> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Workout getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Workout create(@RequestBody Workout workout) { return service.save(workout); }

    @PutMapping("/{id}")
    public Workout update(@PathVariable Long id, @RequestBody Workout workout) {
        return service.update(id, workout);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}