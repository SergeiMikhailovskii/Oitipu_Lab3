package com.oitipu.lab4

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.oitipu.lab4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentQuestionNumber = 0
    private var currentScore = 0
    private var questions: MutableList<QuizQuestion>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityMainBinding.inflate(layoutInflater)

        val quizQuestionFragment = QuizQuestionFragment().apply {
            buttonClickCallback = {
                if (it == questions?.get(currentQuestionNumber)?.right) {
                    currentScore++
                    Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT).show()
                }
                currentQuestionNumber++

                if (currentQuestionNumber >= (questions?.size ?: 0)) {
                    currentQuestionNumber = 0
                }

                questions?.get(currentQuestionNumber)?.let { question ->
                    this.updateFragmentData(question, currentScore)
                }
            }
        }

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_layout, quizQuestionFragment)
        }.commit()

        val ref = Firebase.database.reference
        ref.child("questions").addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e(MainActivity::class.simpleName, error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                questions = snapshot.getValue<MutableList<QuizQuestion>>()

                questions?.get(currentQuestionNumber)?.let {
                    quizQuestionFragment.setQuestionData(it, currentScore)
                }
            }

        })
    }

}