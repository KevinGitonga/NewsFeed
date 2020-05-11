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

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun layoutId(): Int {
      return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        setupNavigationDrawer()
        setSupportActionBar(findViewById(R.id.toolbar))
        bindViews()

    }

    private fun bindViews() {
        val navController: NavController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration =
            AppBarConfiguration.Builder(R.id.newsHomeFragment)
                .setDrawerLayout(drawerLayout)
                .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout))
            .apply {
                setStatusBarBackground(R.color.colorPrimaryDark)
            }
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun start() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.homeNews){
            Toast.makeText(this,"NEWS FEED",Toast.LENGTH_SHORT)
        }

        return true
    }


}
