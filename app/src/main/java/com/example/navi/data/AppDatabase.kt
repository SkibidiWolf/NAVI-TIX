package com.example.navi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Film::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "film_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}