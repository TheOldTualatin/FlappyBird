package com.example.flappybrid.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flappybrid.logic.entities.Transcript

/**
@author YangQX   2022/1/3 - 17:11
 */
@Dao
interface FlappyBirdDao
{
    @Insert
    fun addScore(transcript: Transcript):Long;

    @Query("select * from Transcript ORDER BY :score desc")
    fun queryAllTranscript():LiveData<List<Transcript>>;

    @Update
    fun deleteTranscriptByID(transcript: Transcript);
}