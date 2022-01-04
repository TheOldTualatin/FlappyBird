package com.example.flappybrid.ui.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flappybrid.R
import com.example.flappybrid.logic.entities.Transcript

/**
@author YangQX   2022/1/4 - 18:58
 */
class LeaderboardAdapter(private val data:List<Transcript>) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>()
{
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val rank = view.findViewById<TextView>(R.id.rank);
        val score = view.findViewById<TextView>(R.id.leaderboard_score);
        val date = view.findViewById<TextView>(R.id.leaderboard_date);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        return ViewHolder(inflate);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transcript = data[position];
        holder.rank.text = position.toString();
        holder.score.text= transcript.score.toString();
        holder.date.text = transcript.date;
    }

    override fun getItemCount(): Int = data.size;
}