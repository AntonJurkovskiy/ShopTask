package com.azimut4946777.shoptask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azimut4946777.shoptask.databinding.FragmentAddTaskBinding
import com.azimut4946777.shoptask.databinding.FragmentEditTaskBinding


class EditTaskFragment : Fragment() {
private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(layoutInflater,container,false)
        //TODO()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}