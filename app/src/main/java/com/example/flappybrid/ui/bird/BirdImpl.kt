package com.example.flappybrid.ui.bird

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import com.example.flappybrid.R
import com.example.flappybrid.ui.animation.MyAnimation
import java.util.*


/**
@author YangQX   2021/12/21 - 10:43
 */
class BirdImpl(val bird: ImageView?) : MyAnimation
{
    fun fly()
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

    fun flaot()
    {
        val down = ObjectAnimator.ofFloat(bird,"translationY",0f,50f);
        val up = ObjectAnimator.ofFloat(bird,"translationY",50f,0f);
        val animatorSet = AnimatorSet().apply {
            duration = 1000;
            play(down).before(up);
            start();
        };
        animatorSet.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?)
            {
                animatorSet.start();
            }
        })
    }

    //    小鸟结束跳跃
    override fun stopAnima()
    {
        bird?.clearAnimation();
    }
}