package com.example.logger.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.logger.data.room.Entity.LogEntity
import com.example.logger.data.repository.LogRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class LogViewModel @Inject constructor(private val repository: LogRepository,val context:Application) : ViewModel()  {



    val logs = repository.getLogs()


    val _logSingle = MutableLiveData<LogEntity?>(null)
    var logSingle: LiveData<LogEntity?> = _logSingle


    fun addLog(log: LogEntity) {
        viewModelScope.launch {

            repository.insertLog(log)
        }
    }

    fun updateLog(log: LogEntity) {
        viewModelScope.launch {
            repository.updateLog(log)
        }
    }

    fun deleteLog(log: LogEntity) {
        viewModelScope.launch {
            repository.deleteLog(log)
        }
    }

    fun deleteLogByWorker() {
        viewModelScope.launch {
            repository.deleteLogs()
            logs.collect {
                repository.deleteLogs()
            }

        }
    }

    fun scheduleLogsCleanup() {
        Log.d("WM", "Hss")
        repository.scheduleDelete()
    }

    fun getSingleLog(id: Long) {
        viewModelScope.launch {
            repository.getLog(id)
                .collect { log ->

                    _logSingle.value = log
                }
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun convertMillisecondsToDate(milliseconds: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(milliseconds)
        return sdf.format(date)
    }




//    fun observeLogsDeleted() {
//        WorkManager.getInstance(context.applicationContext).getWorkInfosByTagLiveData("LogCleanup")
//            .observeForever { workInfos ->
//                Log.d("INFO",workInfos.toString())
//                if (workInfos.isNotEmpty()) {
//                    val workInfo = workInfos[0]
//
//                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
//                        val logsDeleted = workInfo.outputData.getBoolean("logsDeleted", false)
//                        if(logsDeleted == true){
//                            viewModelScope.launch {
//                                logs.collect{
//                                    repository.getLogs()
//                                }
//                            }
//
//                        }
//                    }
//                }
//            }
//    }



}