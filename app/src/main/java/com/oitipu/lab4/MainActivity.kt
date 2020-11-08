package com.oitipu.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.oitipu.lab4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentQuestion = 0
    private var currentScore = 0
    private var unsavedScore = 0

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()

        firebaseAnalytics = Firebase.analytics

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(createQuizQuestionFragment(), R.id.fragment_layout)
        }

        binding.viewAd.loadAd(adRequest)
        binding.viewAd.adListener = object : AdListener() {

            override fun onAdClicked() {
                super.onAdClicked()
                firebaseAnalytics.sendEvent("ad_clicked")
            }

            override fun onAdFailedToLoad(p0: LoadAdError?) {
                super.onAdFailedToLoad(p0)
                firebaseAnalytics.sendEvent("failed_to_load")
            }

            override fun onAdClosed() {
                super.onAdClosed()
                firebaseAnalytics.sendEvent("ad_closed")
            }

            override fun onAdOpened() {
                super.onAdOpened()
                firebaseAnalytics.sendEvent("ad_opened")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                firebaseAnalytics.sendEvent("ad_loaded")
            }
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