package com.azimut4946777.shoptask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azimut4946777.shoptask.databinding.FragmentDetailTaskBinding


class DetailTaskFragment : Fragment() {
 private var _binding: FragmentDetailTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailTaskBinding.inflate(layoutInflater,container,false)
        //TODO()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}