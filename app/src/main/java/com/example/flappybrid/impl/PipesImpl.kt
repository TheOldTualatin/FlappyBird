package com.example.flappybrid.impl

import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.RelativeLayout
import com.example.flappybird.dao.MyAnimation

/**
@author YangQX   2021/12/23 - 10:42
 */
class PipesImpl(val pipes:RelativeLayout):MyAnimation
{
    override fun startAnima(late: Long)
    {
        val animationSet = AnimationSet(true)
        // 创建一个RotateAnimation对象（从某个点移动到另一个点）
        // 创建一个RotateAnimation对象（从某个点移动到另一个点）
        val translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            -10f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.0f
        )
        // 设置动画执行的时间（单位：毫秒）
        // 设置动画执行的时间（单位：毫秒）
        translateAnimation.duration = 5000;
        translateAnimation.repeatCount = 20;
        translateAnimation.interpolator = LinearInterpolator();
        // 将TranslateAnimation对象添加到AnimationSet当中
        animationSet.addAnimation(translateAnimation)
        // 使用ImageView的startAnimation方法开始执行动画
        pipes.startAnimation(animationSet)
    }

    override fun stopAnima()
    {
        TODO("Not yet implemented")
    }
}