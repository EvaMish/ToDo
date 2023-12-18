package com.example.todolist.view.custom

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import java.util.Date

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//
//fun MyTimePickerDialog(
//    onTimeSelected: (String) -> Unit,
//    onDismiss: () -> Unit
//) {
//    val timePickerState = rememberTimePickerState(selectableDates = object : SelectableDates {
//        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
//            return utcTimeMillis >= System.currentTimeMillis()
//        }
//    })
//
//
//    val selectedDate = datePickerState.selectedDateMillis?.let {
//        convertMillisToDate(it)
//    } ?: ""
//
//    DatePickerDialog(
//        onDismissRequest = { onDismiss() },
//        confirmButton = {
//            Button(onClick = {
//                onDateSelected(selectedDate)
//                onDismiss()
//            }
//
//            ) {
//                Text(text = "OK")
//            }
//        },
//        dismissButton = {
//            Button(onClick = {
//                onDismiss()
//            }) {
//                Text(text = "Cancel")
//            }
//        }
//    ) {
//        DatePicker(
//            state = datePickerState
//        )
//    }
//
//
//
//
//}
//private fun convertMillisToDate(millis: Long): String {
//    val formatter = SimpleDateFormat("dd/MM/yyyy")
//    return formatter.format(Date(millis))
//}