package com.example.logger.views.log_add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.logger.data.room.Entity.LogEntity
import com.example.logger.views.ui.theme.descriptionColor
import com.example.logger.views.ui.theme.listBg
import com.example.logger.views.ui.theme.textBgColor
import com.example.logger.viewModel.LogViewModel


@Composable
fun LogAddScreen(viewModel: LogViewModel, navController: NavHostController) {

    var description by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(listBg)) {

        val modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 15.dp, end = 15.dp)
        Text(
            text = "Add a Log",
            modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )


        TextField(value = description,
            onValueChange = { newDesc -> description = newDesc },
            modifier = modifier.height(200.dp),
            colors = TextFieldDefaults.textFieldColors(
                placeholderColor = descriptionColor,
                textColor = Color.Black,
                backgroundColor = textBgColor,
                focusedIndicatorColor = Color.Black
            ),
            placeholder = {
                Text(
                    text = "Log Description..."
                )
            },
            label = {
                Text(text = "Description", color = Color.DarkGray)
            }
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = textBgColor,
                backgroundColor = Color.Black,
                contentColor = Color.White

            ),
            enabled = !description.isNullOrEmpty(),
            onClick = {

                viewModel.addLog(LogEntity(0, description,System.currentTimeMillis()))
                navController.popBackStack()






            }, modifier = Modifier.padding(
                15.dp
            )
        ) {
            Text(text = "Add Log")
        }

    }
}

