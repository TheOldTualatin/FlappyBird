package com.example.flappybrid.ui.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.flappybrid.R
import com.example.flappybrid.ui.counter.CounterParameter
import com.example.flappybrid.utills.counter

/**
@author YangQX   2022/1/1 - 11:25
 */
class CounterController(val context: Context,val view: LinearLayout,val number:Int)
{
    fun onChangeCounter()
    {
        val numbers:ArrayList<Int> = counter(number);
        view.removeAllViews();
        for (num in numbers)
        {
            addContextCounter(num);
        }
    }
    fun addContextCounter(num:Int)
    {
        val numView = LayoutInflater.from(context).inflate(R.layout.counter_num,null) as ImageView;
        numView.setImageResource(CounterParameter.numberContext[num]);
        numView.scaleType = ImageView.ScaleType.CENTER_CROP;
//        以编程方式创建的view不具有任何参数,手动添加参数
        val layoutParams = ViewGroup.LayoutParams(40,40);
        layoutParams.height = 40;
        layoutParams.width = 40;
        numView.layoutParams = layoutParams;
        view.addView(numView);
    }
}