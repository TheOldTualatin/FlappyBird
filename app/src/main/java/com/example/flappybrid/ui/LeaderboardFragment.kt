package com.example.flappybrid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flappybrid.R
import com.example.flappybrid.databinding.FragmentLeaderboardBinding
import com.example.flappybrid.databinding.LeaderboardItemBinding
import com.example.flappybrid.logic.dao.FlappyBirdDao
import com.example.flappybrid.logic.database.FlappyBirdDatabase
import com.example.flappybrid.ui.leaderboard.LeaderboardAdapter
import kotlin.concurrent.thread

/**
 * 功能：
 *      1.绘制排行榜界面
 *      2.完善数据库方法
 */
class LeaderboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val flappyBirdDao = FlappyBirdDatabase.getDatabase(requireContext()).flappyBirdDao();
        val queryAllTranscript = flappyBirdDao.queryAllTranscript();
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val view = FragmentLeaderboardBinding.inflate(layoutInflater)
        view.recyclerView.layoutManager = linearLayoutManager;
        view.recyclerView.adapter = LeaderboardAdapter(queryAllTranscript);
        return view.root
    }
}