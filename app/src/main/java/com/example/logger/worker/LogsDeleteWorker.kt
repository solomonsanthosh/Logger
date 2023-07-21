package com.example.logger.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.logger.data.repository.LogRepository

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class LogsDeleteWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    val repository: LogRepository

) : CoroutineWorker(context, workerParameters) {
    override suspend
    fun doWork(): Result {
        return try {
            Log.d("LOG","AA")

            repository.deleteLogs()


            val outputData = Data.Builder()
                .putBoolean("logsDeleted", true)
                .build()

            Result.success(outputData)
        } catch (e: Exception) {
            Log.d("ERR",e.toString())
            Result.failure()
        }
    }
}