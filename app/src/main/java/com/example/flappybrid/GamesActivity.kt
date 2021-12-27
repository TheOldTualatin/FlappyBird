package com.example.flappybrid

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.flappybird.impl.BirdImpl
import com.example.flappybird.impl.GravityImpl
import com.example.flappybrid.databinding.ActivityGamesBinding
import com.example.flappybrid.entity.Bird
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
        activityGames.test.setOnClickListener()
        {
            gravityContrller.startJump();
        }
    }

    private fun init()
    {
        val birdGravity = GravityImpl(activityGames.bird);
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
//        获取当前屏幕宽高
        val dm = resources.displayMetrics;
        val screenWidth = dm.widthPixels;
        val screenHeight = dm.heightPixels;
        Log.d("GamesActivity", "init: ${screenWidth},${screenHeight}");
//        水管移动
        val pipes = arrayOf(activityGames.pipe1,activityGames.pipe2);
        PipesImpl(this,pipes).startAnima();
    }
}