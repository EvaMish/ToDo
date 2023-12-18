package com.example.todolist.view.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.view.custom.MyDatePickerDialog
import com.example.todolist.view.custom.TaskCard
import com.example.todolist.viewmodel.ViewModelDb
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


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
                label = { Text(text = "Label+++++++") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
            )
            val currentContext = LocalContext.current

            IconButton(onClick = {
                if (viewModelDb.newText.value.isNotEmpty() && viewModelDb.newDate.value.isNotEmpty())
                    viewModelDb.insertTask() else Toast.makeText(
                    currentContext,
                    "${viewModelDb.newDate}+++++",
                    Toast.LENGTH_SHORT
                ).show()
            })
            {
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }

        }


        Box(contentAlignment = Alignment.Center) {

            OutlinedTextField(
                value = viewModelDb.newDate.value,
                onValueChange = {
                    viewModelDb.newDate.value = it
                },
                readOnly = true,
                placeholder = {
                    Text(
                        text = "Дата исполнения+++++",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Gray,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .fillMaxWidth(),

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .clickable {
                                showDatePicker = true
                            }
                            .padding(16.dp),
                    )
                },
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

        }
        if (showDatePicker) {
            MyDatePickerDialog(
                onDateSelected = {
                    viewModelDb.newDate.value = it
                },
                onDismiss = { showDatePicker = false }
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(tasksList.value) { item ->
                val itemDate = LocalDate.parse(item.dateSelect, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                val isTaskExpired = itemDate <= LocalDate.now()

                    TaskCard(item, {
                        viewModelDb.tasksDB = it
                        viewModelDb.newText.value = it.description
                        item.dateSelect=it.dateSelect

                    }, {

                        if (item.isCompleted) {
                            viewModelDb.completedTask(item)
                        }
                    },
                        color = if (isTaskExpired) Color.Red else Color.Transparent
                    )


            }


        }


    }
}




