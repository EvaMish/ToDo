package com.example.todolist.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.view.custom.CalendarWithTimePicker
import com.example.todolist.view.custom.MyDatePickerDialog
import com.example.todolist.view.custom.TaskCard
import com.example.todolist.viewmodel.ViewModelDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModelDb: ViewModelDb = viewModel(factory = ViewModelDb.factory)) {
    val tasksList = viewModelDb.tasksList.collectAsState(initial = emptyList())
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable {}
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModelDb.newText.value,
                onValueChange = {
                    viewModelDb.newText.value = it
                },
                label = { Text(text = "Label") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),

                )
            IconButton(onClick = { if (viewModelDb.newText.value != "") viewModelDb.insertTask() })
            {
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }
            // CalendarWithTimePicker()/////
            Box(contentAlignment = Alignment.Center) {
                Button(onClick = { showDatePicker = true }) {
                    Text(text = "viewModelDb.newDate.value")
                }
            }

            if (showDatePicker) {
                MyDatePickerDialog(
                    onDateSelected = { viewModelDb.newDate.value = it },
                    onDismiss = { showDatePicker = false }
                )
            }


        }
        Spacer(modifier = Modifier.height(5.dp))


        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(tasksList.value) { item ->
                TaskCard(item, {
                    viewModelDb.tasksDB = it
                    viewModelDb.newText.value = it.description
                }, {

                    if (item.isCompleted) {
                        viewModelDb.completedTask(item)
                    }
                })
            }


        }

    }

}


