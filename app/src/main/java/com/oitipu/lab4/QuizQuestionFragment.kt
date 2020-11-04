package com.oitipu.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oitipu.lab4.databinding.LayoutQuizQuestionBinding

class QuizQuestionFragment : Fragment(R.layout.layout_quiz_question) {

    private lateinit var binding: LayoutQuizQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutQuizQuestionBinding.inflate(inflater)
        return binding.root
    }

    fun setQuestionData(quizQuestion: QuizQuestion) {
        binding.btnFirst.text = quizQuestion.first
        binding.btnSecond.text = quizQuestion.second
        binding.btnThird.text = quizQuestion.third
        binding.btnForth.text = quizQuestion.fourth
        binding.tvQuestion.text = quizQuestion.question
    }

}