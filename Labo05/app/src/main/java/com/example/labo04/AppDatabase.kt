package com.example.labo04

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.labo04.model.Task

@Database(entities = [Task::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}