package com.example.flappybrid.utills

import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList

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
        val gap = parentHeight*0.2;
        val sum = parentHeight - gap;
//        顶部水管高度
        val topHeight = random.nextInt(sum.toInt());
//        底部水管高度
        val bottomHeight = (sum - topHeight).toInt();
        return arrayOf(topHeight,bottomHeight);
}

fun View.getParentHeight(): Int
{
    val parent = this.parent as ViewGroup;
    return parent.height;
}

//接收一个Int返回一个数组
fun counter(value:Int): ArrayList<Int>
{
    var value = value;
    val numbs = arrayListOf<Int>();
//    每次余出一位
    while(value>0)
    {
        var num = value%10;
        value /=10;
        numbs.add(num);
    }
//    反转数组
    numbs.reverse();
    return numbs;
}