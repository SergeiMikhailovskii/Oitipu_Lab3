package com.oitipu.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oitipu.lab4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentQuestion = 0
    private var currentScore = 0
    private var unsavedScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(createQuizQuestionFragment(), R.id.fragment_layout)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            var fragment: Fragment? = null

            when (it.itemId) {
                R.id.play -> {
                    fragment = createQuizQuestionFragment()
                }
                R.id.profile -> {
                    fragment = UserListFragment()
                }
            }

            fragment?.let { fr ->
                replaceFragment(fr, R.id.fragment_layout)
            }

            true
        }
    }

    private fun createQuizQuestionFragment() = QuizQuestionFragment().apply {
        currentQuestionNumber = currentQuestion
        currentScore = this@MainActivity.currentScore
        unsavedScore = this@MainActivity.unsavedScore
        saveScore = { current, unsaved ->
            currentScore = current
            unsavedScore = unsaved
        }
        saveCurrentQuestion = {
            currentQuestion = it
        }
    }

}