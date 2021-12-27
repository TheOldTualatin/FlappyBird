package com.example.flappybrid.impl

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import com.example.flappybird.dao.MyAnimation

/**
@author YangQX   2021/12/23 - 10:42
 */
class PipesImpl(val pipes:Array<RelativeLayout>):MyAnimation
{
//    每一次动画只执行一遍，然后回到原位
    override fun startAnima(late: Long)
    {
        val lateList = arrayOf<Long>(0,2000);
            for(i in pipes.indices)
            {
                Log.d("PipesImpl", "startAnima: ${pipes[i].x},${pipes[i].y}");
                this.move(pipes[i],lateList[i]);
            }
    }
    fun move(pipe:RelativeLayout,late: Long)
    {
//        保存移动之前的位置
        pipe.animate().apply{
            translationXBy(-1080f-2*50f);
            duration = 4000;
            startDelay = late;
//                    设置动画取消监听
            setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?)
                {
//                    重置当前位置
                    pipe.x=1080f;
//                    递归开始循环
                    move(pipe,0);
                }
            })
            interpolator = LinearInterpolator();
            withLayer();
            start();
        }
    }

    override fun stopAnima()
    {
        TODO("Not yet implemented")
    }
}