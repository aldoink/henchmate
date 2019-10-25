package com.example.henchmate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM Workout")
    fun loadWorkouts(): List<Workout>

    @Insert
    fun insert(workout: Workout)
}