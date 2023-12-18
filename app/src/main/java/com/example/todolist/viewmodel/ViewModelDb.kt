package com.example.todolist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.todolist.App
import com.example.todolist.model.db.AppDb
import com.example.todolist.model.db.TasksDB
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


class ViewModelDb(private val database: AppDb) : ViewModel() {
    val tasksList = database.dao.getAllTasks()
    val newText = mutableStateOf("")
    var newDate =
        mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString())

    //val newDate=
    var tasksDB: TasksDB? = null


    fun insertTask() = viewModelScope.launch {
        val descriptionItem =
            tasksDB?.copy(description = newText.value, dateSelect = newDate.value) ?: TasksDB(
                description = newText.value,
                isCompleted = false,
                dateSelect = newDate.value
            )
        database.dao.insertTask(descriptionItem)
        tasksDB = null
        newText.value = ""
        newDate.value = LocalDate.now().toString()
    }

    fun completedTask(item: TasksDB) = viewModelScope.launch {
        //delay(1000)
        database.dao.deleteCompletedTasks(item)
    }


    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                val dataBase = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return ViewModelDb(dataBase) as T
            }

        }

    }
}