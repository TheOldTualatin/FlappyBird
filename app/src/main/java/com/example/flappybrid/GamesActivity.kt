package com.example.flappybrid

import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        activityGames= ActivityGamesBinding.inflate(layoutInflater);
        setContentView(activityGames.root);
        val x = activityGames.bird.left
        val y = activityGames.bird.top
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
        val gravityContrller = GravityContrller(birdImpl, birdGravity)
        activityGames.test.setOnClickListener()
        {
            gravityContrller.startJump();
        }
//        水管移动
        PipesImpl(activityGames.pipe).startAnima();
    }
}