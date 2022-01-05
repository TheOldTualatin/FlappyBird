package com.example.flappybrid.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.flappybrid.logic.entities.Transcript

/**
@author YangQX   2022/1/3 - 17:11
 最多一次显示10条数据，按成绩高低降序排列
 */
@Dao
interface FlappyBirdDao
{
    @Insert
    fun addScore(transcript: Transcript):Long;

    @Query("select * from Transcript ORDER BY score desc limit 0,7")
    fun queryAllTranscript(): List<Transcript>;

    @Query("update Transcript set score = :score,date = :date where id = :id")
    fun updateTranscriptByID(id:Int,score:Int,date:String,);

    @Query("select * from Transcript ORDER BY score desc limit 9,1")
    fun queryWorstScore():List<Transcript>

    @Query("select count(*) from Transcript")
    fun queryTotalNumber():Int;
}