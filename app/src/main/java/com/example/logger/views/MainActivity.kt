package com.example.logger.views

import android.annotation.SuppressLint
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logger.views.log_add.LogAddScreen
import com.example.logger.views.log_detail.LogDetailScreen
import com.example.logger.views.log_list.LogListScreen
import com.example.logger.views.log_update.logUpdateScreen

import com.example.logger.views.ui.theme.LoggerTheme
import com.example.logger.views.ui.theme.popupColor
import com.example.logger.viewModel.LogViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            LoggerTheme {

                Surface(

                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = hiltViewModel<LogViewModel>()
                    val navController = rememberNavController()
                    viewModel.scheduleLogsCleanup()




                    var selected by remember {
                        mutableStateOf("Home")
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.LogListScreen.route,

                            ) {
                            composable(route = Screen.LogListScreen.route) {
                                LogListScreen(viewModel, navController = navController)
                            }
                            composable(route = Screen.LogAddScreen.route) {
                                LogAddScreen(viewModel, navController = navController)
                            }

                            composable(route = Screen.LogDetailScreen.route) {
                                LogDetailScreen(
                                    viewModel,
                                    navController = navController,
                                    id = it.arguments!!.getString("id")!!.toLong()
                                )
                            }
                            composable(route = Screen.LogUpdateScreen.route) {
                                logUpdateScreen(
                                    viewModel,
                                    navController = navController,
                                    id = it.arguments!!.getString("id")!!.toLong()
                                )
                            }
                            composable(route = Screen.LanguageScreen.route) {
                                language(

                                )
                            }
                        }
                        BottomNavigation(
                            backgroundColor = popupColor,
                            modifier = Modifier.align(alignment = Alignment.BottomCenter)
                        ) {
                            BottomNavigationItem(selected = selected == "Home", onClick = {

                                selected = "Home"
                                navController.navigate(Screen.LogListScreen.route)
                            }, icon = {
                                Icon(imageVector = Icons.Default.List, contentDescription = "Home")
                            })

                            BottomNavigationItem(selected = selected == "Language", onClick = {
                                selected = "Language"
                                navController.navigate(Screen.LanguageScreen.route)
                            }, icon = {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Language"
                                )
                            })
                        }
                    }


                }
            }
        }
    }


}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LoggerTheme {
//        Greeting()
//    }
//}