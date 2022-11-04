package com.example.mytodoapp.ui.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.AddTaskViewBinding
import com.example.mytodoapp.databinding.FragmentHomeBinding
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class TaskFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // TODO add showDialog in SavedState to survive orientation change
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onStart() {
        super.onStart()

        // change title and image
        activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = "Checklist"
        activity?.findViewById<ImageView>(R.id.image_view)
            ?.setImageResource(R.drawable.checklist_poster)

        // add task dialog
        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            context?.let { context ->
                val dialog = AddTaskDialog(context) { text ->
                    taskViewModel.addTask(text)
                }
                dialog.show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val taskListView: RecyclerView = binding.taskListView
        val adapter = TaskAdapter() {
            taskViewModel.removeTask(it)
        }
        taskListView.adapter = adapter

        // listen to data change
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                taskViewModel.currentTasks.collect {
                    adapter.submitList(it)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class AddTaskDialog(context: Context, val onAddTask: (String) -> Unit) : Dialog(context) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val dialogBinding = AddTaskViewBinding.inflate(LayoutInflater.from(context))
            dialogBinding.setAddTaskClickListener {
                val text = dialogBinding.textInput.text.toString()
                onAddTask(text)
                hide()
            }
            setContentView(dialogBinding.root)
        }
    }
}