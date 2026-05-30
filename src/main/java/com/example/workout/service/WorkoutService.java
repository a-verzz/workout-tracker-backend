package com.example.workout.service;

import com.example.workout.model.Workout;
import com.example.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository repository;

    public List<Workout> getAll() {
        return repository.findAll();
    }

    public List<Workout> getByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public Workout getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Workout save(Workout workout) {
        return repository.save(workout);
    }

    public Workout update(Long id, Workout workout) {
        Workout existing = getById(id);
        existing.setName(workout.getName());
        existing.setDate(workout.getDate());
        existing.setDuration(workout.getDuration());
        existing.setActualDuration(workout.getActualDuration());
        existing.setCompleted(workout.isCompleted());
        existing.setCompletedAt(workout.getCompletedAt());
        existing.setCategory(workout.getCategory());
        existing.setTargetMuscle(workout.getTargetMuscle());
        existing.setIntensity(workout.getIntensity());
        existing.setCalories(workout.getCalories());
        existing.setNotes(workout.getNotes());
        return repository.save(existing);
    }

    public Workout complete(Long id, int actualDuration) {
        Workout existing = getById(id);
        existing.setCompleted(true);
        existing.setCompletedAt(LocalDateTime.now());
        existing.setActualDuration(actualDuration);
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
