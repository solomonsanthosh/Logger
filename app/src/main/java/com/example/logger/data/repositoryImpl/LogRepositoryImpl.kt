package com.example.logger.data.repositoryImpl

import android.app.Application
import androidx.work.*

import com.example.logger.worker.LogsDeleteWorker
import com.example.logger.data.room.Entity.LogEntity
import com.example.logger.data.room.LogDB
import com.example.logger.data.repository.LogRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class LogRepositoryImpl(private val context: Application, private val logDB: LogDB) : LogRepository {


    override suspend fun insertLog(logEntity: LogEntity) {
        logDB.logDao().insertLog(logEntity)
    }

    override suspend fun updateLog(logEntity: LogEntity) {
        logDB.logDao().updateLog(logEntity)    }

    override suspend fun deleteLog(logEntity: LogEntity) {
        logDB.logDao().deleteLog(logEntity)
    }

    override suspend fun deleteLogs() {


        val timeStampForDeletion = System.currentTimeMillis() -   60 * 1000
        logDB.logDao().deleteLogs(timeStampForDeletion)




    }



    override fun scheduleDelete() {

        val deleteRequest = PeriodicWorkRequestBuilder<LogsDeleteWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).build()

        val workManager = WorkManager.getInstance(context.applicationContext)
        workManager.enqueueUniquePeriodicWork(
            "LogCleanup",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            deleteRequest
        )

    }



    override fun getLogs() = logDB.logDao().getLogs()



    override fun getLog(id: Long): Flow<LogEntity> = logDB.logDao().getLog(id)





}