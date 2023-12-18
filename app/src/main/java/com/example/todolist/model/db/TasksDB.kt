package com.example.todolist.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class TasksDB(
    @PrimaryKey(autoGenerate = true)
    val idTask: Int? = null,
    val description: String,
    var isCompleted:Boolean,
    var dateSelect: String? = null
    )// добавить поле для времени исполнения задачи