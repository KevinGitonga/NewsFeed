/*
 * *
 *  * Created by Kevin Gitonga on 5/8/20 7:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 7:51 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){
    private var mExitTime: Long = 0

    override fun layoutId(): Int {
      return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {

        bindViews()

    }

    private fun bindViews() {
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.newsHomeFragment, R.id.newsCategoriesFragment,
            R.id.bookMarksFragment, R.id.settingsFragment))

        val navController: NavController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavBar.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp()
                || super.onSupportNavigateUp()
    }



    override fun start() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()

            }
        else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(this,"Click Again To Exit",Toast.LENGTH_SHORT)
            }
    }



}
