package com.example.exercise202

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class CountNotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_count_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(requireActivity()).get(CountNotesViewModel::class.java)
        viewModel.getCountNotes().observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.notes_number).text = getString(R.string.note_number, it)
        })

        viewModel.getCountNotes().observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.notes_number).text = getString(R.string.note_number, it)
        }

        view.findViewById<Button>(R.id.add_note_button).setOnClickListener {
            viewModel.addNote(
                view.findViewById<EditText>(R.id.note_text).text.toString()
            )
        }

    }

}