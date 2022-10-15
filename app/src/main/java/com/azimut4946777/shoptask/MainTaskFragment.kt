package com.azimut4946777.shoptask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.azimut4946777.shoptask.databinding.FragmentMainTaskBinding


class MainTaskFragment : Fragment() {
    private var _binding: FragmentMainTaskBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainTaskBinding.inflate(layoutInflater, container, false)
        //bottomNavigationView setting in progress
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.menu.getItem(3).isEnabled = false
        //FAB bottom action
        binding.fab.setOnClickListener {
            val action = MainTaskFragmentDirections.actionMainTaskFragmentToDetailTaskFragment()
            findNavController().navigate(action)
        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }
