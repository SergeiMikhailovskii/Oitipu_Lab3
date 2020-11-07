package com.oitipu.lab4

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    addBackToStack: Boolean = false
) {
    var transactionFragment = fragment
    val existingFragment = supportFragmentManager.findFragmentByTag(fragment.tag)
    val fragmentTransaction = supportFragmentManager.beginTransaction()

    existingFragment?.let {
        transactionFragment = existingFragment
    }

    if (!supportFragmentManager.isStateSaved) {
        if (addBackToStack) {
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction
            .replace(frameId, transactionFragment, transactionFragment.name())
            .commit()
    }
}

fun Fragment.name(): String = this.javaClass.simpleName