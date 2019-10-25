package com.example.henchmate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Exercise::class,
        Workout::class
    ],
    version = 1
)
abstract class HenchMateDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: HenchMateDatabase? = null

        fun getDatabase(context: Context): HenchMateDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HenchMateDatabase::class.java,
                    "henchmate_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
