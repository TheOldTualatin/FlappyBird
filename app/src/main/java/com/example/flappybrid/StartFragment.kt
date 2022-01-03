package com.example.flappybrid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flappybrid.databinding.FragmentStartBinding
import com.example.flappybrid.ui.bird.BirdImpl
import com.example.flappybrid.ui.land.LandImpl

class StartFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        val fragmentStartBinding = FragmentStartBinding.inflate(layoutInflater);
        val bird = fragmentStartBinding.bird;
        val land = fragmentStartBinding.land;
//        开启帧动画
        BirdImpl(bird).apply {
            fly();
            flaot();
        }
//        地面滚动
        LandImpl(land).startAnima();
        fragmentStartBinding.start.setOnClickListener{
            val navController = this.findNavController();
            navController.navigate(R.id.action_startFragment_to_gameFragment);
        }
        return fragmentStartBinding.root;
    }
}