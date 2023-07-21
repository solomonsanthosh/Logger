package com.example.logger.views.log_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.logger.views.Screen
import com.example.logger.views.log_list.components.LogCard
import com.example.logger.views.ui.theme.listBg
import com.example.logger.viewModel.LogViewModel


@Composable
fun LogListScreen (viewModel: LogViewModel, navController: NavController) {

    val logs by viewModel.logs.collectAsState(initial = emptyList())



    Column(modifier = Modifier
        .fillMaxSize()
        .background(listBg)) {
        TopAppBar(

            title={ Text(text = "Logger", color = Color.Black)} ,
            backgroundColor = listBg,
            elevation = 0.dp,

            actions = {
                IconButton(onClick = {

                    navController.navigate(Screen.LogAddScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
                }
            }
        )

    
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(listBg)
        .weight(1f)){



        if(logs.isEmpty()){
            item {

                Text(text = "No logs to display",color=Color.DarkGray, textAlign = TextAlign.Center, modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth())
            }
        } else{
            items(items = logs){
                    item -> LogCard(item = item,navController,viewModel)
            }
        }

    }



    }

}