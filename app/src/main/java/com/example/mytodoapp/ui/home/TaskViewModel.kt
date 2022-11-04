package com.example.mytodoapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.mytodoapp.model.Task
import kotlinx.coroutines.flow.MutableStateFlow

class TaskViewModel : ViewModel() {
    val initialTasks = listOf(
        Task(1, "Do homework"),
        Task(2, "Mow the lawn"),
        Task(3, "Clean bathroom"),
        Task(4, "Cook dinner"),
        Task(5, "Pick up groceries")
    )

    var currentTasks = MutableStateFlow(initialTasks)

    fun addTask(text: String) {
        val newTasks = ArrayList<Task>(currentTasks.value)
        newTasks.add(Task((initialTasks.size + 1).toLong(), text))
        currentTasks.value = newTasks
    }

    fun removeTask(id: Long) {
        val newTasks = currentTasks.value.filter {
            it.id != id
        }
        currentTasks.value = newTasks
    }
}