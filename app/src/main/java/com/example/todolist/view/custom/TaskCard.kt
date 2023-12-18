package com.example.todolist.view.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.model.db.TasksDB

@Composable
fun TaskCard(
    item: TasksDB,
    onClick: (TasksDB) -> Unit,
    onClickDelete: (TasksDB) -> Unit,
    color: Color
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClick(item)
            },
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),

        )
    {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.description}-${item.dateSelect} ",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(5.dp)
            )
//            IconButton(onClick = { onClickDelete(item) }) {
//                Icon(imageVector = Icons.Default.Check, contentDescription = "")
//
//            }

            Checkbox(
                checked = item.isCompleted,
                onCheckedChange = {
                    item.isCompleted = it
                    onClickDelete(item)

                })
        }


    }
}