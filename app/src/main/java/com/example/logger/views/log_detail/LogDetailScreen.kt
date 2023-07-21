package com.example.logger.views.log_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import com.example.logger.views.ui.theme.dateColor
import com.example.logger.views.ui.theme.descriptionColor
import com.example.logger.views.ui.theme.listBg
import com.example.logger.viewModel.LogViewModel


@Composable
fun LogDetailScreen(viewModel: LogViewModel, navController: NavHostController, id: Long) {


    val log = viewModel.logSingle.observeAsState().value


    LaunchedEffect(id){
        viewModel.getSingleLog(id)
    }

    val modifier  = Modifier.padding(top=10.dp, start = 20.dp,end=20.dp)
    Column(modifier = Modifier
        .background(listBg)
        .fillMaxSize()) {

            Text(text = log?.description?:"", color = descriptionColor, modifier = modifier, fontSize = 18.sp)

        if (log != null) {
            Text(
                text = viewModel.convertMillisecondsToDate(log.createdAt),
                color = dateColor,
                modifier = modifier,
                fontSize = 16.sp
            )
            }




    }
}





