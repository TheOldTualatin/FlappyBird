package com.example.flappybrid.logic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
@author YangQX   2022/1/4 - 11:34
 */
@Entity(tableName = "Transcript")
class Transcript(
    @PrimaryKey(autoGenerate = true) var id:Int?,
    @ColumnInfo(name = "score")var score:Int,
    @ColumnInfo(name = "date")var date:String,
)