package com.example.flappybrid.ui.land

import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import com.example.flappybrid.ui.animation.MyAnimation

/**
@author YangQX   2021/12/23 - 9:29
 */
class LandImpl(val land:ImageView): MyAnimation
{
    override fun startAnima(late: Long)
    {
        val animationSet = AnimationSet(true)
        // 创建一个RotateAnimation对象（从某个点移动到另一个点）
        val translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            -0.06f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.0f
        )
        // 设置动画执行的时间（单位：毫秒）
        translateAnimation.duration = 180
        translateAnimation.repeatCount = Animation.INFINITE;
        // 将TranslateAnimation对象添加到AnimationSet当中
        animationSet.addAnimation(translateAnimation)
        // 使用ImageView的startAnimation方法开始执行动画
        land.startAnimation(animationSet)
    }

    override fun stopAnima()
    {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }
}