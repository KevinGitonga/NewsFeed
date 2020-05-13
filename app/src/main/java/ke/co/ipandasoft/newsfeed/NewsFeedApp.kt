/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:30 PM
 *
 */

package ke.co.ipandasoft.newsfeed

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import ke.co.ipandasoft.newsfeed.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsFeedApp :MultiDexApplication(){


    override fun onCreate() {
        super.onCreate()
        initLogger()
        renderTheme()
        initKoinDi()
    }

    private fun renderTheme() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref=preferences.getString(getString(
            R.string.app_theme_key),getString(R.string.system_default_value))
        Timber.e("SELECTED THEME $themePref")

        when {
            themePref.equals(getString(R.string.system_default_value)) -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            themePref.equals(getString(R.string.light_theme_value)) -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            themePref.equals(getString(R.string.dark_theme_value)) -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }


    //init koin modules
    private fun initKoinDi() {
        startKoin {
            androidContext(this@NewsFeedApp)
            modules(NetModule.networkModule)
            modules(PersistenceModule.persistenceModule)
            modules(repositoryModule)
            modules(appModule)
            modules(viewModelModule)
        }
    }

    //init timber for logging
    private fun initLogger() {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

}