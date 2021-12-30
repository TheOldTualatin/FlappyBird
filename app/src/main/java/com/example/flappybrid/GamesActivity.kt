package com.example.flappybrid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flappybird.impl.BirdImpl
import com.example.flappybird.impl.GravityImpl
import com.example.flappybrid.databinding.ActivityGamesBinding
import com.example.flappybrid.entity.Bird
import com.example.flappybrid.impl.CollisionController
import com.example.flappybrid.impl.GravityContrller
import com.example.flappybrid.impl.LandImpl
import com.example.flappybrid.impl.PipesImpl

class GamesActivity : AppCompatActivity()
{
    lateinit var  activityGames: ActivityGamesBinding
    lateinit var gravityContrller:GravityContrller;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        activityGames= ActivityGamesBinding.inflate(layoutInflater);
        setContentView(activityGames.root);
//        初始化
        this.init();
//        小鸟跳跃监听事件
        activityGames.screen.setOnClickListener()
        {
            gravityContrller.startJump();
        }
    }

//    当准备好交互时开始监听水管移动位置
    override fun onResume()
    {
        super.onResume();
        val pipes = arrayOf(activityGames.pipe1,activityGames.pipe2);
        CollisionController(pipes,activityGames.bird).setOnCollision(){
//            取消跳跃监听
            activityGames.screen.setOnClickListener(null);
        };
    }

    private fun init()
    {
        val birdGravity = GravityImpl(this,activityGames.bird);
//        小鸟飞行
        Bird(activityGames.bird).fly();
//        开启地心引力
        birdGravity.startAnima();
//        开启地图底端滚动
        LandImpl(activityGames.land).startAnima();
//        小鸟跳跃
        val birdImpl = BirdImpl(activityGames.bird);
//        小鸟跳跃控制者
        gravityContrller = GravityContrller(birdImpl, birdGravity);
//        水管移动
        val pipes = arrayOf(activityGames.pipe1,activityGames.pipe2);
        PipesImpl(this,pipes).startAnima();
    }
}