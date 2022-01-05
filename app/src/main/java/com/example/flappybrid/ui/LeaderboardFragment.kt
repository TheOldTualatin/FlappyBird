package com.example.flappybrid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flappybrid.databinding.FragmentLeaderboardBinding
import com.example.flappybrid.logic.database.FlappyBirdDatabase
import com.example.flappybrid.logic.entities.Transcript
import com.example.flappybrid.ui.leaderboard.LeaderboardAdapter
import kotlin.concurrent.thread

/**
 * 功能：
 *      1.绘制排行榜界面
 *      2.完善数据库方法
 */
class LeaderboardFragment : Fragment() {
    lateinit var queryAllTranscript:List<Transcript>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        thread {
            val flappyBirdDao = FlappyBirdDatabase.getDatabase(requireContext()).flappyBirdDao();
            queryAllTranscript = flappyBirdDao.queryAllTranscript();
        }
        val view = FragmentLeaderboardBinding.inflate(layoutInflater)
        view.recyclerView.layoutManager = linearLayoutManager;
        view.recyclerView.adapter = LeaderboardAdapter(queryAllTranscript);
        return view.root
    }
}