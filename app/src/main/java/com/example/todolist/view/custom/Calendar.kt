package com.example.todolist.view.custom

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarWithTimePicker() {
    var datePicked by remember { mutableStateOf("1") }
    var timePicked by remember { mutableStateOf("12:00 AM") }

    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        R.style.CustomDatePickerDialog,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            datePicked = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    val timePickerDialog = TimePickerDialog(
        context,
        R.style.CustomTimePickerDialog,
        { _, hourOfDay, minute ->
            val amPm = if (hourOfDay < 12) "AM" else "PM"
            val hour = if (hourOfDay % 12 == 0) 12 else hourOfDay % 12
            timePicked = String.format("%02d:%02d %s", hour, minute, amPm)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )

    Column {
        OutlinedTextField(
            value = datePicked,
            onValueChange = {

            },
            readOnly = true,
            placeholder = {
                Text(
                    text = "Дата исполнения",
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
                            datePickerDialog.show()
                            timePickerDialog.show()
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

//        OutlinedTextField(
//            value = timePicked,
//            onValueChange = {},
//            readOnly = true,
//            placeholder = {
//                Text(
//                    text = "Время исполнения",
//                    fontFamily = FontFamily.SansSerif,
//                    fontWeight = FontWeight.Light,
//                    fontSize = 14.sp,
//                    color = Color.Gray
//                )
//            },
//            modifier = Modifier
//                .border(
//                    1.dp,
//                    Color.Gray,
//                    shape = RoundedCornerShape(25.dp)
//                )
//                .fillMaxWidth(),
//
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.AccountCircle,
//                    contentDescription = null,
//                    tint = Color.DarkGray,
//                    modifier = Modifier
//                        .clickable {
//                            timePickerDialog.show()
//                        }
//                        .padding(16.dp),
//                )
//            },
//            shape = RoundedCornerShape(25.dp),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Black,
//                unfocusedBorderColor = Color.Gray
//            )
//        )
    }
}



