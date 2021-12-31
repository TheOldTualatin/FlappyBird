package com.example.flappybrid.ui.pipes

/**
@author YangQX   2021/12/31 - 21:56
 */
object PipesParameter
{
    val baseWidth: Int = 1080;
    val baseTime: Int = 4000;
//    以1080像素为基础单位算出每px的时间
    val baseSpeed: Float = baseWidth/baseTime.toFloat();
}
