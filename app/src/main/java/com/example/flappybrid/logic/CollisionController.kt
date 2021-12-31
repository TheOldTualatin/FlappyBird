package com.example.flappybrid.logic

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.flappybrid.R
import com.example.flappybrid.ui.pipes.PipesParameter
import com.example.flappybrid.utills.getParentHeight
import java.util.*
import kotlin.math.log

/**
@author YangQX   2021/12/30 - 10:32
 */
class CollisionController(val pipes:Array<RelativeLayout>,val bird:ImageView)
{
    private val TAG = "CollisionController";
    private lateinit var topPipe:ImageView;
    private lateinit var bottomPipe:ImageView;

    /**
     * @param onCollision 水管和鸟相遇的回调
     * @param flag 碰撞则返回fales
     */
    public fun setOnCollisionListener(onCollision: () -> Unit)
    {
        val timer = Timer()
//        开启定时器任务，检测小鸟是否处于间隙
        timer.schedule(object : TimerTask()
       {
           override fun run()
           {
//               判断水管位置是否与小鸟位置重合
               if(isCollision(pipes[0]))
               {
                   topPipe = pipes[0].findViewById(R.id.topPipe1);
                   bottomPipe = pipes[0].findViewById(R.id.bottomPipe1);
//                  调用回调事件
                   if(!isInGap(topPipe,bottomPipe))
                   {
                       onCollision();
                   }
               }else if(isCollision(pipes[1]))
               {
                   topPipe = pipes[1].findViewById(R.id.topPipe2);
                   bottomPipe = pipes[1].findViewById(R.id.bottonPipe2);
                   var flage = true;
                   if(!isInGap(topPipe,bottomPipe))
                   {
                       onCollision();
                   }
               }
           }
       },0,1);
    }

    /**
     * 判断水管和小鸟是否重合
     */
    fun isCollision(pipe:RelativeLayout):Boolean
    {
        val birdStart = bird.x+bird.width;
        val birdEnd = bird.x;
        val pipesStart = pipe.x;
        val pipesEnd = pipe.width+pipesStart;
        return pipesStart<birdStart && pipesEnd > birdEnd
    }

    /**
     * 当小鸟和水管重合时，判断小鸟是否在空隙中
     */
    fun isInGap(topPipe:ImageView,bottom:ImageView):Boolean
    {
        val topHeight = topPipe.height;
        val bottomHeight = bottom.height;
        val parentHeight = topPipe.getParentHeight();
        val birdStart = bird.y+bird.height;
        val birdEnd = birdStart-bird.height;
        val topY = topHeight;
        val bottomY = parentHeight - bottomHeight;
        return birdStart > topY && birdEnd < bottomY;
    }

//    判断小鸟是否穿过水管
    fun setBirdThroughPipesListener(context: Context,onThrough: () -> Unit):Boolean
    {
        val timer = Timer();
        val dm = context.resources.displayMetrics;
        val screenWidth = dm.widthPixels;
//        得到水管从开始移动到离开小鸟的距离
        val pipeDistance = screenWidth - bird.x;
//        得到水管从开始移动到离开小鸟的时间段
        val period = (pipeDistance/PipesParameter.baseSpeed).toLong();
        Log.d("setBirdThroughPipesListener", "setBirdThroughPipesListener: +${pipeDistance}")
//        按照时间调用
        timer.schedule(object : TimerTask(){
            override fun run()
            {
                onThrough();
            }
        },period,period);
        return true;
    };
}