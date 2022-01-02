package com.example.flappybrid

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.flappybrid.databinding.FragmentGameBinding
import com.example.flappybrid.logic.CollisionController
import com.example.flappybrid.logic.CounterController
import com.example.flappybrid.logic.GravityContrller
import com.example.flappybrid.logic.model.CountViewModel
import com.example.flappybrid.ui.bird.BirdImpl
import com.example.flappybrid.ui.gravity.GravityImpl
import com.example.flappybrid.ui.land.LandImpl
import com.example.flappybrid.ui.pipes.PipesImpl

class GameFragment : Fragment() {

    private val TAG = "GamesActivity";
    private var pipesCrossed = -2;
    lateinit var fragmentGamesBinding: FragmentGameBinding
    lateinit var gravityContrller: GravityContrller;
    var firstClick = true;
    val count by activityViewModels<CountViewModel>();
    val updateCounter = 1;
    //    在主线程中更新counter
    var handler = object : Handler(Looper.myLooper()!!)
    {
        override fun handleMessage(msg: Message)
        {
            when(msg.what)
            {
                updateCounter->count.setLiveData(pipesCrossed);
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentGamesBinding = FragmentGameBinding.inflate(layoutInflater);
        this.init();
//        计数器观察者
        count.liveData.observe(requireActivity(), Observer {
            CounterController(requireContext(),fragmentGamesBinding.counter,pipesCrossed).onChangeCounter();
        })
//        点击屏幕小鸟跳跃
        fragmentGamesBinding.screen.setOnClickListener{
//            如果小鸟在屏幕之中
            if(fragmentGamesBinding.bird.y>0)
            {
                gravityContrller.startJump();
            }
//            判断是否为第一次点击
            if(firstClick)
            {
//               获取水管对象
                val pipes = arrayOf(fragmentGamesBinding.pipe1, fragmentGamesBinding.pipe2);
//                水管开始移动
                PipesImpl(requireContext(), pipes).startAnima();
                val collisionController = CollisionController(pipes, fragmentGamesBinding.bird);
//               当小鸟与水管重合监听
                collisionController.setOnCollisionListener{
                    fragmentGamesBinding.screen.setOnClickListener(null);
                };
//               当水管跨越小鸟监听
                collisionController.setBirdThroughPipesListener(requireContext()) {
                    if(++pipesCrossed>0)
                    {
                        val msg = Message();
                        msg.what = updateCounter;
//                    将消息转发到主线程中
                        handler.sendMessage(msg)
                    }
                }
//                隐藏提示
                fragmentGamesBinding.startText.visibility = View.INVISIBLE;
                fragmentGamesBinding.hint.visibility = View.INVISIBLE;
                firstClick = false;
            }
        }
        return fragmentGamesBinding.root;
    }

    private fun init()
    {
        val birdGravity = GravityImpl(requireContext(), fragmentGamesBinding.bird);
//        开启地心引力
        birdGravity.startAnima();
//        开启地图底端滚动
        LandImpl(fragmentGamesBinding.land).startAnima();
//        创建小鸟
        val birdImpl = BirdImpl(fragmentGamesBinding.bird);
//        小鸟飞行
        birdImpl.fly();
//        小鸟跳跃控制者
        gravityContrller = GravityContrller(birdImpl, birdGravity);
    }
}