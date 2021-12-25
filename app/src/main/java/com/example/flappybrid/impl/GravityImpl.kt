package com.example.flappybird.impl

import android.animation.ObjectAnimator
import android.os.HandlerThread
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.core.graphics.rotationMatrix
import com.example.flappybird.dao.MyAnimation
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.round

/**
@author YangQX   2021/12/21 - 10:47
 */
class GravityImpl(val bird: ImageView?) : MyAnimation
{
    //    定义定时器
    val timer: Timer = Timer("BIRD_TIMER");
    lateinit var translationY: ObjectAnimator;
    lateinit var rotationX: ObjectAnimator;

    //   重力牵引
    override fun startAnima(late: Long)
    {
//        //        低头
        rotationX = ObjectAnimator.ofFloat(bird,"rotation",-40f);
        rotationX.apply {
            duration = 350;
            start();
        }
        translationY = ObjectAnimator.ofFloat(bird, "translationY", 850f);
        translationY.apply {
            duration = 500
            interpolator = AccelerateInterpolator(1.2f);
            startDelay = late
            start()
        }
    }

    //    小鸟结束跳跃
    override fun stopAnima()
    {
        translationY.cancel();
        rotationX.cancel();
        val x = bird?.x
        val y = bird?.y
        Log.d("Gravity", "x:${x},y:${y}");
    }

}