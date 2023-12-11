package com.example.todolist.model

import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("tasks")
data class TasksDB(
    @PrimaryKey(autoGenerate = true)
    val idTask: Int? = null,
    val description: String,
    var isCompleted:Boolean,
    var dateSelect:String

    )// добавить поле для времени исполнения задачи