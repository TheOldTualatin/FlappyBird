package com.example.flappybrid.logic.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
@author YangQX   2022/1/1 - 9:54
 */
class CountViewModel : ViewModel()
{
    private val number = MutableLiveData<Int>();
    public val liveData get():LiveData<Int> = this.number;
    public fun setLiveData(value: Int)
    {
        this.number.value = value;
    }
}