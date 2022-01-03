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
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import com.example.flappybrid.databinding.FragmentGameBinding
import com.example.flappybrid.ui.controller.CollisionController
import com.example.flappybrid.ui.controller.CounterController
import com.example.flappybrid.ui.controller.GravityContrller
import com.example.flappybrid.logic.model.CountViewModel
import com.example.flappybrid.ui.bird.BirdImpl
import com.example.flappybrid.ui.gravity.GravityImpl
import com.example.flappybrid.ui.land.LandImpl
import com.example.flappybrid.ui.pipes.PipesImpl

class GameFragment : Fragment(), View.OnClickListener
{

    private var pipesCrossed = -2;
    var firstClick = true;
    val count by activityViewModels<CountViewModel>();
    val updateCounter = 1;
    val gameOver = 2;

    //    在主线程中更新counter
    var handler = object : Handler(Looper.myLooper()!!)
    {
        override fun handleMessage(msg: Message)
        {
            when (msg.what)
            {
                updateCounter -> count.setLiveData(pipesCrossed);
                gameOver -> gameOver();
            }
        }
    }

    lateinit var birdImpl: BirdImpl;
    lateinit var gravityContrller: GravityContrller;
    lateinit var gravityImpl: GravityImpl;
    lateinit var fragmentGamesBinding: FragmentGameBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        fragmentGamesBinding = FragmentGameBinding.inflate(layoutInflater);
        this.init();
//        计数器观察者
        count.liveData.observe(requireActivity(), Observer {
            CounterController(requireContext(), fragmentGamesBinding.counter, pipesCrossed).onChangeCounter();
        })
//        点击屏幕小鸟跳跃
        fragmentGamesBinding.screen.setOnClickListener(this);
        return fragmentGamesBinding.root;
    }

    private fun init()
    {

//        开启地图底端滚动
        LandImpl(fragmentGamesBinding.land).startAnima();
//        创建小鸟
        birdImpl = BirdImpl(fragmentGamesBinding.bird);
//        小鸟飞行
        birdImpl.fly();
    }

    override fun onClick(v: View?)
    {
        when (v?.id)
        {
            R.id.screen ->
            {
                onClickScreen()
            }
        }
    }

    private fun onClickScreen()
    {
//        判断是否为第一次点击,如果为第一次点击，开始执行碰
        if (firstClick)
        {
            firstClick = gameGrow();
        }
        //            如果小鸟在屏幕之中
        if (fragmentGamesBinding.bird.y > 0)
        {
//           控制小鸟跳跃
            gravityContrller = GravityContrller(birdImpl, gravityImpl);
            gravityContrller.startJump();
        }
    }

    private fun gameGrow(): Boolean
    {
//        对小鸟的地心引力
        gravityImpl = GravityImpl(requireContext(), fragmentGamesBinding.bird);
//        开启地心引力
        gravityImpl.startAnima();
        val pipes = arrayOf(fragmentGamesBinding.pipe1, fragmentGamesBinding.pipe2);
//        水管开始移动
        PipesImpl(requireContext(), pipes).startAnima();
        val collisionController = CollisionController(pipes, fragmentGamesBinding.bird);
//         当小鸟与水管重合监听
        collisionController.setOnCollisionListener {
            fragmentGamesBinding.screen.setOnClickListener(null);
//            隐藏水管组
            fragmentGamesBinding.pipe1.visibility = View.INVISIBLE;
            fragmentGamesBinding.pipe2.visibility = View.INVISIBLE;
            val msg = Message();
            msg.what = gameOver;
            handler.sendMessage(msg);
        };
//         当水管跨越小鸟监听
        collisionController.setBirdThroughPipesListener(requireContext()) {
            if (++pipesCrossed > 0)
            {
                val msg = Message();
                msg.what = updateCounter;
//               将消息转发到主线程中
                handler.sendMessage(msg)
            }
        }
        this.hintHidden();
        return false;
    }

    private fun hintHidden()
    {
//       隐藏提示
        fragmentGamesBinding.startText.visibility = View.GONE;
        fragmentGamesBinding.hint.visibility = View.GONE;
    }

    fun gameOver()
    {
        fragmentGamesBinding.score.text = pipesCrossed.toString();
        fragmentGamesBinding.gameOver.visibility = View.VISIBLE;
        fragmentGamesBinding.restart.setOnClickListener{
            parentFragmentManager.commit {
                replace<GameFragment>(R.id.fragmentContainerView);
            }
        }
    }

}