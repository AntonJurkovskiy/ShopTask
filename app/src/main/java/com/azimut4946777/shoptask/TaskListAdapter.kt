package com.azimut4946777.shoptask

import android.content.ClipData
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azimut4946777.shoptask.data.Task
import com.azimut4946777.shoptask.databinding.FragmentEditTaskBinding
import com.azimut4946777.shoptask.databinding.ListItemBinding

class TaskListAdapter(private val onItemClicked: (Task) -> Unit) :
    ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val viewHolder = TaskViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class TaskViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                tvTaskName.text = task.taskName
                tvTaskQty.text = task.taskQuantity.toString()
                //taskIsDone.isChecked = false
                taskIsDone.setOnCheckedChangeListener{ buttonView, isChecked ->
                    if (isChecked){
                      taskIsDone.scaleX=2F
                        taskIsDone.scaleY=2F
                    }else{
                        taskIsDone.scaleX=1F
                        taskIsDone.scaleY=1F

                    }
                }

                }
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldTask: Task, newTask: Task): Boolean {
                return oldTask.id === newTask.id
            }

            override fun areContentsTheSame(oldTask: Task, newTask: Task): Boolean {
                return oldTask.taskName == newTask.taskName
            }
        }
    }
}