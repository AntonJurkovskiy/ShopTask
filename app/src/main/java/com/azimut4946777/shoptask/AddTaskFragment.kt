package com.azimut4946777.shoptask

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.azimut4946777.shoptask.data.Task
import com.azimut4946777.shoptask.databinding.FragmentAddTaskBinding


class AddTaskFragment : Fragment() {
    //Connecting to ViewModel
    private val viewModel: ShopTaskViewModel by activityViewModels() {
        ShopTaskViewModelFactory(
            (activity?.application as ShopTaskApplication).database.taskDao()
        )
    }
    lateinit var task: Task

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        // Save Button action
     binding.saveButton.setOnClickListener {
addNewTask()
     }


        return binding.root
    }
    // Chek input text
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.taskNameInput.text.toString(),
            binding.taskQtyInput.text.toString()
        )
    }
    // Add new Task
    private fun addNewTask(){
        if(isEntryValid()){
            viewModel.addNewTask(
                binding.taskNameInput.text.toString(),
                binding.taskQtyInput.text.toString(),
            )
            val action = AddTaskFragmentDirections.actionAddTaskFragmentToMainTaskFragment()
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}