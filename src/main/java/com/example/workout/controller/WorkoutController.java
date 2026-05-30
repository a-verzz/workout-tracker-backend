package com.example.workout.controller;

import com.example.workout.model.Workout;
import com.example.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService service;

    @GetMapping
    public List<Workout> getAll(@RequestParam(required = false) LocalDate date) {
        if (date != null) {
            return service.getByDate(date);
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Workout getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Workout create(@RequestBody Workout workout) { return service.save(workout); }

    @PutMapping("/{id}")
    public Workout update(@PathVariable Long id, @RequestBody Workout workout) {
        return service.update(id, workout);
    }

    @PatchMapping("/{id}/complete")
    public Workout complete(@PathVariable Long id, @RequestBody(required = false) Map<String, Integer> body) {
        int actualDuration = body != null && body.get("actualDuration") != null ? body.get("actualDuration") : 0;
        int actualSeconds = body != null && body.get("actualSeconds") != null ? body.get("actualSeconds") : 0;
        return service.complete(id, actualDuration, actualSeconds);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
