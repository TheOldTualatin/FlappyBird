package com.example.flappybrid.logic

import android.content.Context
import android.view.LayoutInflater
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
        if(numbers.size>2)
        {
            for (num in numbers)
            {
                addContextCounter(num);
            }
        }else if(number>0)
        {
            val numberScore = view.findViewById<ImageView>(R.id.numberScore);
            numberScore.setImageResource(CounterParameter.numberScore[number]);
        }
    }
    fun addContextCounter(num:Int)
    {
        view.removeAllViews();
        val numView = LayoutInflater.from(context).inflate(R.layout.counter_num,view,false) as ImageView;
        numView.setImageResource(CounterParameter.numberContext[num]);
        view.addView(numView);
    }
}