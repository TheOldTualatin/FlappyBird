package com.example.flappybrid.logic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
@author YangQX   2022/1/3 - 17:03
 */
@Entity(tableName = "Transcript")
data class Transcript(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "score")val score:Int,
    @ColumnInfo(name = "date")val date:String,
)