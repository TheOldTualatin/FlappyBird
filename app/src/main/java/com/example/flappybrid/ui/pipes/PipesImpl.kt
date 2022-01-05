package com.example.flappybrid.ui.pipes

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.flappybrid.ui.animation.MyAnimation
import com.example.flappybrid.R
import com.example.flappybrid.utills.getGap
import com.example.flappybrid.utills.getParentHeight

/**
@author YangQX   2021/12/23 - 10:42
 */
class PipesImpl(val context: Context,val pipes:Array<RelativeLayout>): MyAnimation
{
//   collision
//    当前屏幕宽高
    private var screenWidth:Int;
    private var screenHeight:Int;
//    移动一整个屏幕的时间
    private var currentTime:Long;
    init
    {
        //    获取当前屏幕宽高
        val dm = context.resources.displayMetrics;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        currentTime = (screenWidth/PipesParameter.baseSpeed).toLong();
    }
//    每一次动画只执行一遍，然后回到原位
    override fun startAnima(late: Long)
    {
        val lateList = arrayOf<Long>(0,currentTime/2);
            for(i in pipes.indices)
            {
                this.move(pipes[i],lateList[i]);
            }
    }

    override fun stopAnima()
    {
        TODO("Not yet implemented")
    }

    override fun pause() {
    }

    /**
     * 为了保证自适应，必须按照不同屏幕尺寸进行适配,移动距离根据屏幕宽度决定
     */
    fun move(pipe:RelativeLayout,late: Long)
    {
        Log.d("GamesActivity", "init: ${screenWidth},${screenHeight}");
//        保存移动之前的位置
        pipe.animate().apply{
            translationXBy(-screenWidth-2*50f);
            duration = currentTime;
            startDelay = late;
//            动画取消时监听
            setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?)
                {
//                    重置当前位置
                    pipe.x=screenWidth.toFloat();
//                    判断水管组合
                    if(pipe.id == R.id.pipe1)
                    {
                        val topPipe1 = pipe.findViewById<ImageView>(R.id.topPipe1);
                        val bottomPipe1 = pipe.findViewById<ImageView>(R.id.bottomPipe1);
//                        随机水管高度
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
//            线性动画
            interpolator = LinearInterpolator();
            withLayer();
            start();
        }
    }

    /**
     * 设置水管高度
     */
    private fun setPipesHeight(topPipe:ImageView,bottomPipe:ImageView)
    {
        val parentHeight = topPipe.getParentHeight();
//        获取随机值
        val gap:Array<Int> = getGap(parentHeight);
        val layoutParams1 = topPipe.layoutParams;
        layoutParams1.height = gap[0];
        topPipe.layoutParams = layoutParams1;
        val layoutParams2 = bottomPipe.layoutParams;
        layoutParams2.height = gap[1];
        bottomPipe.layoutParams = layoutParams2;
    }
}