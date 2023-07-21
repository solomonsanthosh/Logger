package com.example.logger.data.room.Dao

import androidx.room.*
import com.example.logger.data.room.Entity.LogEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LogDAO {
    @Insert
    suspend fun insertLog(logEntity: LogEntity)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLog(logEntity: LogEntity)


    @Delete
    suspend fun deleteLog(logEntity: LogEntity)


    @Query("DELETE FROM logs WHERE created_at < :timestamp")
    suspend fun deleteLogs(timestamp: Long)

    @Query("SELECT * FROM logs ORDER BY created_at DESC")
    fun getLogs(): Flow<List<LogEntity>>

    @Query("SELECT * FROM logs WHERE id = :id")
    fun getLog(id:Long): Flow<LogEntity>
}