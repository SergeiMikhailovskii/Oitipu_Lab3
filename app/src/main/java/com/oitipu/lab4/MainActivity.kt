package com.oitipu.lab4

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val quizQuestionFragment = QuizQuestionFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_layout, quizQuestionFragment)
        }.commit()

        val ref = Firebase.database.reference
        ref.child("questions").addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e(MainActivity::class.simpleName, error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val quizQuestions = snapshot.getValue<MutableList<QuizQuestion>>()

                quizQuestions?.get(currentQuestionNumber)?.let {
                    quizQuestionFragment.setQuestionData(it)
                }
            }

        })
    }

}