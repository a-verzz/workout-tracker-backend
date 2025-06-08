package com.example.workout.service;

import com.example.workout.model.Workout;
import com.example.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository repository;

    public List<Workout> getAll() { return repository.findAll(); }

    public Workout getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Workout save(Workout workout) {
        return repository.save(workout);
    }

    public Workout update(Long id, Workout workout) {
        Workout existing = getById(id);
        existing.setName(workout.getName());
        existing.setDuration(workout.getDuration());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}