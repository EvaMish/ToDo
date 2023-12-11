package com.example.todolist.viewmodel

import android.icu.util.LocaleData
import android.os.Build
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.todolist.App
import com.example.todolist.model.AppDb
import com.example.todolist.model.TasksDB
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date
import java.util.Locale

class ViewModelDb(private val database: AppDb) : ViewModel() {
    val tasksList = database.dao.getAllTasks()
    val newText = mutableStateOf("")
    val newDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        mutableStateOf(LocalDate.now().toString())
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    var tasksDB: TasksDB? = null


    fun insertTask() = viewModelScope.launch {
        val descriptionItem = tasksDB?.copy(description = newText.value) ?: TasksDB(
            description = newText.value,
            isCompleted = false,
            dateSelect = newDate.value
        )////isCompleted-уточнить
        database.dao.insertTask(descriptionItem)
        tasksDB = null
        newText.value = ""
    }

    fun completedTask(item: TasksDB) = viewModelScope.launch {
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