package com.example.todolist

import android.app.Application
import com.example.todolist.model.db.AppDb

class App : Application() {
    val database by lazy{
       AppDb.createDataBase(this)
    }
}