package com.azimut4946777.shoptask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.azimut4946777.shoptask.data.Task
import com.azimut4946777.shoptask.databinding.FragmentAddTaskBinding
import com.azimut4946777.shoptask.databinding.FragmentEditTaskBinding


class EditTaskFragment : Fragment() {
//For navigation Argument
    private val navigationArgs: EditTaskFragmentArgs by navArgs()

    lateinit var task: Task

    //Connecting to ViewModel
    private val viewModel: ShopTaskViewModel by activityViewModels {
        ShopTaskViewModelFactory(
            (activity?.application as ShopTaskApplication).database.taskDao()
        )
    }

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(layoutInflater, container, false)
        //TODO()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
val id = navigationArgs.taskId
viewModel.retrieveTask(id).observe(this.viewLifecycleOwner) {selectedTask->
task = selectedTask
    bind(task)
    binding.saveButton.setOnClickListener {
        editTask()
    }
binding.deleteButton.setOnClickListener {
deleteTask()
}
}
    }

    // For delete Task
    private fun deleteTask() {
        viewModel.deleteTask(task)
        findNavController().navigateUp()
    }

// For edit Task
private fun editTask() {
viewModel.updateTask(taskId =task.id, taskName = binding.taskNameInput.text.toString(), taskQty = binding.taskQtyInput.text.toString())
    findNavController().navigateUp()
}
    private fun bind(task: Task) {
        binding.apply {
            taskNameInput.setText(task.taskName, TextView.BufferType.SPANNABLE)
            taskQtyInput.setText(task.taskQuantity.toString(), TextView.BufferType.SPANNABLE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}