package com.example.flappybrid.logic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flappybrid.logic.dao.FlappyBirdDao
import com.example.flappybrid.logic.entities.Transcript

/**
@author YangQX   2022/1/3 - 18:26
 */
@Database(entities = [Transcript::class], version = 1)
abstract class FlappyBirdDatabase:RoomDatabase()
{
    abstract fun flappyBirdDao():FlappyBirdDao;
    companion object{
        private var instance : FlappyBirdDatabase? = null;

        @Synchronized
        fun getDatabase(context: Context):FlappyBirdDatabase
        {
            instance?.let {
                return it;
            }
            return Room.databaseBuilder(context.applicationContext,FlappyBirdDatabase::class.java,"Transcript")
                .build().apply {
                    instance = this;
                }
        }
    }
}