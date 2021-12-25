package com.example.flappybird.dao

/**
@author YangQX   2021/12/21 - 10:40
    定义动画类 --> 提供停止和开始
 */
interface MyAnimation
{
    fun startAnima(late:Long=0);
    fun stopAnima();
}