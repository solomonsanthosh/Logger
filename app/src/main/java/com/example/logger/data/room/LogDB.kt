package com.example.logger.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.logger.data.room.Dao.LogDAO
import com.example.logger.data.room.Entity.LogEntity


@Database(entities = [LogEntity::class], version = 1, exportSchema = false)
abstract class LogDB : RoomDatabase() {

    abstract fun logDao(): LogDAO


}