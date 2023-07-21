package com.example.logger.views.log_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.logger.data.room.Entity.LogEntity
import com.example.logger.views.ui.theme.dateColor
import com.example.logger.views.ui.theme.descriptionColor
import com.example.logger.views.ui.theme.popupColor
import com.example.logger.viewModel.LogViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LogCard(item: LogEntity, navController: NavController,viewModel: LogViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier

            .combinedClickable(onClick = { navController.navigate("log_detail_screen/${item.id}") },
                onLongClick = {


                    expanded = true
                })
            .padding(start = 12.dp, end = 12.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            , elevation = 1.dp, shape = RoundedCornerShape(5.dp), backgroundColor = Color.White
    ) {


        DropdownMenu(
            modifier=Modifier.background(popupColor),
            properties= PopupProperties(usePlatformDefaultWidth = true),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(

                onClick= {
                    navController.navigate("log_update_screen/${item.id}")
                },
                content = {
                     Text("Update")
                },


            )
            Divider()
            DropdownMenuItem(
                content = {
                    Text("Delete")
                },

                onClick = {
                    viewModel.deleteLog(item)
                    expanded = false

                }
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            Text(
                text = item.description,
                color = descriptionColor,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = viewModel.convertMillisecondsToDate(item.createdAt),
                color = dateColor,

                modifier = Modifier.padding(top = 5.dp),
                fontSize = 14.sp
            )

        }
    }

}