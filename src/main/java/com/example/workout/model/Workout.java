package com.example.workout.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate date;
    private int duration;
    private int actualDuration;
    private int actualSeconds;
    private boolean completed;
    private LocalDateTime completedAt;
    private String category;
    private String targetMuscle;
    private String intensity;
    private int calories;
    private String notes;

    // Scheduling: "once", "daily", "weekdays"
    private String repeatMode = "once";

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "workout_repeat_days", joinColumns = @JoinColumn(name = "workout_id"))
    @Column(name = "day_value")
    private List<Integer> repeatDays = new ArrayList<>();

    // Override tracking: parentId links an override/skip record back to its master schedule row
    private Long parentId;

    // Boolean wrapper so Jackson serialises as "isOverride" (via getIsOverride getter)
    private Boolean isOverride;
    private boolean skipped;

    public Workout() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getActualDuration() { return actualDuration; }
    public void setActualDuration(int actualDuration) { this.actualDuration = actualDuration; }

    public int getActualSeconds() { return actualSeconds; }
    public void setActualSeconds(int actualSeconds) { this.actualSeconds = actualSeconds; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTargetMuscle() { return targetMuscle; }
    public void setTargetMuscle(String targetMuscle) { this.targetMuscle = targetMuscle; }

    public String getIntensity() { return intensity; }
    public void setIntensity(String intensity) { this.intensity = intensity; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getRepeatMode() { return repeatMode; }
    public void setRepeatMode(String repeatMode) { this.repeatMode = repeatMode != null ? repeatMode : "once"; }

    public List<Integer> getRepeatDays() { return repeatDays; }
    public void setRepeatDays(List<Integer> repeatDays) { this.repeatDays = repeatDays != null ? repeatDays : new ArrayList<>(); }

    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }

    // getter name getIsOverride → Jackson JSON key "isOverride"
    public Boolean getIsOverride() { return isOverride; }
    public void setIsOverride(Boolean isOverride) { this.isOverride = isOverride; }

    public boolean isSkipped() { return skipped; }
    public void setSkipped(boolean skipped) { this.skipped = skipped; }
}
