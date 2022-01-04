package com.example.flappybrid.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flappybrid.logic.entities.Transcript
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
@author YangQX   2022/1/3 - 17:11
 最多一次显示10条数据，按成绩高低降序排列
 */
@Dao
interface FlappyBirdDao
{
    @Insert
    fun addScore(transcript: Transcript):Long;

    @Query("select * from Transcript ORDER BY score desc limit 0,10")
    fun queryAllTranscript(): List<Transcript>;

    @Query("update Transcript set score = :score,date = :date where id = :id")
    fun updateTranscriptByID(id:Int,score:Int,date:String,);

    @Query("select * from Transcript ORDER BY score desc limit 9,1")
    fun queryWorstScore():List<Transcript>

    @Query("select count(*) from Transcript")
    fun queryTotalNumber():Int;
}