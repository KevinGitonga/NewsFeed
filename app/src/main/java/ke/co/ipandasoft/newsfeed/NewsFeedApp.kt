/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:30 PM
 *
 */

package ke.co.ipandasoft.newsfeed

import androidx.multidex.MultiDexApplication
import ke.co.ipandasoft.newsfeed.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsFeedApp :MultiDexApplication(){


    override fun onCreate() {
        super.onCreate()
        initLogger()
        initKoinDi()
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