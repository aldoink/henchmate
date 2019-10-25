package com.example.henchmate

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Number,
    val name: String
)

class WorkoutWithExercises(
    @Embedded
    val workout: Workout,
    @Relation(entity = Exercise::class, parentColumn = "id", entityColumn = "workoutId")
    val exercises: List<Exercise>
)