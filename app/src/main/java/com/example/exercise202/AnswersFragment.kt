package com.example.exercise202

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation


class AnswersFragment : Fragment() {

//    private val question: TextView?
//        get() = view?.findViewById(R.id.questions_title)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answers, container, false)
    }

//    fun setQuestionsData(questionId: Int) {
//        when (questionId) {
//            R.id.first_question -> {
//                question?.text = getString(R.string.first_question)
//            }
//            R.id.second_question -> {
//                question?.text = getString(R.string.second_question)
//            }
//            R.id.third_question -> {
//                question?.text = getString(R.string.third_question)
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val questionId = arguments?.getInt(QUESTION_ID, 0) ?: 0
//        setQuestionsData(questionId)
        val fragmentBundle = Bundle()
        view?.findViewById<View>(R.id.mercury_button)?.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.questions_action, fragmentBundle)
        }
    }

//    companion object {
//        private const val QUESTION_ID = "QUESTION_ID"
//        fun newInstance(questionId: Int) =
//            AnswersFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(QUESTION_ID, questionId)
//                }
//            }
//    }
}