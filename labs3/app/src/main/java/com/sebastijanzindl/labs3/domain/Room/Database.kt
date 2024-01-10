package com.sebastijanzindl.labs3.domain.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sebastijanzindl.labs3.domain.Models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = true)
abstract class Lab3Database: RoomDatabase() {
    abstract fun dao(): MovieDao
    companion object {
        @Volatile
        private var INSTANCE: Lab3Database? = null

        @JvmStatic
        fun getDB(context: Context): Lab3Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    Lab3Database::class.java,
                    "lab3_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}