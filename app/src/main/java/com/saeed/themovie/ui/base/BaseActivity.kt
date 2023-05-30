package com.saeed.themovie.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.saeed.themovie.R

abstract class BaseActivity : AppCompatActivity() {

    inline fun <reified F : BaseFragment> goToFragment(
        shouldReplace: Boolean = false,
        hasAnimation: Boolean = false,
        bundle: Bundle? = null
    ) {

        val instance: Fragment = F::class.java.newInstance()
        instance.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        if (shouldReplace) {
            transaction.replace(R.id.container, instance)
        } else {
            transaction.add(R.id.container, instance)
        }

        if (hasAnimation) {
            transaction.setCustomAnimations(
                R.animator.fade_in,
                R.animator.fade_out,
                R.animator.fade_in,
                R.animator.fade_out
            )
        }

        transaction.addToBackStack(F::class.java.javaClass.name)
            .commitAllowingStateLoss()
    }
}