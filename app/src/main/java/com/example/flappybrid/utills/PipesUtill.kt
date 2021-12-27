package com.example.flappybrid.utills

import java.util.*

/**
@author YangQX   2021/12/27 - 16:27
 */
fun getGap():Array<Int>
{
        val random = Random();
        val topPipe = random.nextInt(500);
        val bottomPipe = 520-topPipe-10;
//        返回水管顶部和底部的高度
        return arrayOf(topPipe,bottomPipe);
}