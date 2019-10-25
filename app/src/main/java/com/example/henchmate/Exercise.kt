package com.example.henchmate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Number,
    val workoutId: Number,
    val name: String,
    val numberOfSets: Int,
    val repsPerSet: Int,
    val weight: Number,
    val setHistory: MutableList<String> = ArrayList()
) {
    init {
        for (i in 0 until numberOfSets) {
            setHistory.add("")
        }
    }
}