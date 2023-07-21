package com.example.logger.data.room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "logs")
data class LogEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val description:String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
