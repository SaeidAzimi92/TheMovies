package com.saeed.themovie.ui.main

import android.os.Bundle
import android.view.Menu
import com.saeed.themovie.R
import com.saeed.themovie.ui.base.BaseActivity
import com.saeed.themovie.ui.main.fragments.MainFragment



class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        goToFragment<MainFragment>(shouldReplace = true)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1)
            finish()
        super.onBackPressed()
    }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}