package com.example.logger.views

sealed class Screen(val route:String){
    object LogListScreen:Screen("log_list_screen")
    object LogDetailScreen:Screen("log_detail_screen/{id}")
    object LogUpdateScreen:Screen("log_update_screen/{id}")
    object LogAddScreen:Screen("log_add_screen")
    object LanguageScreen:Screen("language_screen")


}
