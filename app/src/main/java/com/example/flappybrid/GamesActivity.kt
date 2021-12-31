package com.example.flappybrid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flappybrid.databinding.ActivityGamesBinding
import com.example.flappybrid.logic.CollisionController
import com.example.flappybrid.logic.GravityContrller
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
    var flage = true;
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        activityGames = ActivityGamesBinding.inflate(layoutInflater);
        setContentView(activityGames.root);
//        初始化
        this.init();
//        小鸟跳跃监听事件
        activityGames.screen.setOnClickListener{
            if(activityGames.bird.y>0)
            {
                gravityContrller.startJump();
            }
//            判断是否为第一次点击
            if(flage)
            {
                //        水管移动
                val pipes = arrayOf(activityGames.pipe1, activityGames.pipe2);
                PipesImpl(this, pipes).startAnima();
                val collisionController = CollisionController(pipes, activityGames.bird)
                collisionController.setBirdThroughPipesListener(this) {
                    Log.d("BirdThroughPipes", "onResume: ${++pipesCrossed}");
                }
//        当小鸟与水管重合监听
                collisionController.setOnCollisionListener{
                    activityGames.screen.setOnClickListener(null);
                };
//        当水管跨越小鸟监听
                activityGames.touch.visibility = View.INVISIBLE;
                flage = false;
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