package com.example.mytodoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.databinding.ListItemTaskBinding
import com.example.mytodoapp.model.Task

class TaskAdapter(val onTaskCompleted: (Long) -> Unit) :
    ListAdapter<Task, RecyclerView.ViewHolder>(TaskDiffCallback()) {

    lateinit var binding : ListItemTaskBinding

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)
        (holder as TaskViewHolder).bind(task)

        binding.setCheckboxClickListener {
            val checked = (it as CheckBox).isChecked
            if (checked) {
                // if unchecked, remove from list
                onTaskCompleted(task.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ListItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }
}

class TaskViewHolder(private val binding: ListItemTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task) {
        binding.task = task
        binding.executePendingBindings()
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.desc == newItem.desc
    }
}