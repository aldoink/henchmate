package com.example.henchmate

import kotlin.collections.ArrayList

class Exercise(
    val exerciseName: String,
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