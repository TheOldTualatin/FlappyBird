package com.example.flappybird.impl

import android.graphics.drawable.AnimationDrawable
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import com.example.flappybird.dao.MyAnimation
import com.example.flappybrid.R
import java.util.*


/**
@author YangQX   2021/12/21 - 10:43
 */
class BirdImpl(val bird: ImageView?) : MyAnimation
{
    init
    {
//        小鸟煽动翅膀帧动画
        bird?.setImageResource(R.drawable.birdanimalist);
        val animationDrawable = bird?.drawable as AnimationDrawable;
        animationDrawable.isOneShot = false;
        animationDrawable.start();
    }

    //    定义定时器
    val timer: Timer = Timer("BIRD_TIMER");

    //    小鸟开始跳跃
    override fun startAnima(late: Long)
    {
//        如果小鸟没有超出屏幕就执行动画
        if(isOverFlow())
        {
            bird?.animate()?.cancel();
        }else
        {
            bird?.let {
                it.animate().apply {
                    translationYBy(-200f);
                    rotation(60f);
                    duration = 500;
                    startDelay = late;
                    interpolator = AccelerateInterpolator(0.2f);
                    withLayer();
                    start();
                }
            }
        }
    }

    private fun isOverFlow(): Boolean
    {
//        判断小鸟坐标
        val y = bird!!.y;
        return y < 20;
    }

    //    小鸟结束跳跃
    override fun stopAnima()
    {
        bird?.clearAnimation();
    }
}