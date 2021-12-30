package com.example.flappybrid.impl

import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.flappybrid.R
import com.example.flappybrid.utills.getParentHeight
import java.util.*

/**
@author YangQX   2021/12/30 - 10:32
 */
class CollisionController(val pipes:Array<RelativeLayout>,val bird:ImageView)
{
    private val TAG = "CollisionController";
    private lateinit var topPipe:ImageView;
    private lateinit var bottomPipe:ImageView;
    public fun setOnCollision(onCollision:CollisionController.() -> Unit)
    {
        val timer = Timer()
//        开启定时器任务，检测小鸟是否处于间隙
        timer.schedule(object : TimerTask()
       {
           override fun run()
           {
//               判断水管位置是否与小鸟位置重合
//               鸟头
               if(isCollision(pipes[0]))
               {
                   topPipe = pipes[0].findViewById(R.id.topPipe1);
                   bottomPipe = pipes[0].findViewById(R.id.bottomPipe1);
                   if(!isInGap(topPipe,bottomPipe))
                   {
                       timer.cancel();
                       onCollision()
                   }
               }else if(isCollision(pipes[1]))
               {
                   topPipe = pipes[1].findViewById(R.id.topPipe2);
                   bottomPipe = pipes[1].findViewById(R.id.bottonPipe2);
                   if(!isInGap(topPipe,bottomPipe))
                   {
                       timer.cancel();
                       onCollision()
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
        if(pipesStart<birdStart && pipesEnd > birdEnd)
        {
            Log.d(TAG, "isCollision: birdStart:${birdStart},birdEnd:${birdEnd},pipesStart:${pipesStart},pipesEnd:${pipesEnd}");
        }
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
        val birdStart = bird.y;
        val birdEnd = birdStart+bird.height-10;
        val topY = topHeight;
        val bottomY = parentHeight - bottomHeight;
        if(!(birdStart > topY && birdEnd < bottomY))
        {
            Log.d(TAG, "isInGap: birdY:${birdStart},topY:${topY},bottomY:${bottomY}");
        }
        return birdStart > topY && birdEnd < bottomY;
    }
}