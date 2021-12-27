package com.example.flappybrid.impl

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.flappybird.dao.MyAnimation
import com.example.flappybrid.R
import com.example.flappybrid.utills.getGap

/**
@author YangQX   2021/12/23 - 10:42
 */
class PipesImpl(val context: Context,val pipes:Array<RelativeLayout>):MyAnimation
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
                    if(pipe.id == R.id.pipe1)
                    {
                        val topPipe1 = pipe.findViewById<ImageView>(R.id.topPipe1);
                        val bottomPipe1 = pipe.findViewById<ImageView>(R.id.bottomPipe1);
                        setPipesHeight(topPipe1,bottomPipe1);
                    }else
                    {
                        val topPipe2 = pipe.findViewById<ImageView>(R.id.topPipe2);
                        val bottomPipe2 = pipe.findViewById<ImageView>(R.id.bottonPipe2);
                        setPipesHeight(topPipe2,bottomPipe2);
                    }
//                    递归开始循环
                    move(pipe,0);
                }
            })
            interpolator = LinearInterpolator();
            withLayer();
            start();
        }
    }

    fun setPipesHeight(topPipe:ImageView,bottomPipe:ImageView)
    {
        val scale = context.getResources().getDisplayMetrics().density;
        val gap:Array<Int> = getGap()
        var layoutParams1 = topPipe.layoutParams;
        layoutParams1.height = (gap[0]* scale + 0.5f).toInt();
        topPipe.layoutParams = layoutParams1;
        val layoutParams2 = bottomPipe.layoutParams;
        layoutParams2.height = (gap[1]* scale + 0.5f).toInt();
        bottomPipe.layoutParams = layoutParams2;
    }

    override fun stopAnima()
    {
        TODO("Not yet implemented")
    }
}