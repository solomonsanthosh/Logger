package com.example.logger.data.repository

import com.example.logger.data.room.Entity.LogEntity
import kotlinx.coroutines.flow.Flow

interface LogRepository {


    suspend fun insertLog(logEntity: LogEntity)

    suspend fun updateLog(logEntity: LogEntity)

    suspend fun deleteLog(logEntity: LogEntity)


    suspend fun deleteLogs()

    fun scheduleDelete()

    fun getLogs(): Flow<List<LogEntity>>

     fun getLog(id:Long): Flow<LogEntity>
}
