package com.example.mytodoapp.ui.note

import androidx.lifecycle.ViewModel
import com.example.mytodoapp.model.Note
import kotlinx.coroutines.flow.MutableStateFlow

class NoteViewModel : ViewModel() {

    companion object {
        var instance: NoteViewModel? = null
    }

    private val initialNotes = listOf(
        Note(1, "My Note 1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
        Note(2, "My Note 2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
        Note(3, "My Note 3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
        Note(4, "My Note 4", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from"),
        Note(5, "My Note 5", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
        Note(6, "My Note 6", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))

    val notes = MutableStateFlow<List<Note>>(initialNotes)
    var currentNote = MutableStateFlow<Note>(Note(0,"", ""))

    fun saveNote() {
        currentNote.value.id = (initialNotes.size + 1).toLong()
        val newNotes = ArrayList(initialNotes)
        newNotes.add(currentNote.value)
        notes.value = newNotes
    }

//    fun updateTitle(title: String) {
//        val newNote = currentNote.value.copy(
//            title = title
//        )
//        currentNote.value = newNote
//        // currentNote = MutableStateFlow<Note>(newNote)
//    }
//
//    fun updateNote(text: String) {
//        val newNote = currentNote.value.copy(
//            text = text
//        )
//        currentNote.value = newNote
//        // currentNote = MutableStateFlow<Note>(newNote)
//    }
}