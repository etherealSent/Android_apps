package com.example.exercise202

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

class QuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questions = listOf<View>(
            view.findViewById(R.id.first_question),
            view.findViewById(R.id.second_question),
            view.findViewById(R.id.third_question)
        )

        questions[0].setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.answers_id_action)
        }

//        questions.forEach { question ->
////            val fragmentBundle = Bundle()
////            fragmentBundle.putInt(QUESTION_ID, question.id)
//
//            question.setOnClickListener {
//                Navigation.createNavigateOnClickListener(R.id.answers_id_action)
//            }
//        }
    }
}