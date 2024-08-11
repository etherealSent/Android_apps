package com.example.exercise202

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteListAdapter(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val noteList = mutableListOf<Note>()

    fun replaceData(newNoteList: List<Note>) {
        noteList.clear()
        noteList.addAll(newNoteList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(inflater.inflate(R.layout.view_note_item, parent, false))
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    inner class NoteViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val noteTextView =
            containerView.findViewById<TextView>(R.id.note_item_text)

        fun bind(note: Note) {
            noteTextView.text = "Note: ${note.text}"
        }
    }
}

