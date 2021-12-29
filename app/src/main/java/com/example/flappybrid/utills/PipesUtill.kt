package com.example.flappybrid.utills

import android.view.View
import android.view.ViewGroup
import java.util.*

/**
@author YangQX   2021/12/27 - 16:27
 */
/**
 * 水管随机高度,根据百分比自适应
 */
fun getGap(parentHeight:Int):Array<Int>
{
        val random = Random();
//        间隙占总高度30%
        val gap = parentHeight*0.3;
        val sum = parentHeight - gap;
//        顶部水管高度
        val topHeight = random.nextInt(sum.toInt());
//        底部水管高度
        val bottomHeight = (sum - topHeight).toInt();
        return arrayOf(topHeight,bottomHeight);
}
fun<T : View> getParentHeight(child:T): Int
{
        val parent = child.parent as ViewGroup;
        return parent.height;
}