package com.example.todolist.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasksDB: TasksDB)

//    @Query("SELECT * FROM tasks WHERE idTask= :idTask ")
//    suspend fun getTaskById(idTask:Int):TasksDB?


    @Delete
    suspend fun deleteCompletedTasks(idTasks: TasksDB)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TasksDB>>


}