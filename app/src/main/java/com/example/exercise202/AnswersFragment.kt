package com.example.exercise202

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnswersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private val question: TextView?
        get() = view?.findViewById(R.id.answers_title)

    private val correctAnswer: TextView?
        get() = view?.findViewById(R.id.correct_answer)

    fun setQuestionsData(questionId: Int) {
        when (questionId) {
            R.id.first_question -> {
                question?.text = getString(R.string.first_question)
                correctAnswer?.text = getString(R.string.first_answer)
            }
            R.id.second_question -> {
                question?.text = getString(R.string.second_question)
                correctAnswer?.text = getString(R.string.second_answer)
            }
            R.id.third_question -> {
                question?.text = getString(R.string.third_question)
                correctAnswer?.text = getString(R.string.third_answer)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionId = arguments?.getInt(QUESTION_ID, 0) ?: 0
        val rightAnswer = arguments?.getString(RIGHT_ANSWER_ID, "") ?: ""

        setQuestionsData(questionId)

        val listAnswers = listOf<Button>(
            view.findViewById(R.id.mercury_button),
            view.findViewById(R.id.venus_button),
            view.findViewById(R.id.earth_button),
            view.findViewById(R.id.mars_button),
            view.findViewById(R.id.jupiter_button),
            view.findViewById(R.id.saturn_button),
            view.findViewById(R.id.uranus_button),
            view.findViewById(R.id.neptune_button)
        )


        listAnswers.forEach { answer ->
            answer.setOnClickListener {
                if (answer.text == rightAnswer) {
                    correctAnswer?.isVisible = true
                }
            }
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnswersFragment.
         */
        // TODO: Rename and change types and number of parameters
        private const val QUESTION_ID = "QUESTION_ID"
        fun newInstance(questionId: Int) =
            AnswersFragment().apply {
                arguments = Bundle().apply {
                    putInt(QUESTION_ID, questionId)
                }
            }
    }
}