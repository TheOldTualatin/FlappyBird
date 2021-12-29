package com.example.flappybird.impl

import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import com.example.flappybird.dao.MyAnimation
import com.example.flappybrid.utills.getParentHeight

/**
@author YangQX   2021/12/21 - 10:47
 */
class GravityImpl(val context: Context,val bird: ImageView) : MyAnimation
{
    // 定义定时器
    lateinit var translationY: ObjectAnimator;
    lateinit var rotationX: ObjectAnimator;

    // 重力牵引
    override fun startAnima(late: Long)
    {
        // 低头
        rotationX = ObjectAnimator.ofFloat(bird,"rotation",-40f);
        rotationX.apply {
            duration = 350;
            start();
        }
        val parentHeight =  getParentHeight(bird);
//        小鸟从中心跌落，所以只要走一半的距离
        val fallHeight = (parentHeight/2).toFloat();
//        移动距离需与父布局高度一致
        translationY = ObjectAnimator.ofFloat(bird, "translationY", fallHeight);
        translationY.apply {
            duration = 500
            interpolator = AccelerateInterpolator(1.2f);
            startDelay = late
            start()
        }
    }

    /**
     * 获取父布局高度
     */


    override fun stopAnima()
    {
        translationY.cancel();
        rotationX.cancel();
        val x = bird.x
        val y = bird.y
        Log.d("Gravity", "x:${x},y:${y}");
    }

}