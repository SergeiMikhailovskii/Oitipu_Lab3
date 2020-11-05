package com.oitipu.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oitipu.lab4.databinding.LayoutQuizQuestionBinding

class QuizQuestionFragment : Fragment(R.layout.layout_quiz_question) {

    private lateinit var binding: LayoutQuizQuestionBinding

    var buttonClickCallback: ((String) -> Unit)? = null
    var buttonSaveCallback: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutQuizQuestionBinding.inflate(inflater)
        return binding.root
    }


    fun setQuestionData(quizQuestion: QuizQuestion, currentScore: Int) {
        binding.btnFirst.apply {
            setOnClickListener {
                buttonClickCallback?.invoke(this.text.toString())
            }
            text = quizQuestion.first
        }

        binding.btnSecond.apply {
            setOnClickListener {
                buttonClickCallback?.invoke(this.text.toString())
            }
            text = quizQuestion.second
        }

        binding.btnThird.apply {
            setOnClickListener {
                buttonClickCallback?.invoke(this.text.toString())
            }
            text = quizQuestion.third
        }

        binding.btnForth.apply {
            setOnClickListener {
                buttonClickCallback?.invoke(this.text.toString())
            }
            text = quizQuestion.fourth
        }

        binding.btnSave.setOnClickListener {
            fragmentManager?.let { it1 -> EnterNameBottomSheetFragment().apply {
                onSaveClick = {
                    buttonSaveCallback?.invoke(it)
                }
            }.show(it1, "SAVE_DLG") }
        }

        binding.tvQuestion.text = quizQuestion.question
        binding.tvScore.text = currentScore.toString()
    }

    fun updateFragmentData(quizQuestion: QuizQuestion, currentScore: Int) {
        binding.btnFirst.text = quizQuestion.first
        binding.btnSecond.text = quizQuestion.second
        binding.btnThird.text = quizQuestion.third
        binding.btnForth.text = quizQuestion.fourth
        binding.tvQuestion.text = quizQuestion.question
        binding.tvScore.text = currentScore.toString()
    }


}