package com.example.mytodoapp.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.databinding.ListItemNoteBinding
import com.example.mytodoapp.model.Note

class NoteAdapter(val onNoteDeleted: (Long) -> Unit) :
    ListAdapter<Note, RecyclerView.ViewHolder>(NoteDiffCallback()) {

    lateinit var binding: ListItemNoteBinding

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = getItem(position)
        (holder as NoteViewHolder).bind(note)

        // TODO remove note
//        binding.setCheckboxClickListener {
//            val checked = (it as CheckBox).isChecked
//            if (checked) {
//                // if unchecked, remove from list
//                onNoteDeleted(note.id)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = ListItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }
}

class NoteViewHolder(private val binding: ListItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.note = note
        binding.executePendingBindings()
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.text == newItem.text
    }
}