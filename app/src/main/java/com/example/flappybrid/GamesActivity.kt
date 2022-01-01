package com.example.flappybrid

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.flappybrid.databinding.ActivityGamesBinding
import com.example.flappybrid.logic.CollisionController
import com.example.flappybrid.logic.CounterController
import com.example.flappybrid.logic.GravityContrller
import com.example.flappybrid.logic.model.CountViewModel
import com.example.flappybrid.ui.bird.BirdImpl
import com.example.flappybrid.ui.gravity.GravityImpl
import com.example.flappybrid.ui.land.LandImpl
import com.example.flappybrid.ui.pipes.PipesImpl

class GamesActivity : AppCompatActivity()
{
    private val TAG = "GamesActivity";
    private var pipesCrossed = 0;
    lateinit var activityGames: ActivityGamesBinding
    lateinit var gravityContrller: GravityContrller;
    var firstClick = true;
    val count by viewModels<CountViewModel>();
    val updateCounter = 1;
    var handler = object : Handler(Looper.myLooper()!!)
    {
        override fun handleMessage(msg: Message)
        {
            when(msg.what)
            {
                updateCounter->count.setLiveData(++pipesCrossed);
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        activityGames = ActivityGamesBinding.inflate(layoutInflater);
        setContentView(activityGames.root);
        this.init();
//        计数器观察者
        count.liveData.observe(this, Observer {
            CounterController(this,activityGames.counter,pipesCrossed).onChangeCounter();
        })
//        点击屏幕小鸟跳跃
        activityGames.screen.setOnClickListener{
//            如果小鸟在屏幕之中
            if(activityGames.bird.y>0)
            {
                gravityContrller.startJump();
            }
//            判断是否为第一次点击
            if(firstClick)
            {
//               获取水管对象
                val pipes = arrayOf(activityGames.pipe1, activityGames.pipe2);
//                水管开始移动
                PipesImpl(this, pipes).startAnima();
                val collisionController = CollisionController(pipes, activityGames.bird);
//               当小鸟与水管重合监听
                collisionController.setOnCollisionListener{
                    activityGames.screen.setOnClickListener(null);
                };
//               当水管跨越小鸟监听
                collisionController.setBirdThroughPipesListener(this) {
                    val msg = Message();
                    msg.what = updateCounter;
                    handler.sendMessage(msg)
                }
//                隐藏提示
                activityGames.startText.visibility = View.INVISIBLE;
                activityGames.hint.visibility = View.INVISIBLE;
                firstClick = false;
            }
        }
    }

    private fun init()
    {
        val birdGravity = GravityImpl(this, activityGames.bird);
//        开启地心引力
        birdGravity.startAnima();
//        开启地图底端滚动
        LandImpl(activityGames.land).startAnima();
//        创建小鸟
        val birdImpl = BirdImpl(activityGames.bird);
//        小鸟飞行
        birdImpl.fly();
//        小鸟跳跃控制者
        gravityContrller = GravityContrller(birdImpl, birdGravity);
    }
}