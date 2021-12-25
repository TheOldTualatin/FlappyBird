package com.example.flappybrid.entity

import android.graphics.drawable.AnimationDrawable
import android.view.animation.Animation
import android.widget.ImageView
import com.example.flappybrid.R

/**
@author YangQX   2021/12/22 - 11:56
 */
class Bird(val bird:ImageView)
{
    fun fly()
    {
        bird.setImageResource(R.drawable.birdanimalist);
        val animationDrawable = bird.drawable as AnimationDrawable
        animationDrawable.isOneShot = false;
        animationDrawable.start();
    }
}