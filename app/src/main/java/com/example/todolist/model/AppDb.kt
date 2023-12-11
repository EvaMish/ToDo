package com.example.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TasksDB::class], version = 1)
abstract class AppDb() : RoomDatabase() {

    abstract val dao: Dao

    companion object {

        fun createDataBase(context: Context): AppDb {
            return Room.databaseBuilder(
                context,
                AppDb::class.java,
                "todo.db"//last-test
            ).build()
        }
    }
}