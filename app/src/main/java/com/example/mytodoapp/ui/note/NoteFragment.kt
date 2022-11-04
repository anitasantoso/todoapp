package com.example.mytodoapp.ui.note

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
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
import com.example.mytodoapp.databinding.FragmentNoteBinding
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel : NoteViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        // undo on onStop?

        // change title and image
        activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = "Note"
        activity?.findViewById<ImageView>(R.id.image_view)?.setImageResource(R.drawable.note_poster)

        // add task dialog
        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {

            // pass current view model
            NoteViewModel.instance = noteViewModel

            activity?.let {
                val intent = Intent(context, NewNoteActivity::class.java)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
                } else {
                    startActivity(intent)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val noteListView: RecyclerView = binding.noteListView
        val adapter = NoteAdapter() {
            // TODO remove note here
        }
        noteListView.adapter = adapter

        // listen to data change
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                noteViewModel.notes.collect {
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
}