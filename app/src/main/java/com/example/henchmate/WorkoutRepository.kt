package com.example.henchmate

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val allWorkouts: List<Workout> = workoutDao.loadWorkouts()

    fun insert(workout: Workout) {
        workoutDao.insert(workout)
    }
}