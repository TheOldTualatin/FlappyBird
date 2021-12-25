package com.example.flappybrid.impl

import com.example.flappybird.impl.BirdImpl
import com.example.flappybird.impl.GravityImpl

/**
@author YangQX   2021/12/22 - 12:36
 */
class GravityContrller(val bird:BirdImpl,val gravity: GravityImpl)
{
    fun startJump()
    {
//        停止引力
        gravity.stopAnima();
//        开始跳跃
        bird.startAnima();
//        跳跃结束开启引力
        gravity.startAnima(100);
    }
}