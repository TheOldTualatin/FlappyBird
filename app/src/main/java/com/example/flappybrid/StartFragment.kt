package com.example.flappybrid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flappybrid.databinding.FragmentStartBinding

class StartFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        val fragmentStartBinding = FragmentStartBinding.inflate(layoutInflater);
        fragmentStartBinding.start.setOnClickListener{
            val navController = this.findNavController();
            navController.navigate(R.id.action_startFragment_to_gameFragment);
        }
        return fragmentStartBinding.root;
    }
}