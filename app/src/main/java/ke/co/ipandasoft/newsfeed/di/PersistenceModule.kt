/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:34 PM
 *
 */

package ke.co.ipandasoft.newsfeed.di

import android.content.Context
import androidx.room.Room
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.data.local.AppDatabase
import ke.co.ipandasoft.newsfeed.data.local.NewsLocalityDao
import org.koin.dsl.module

object PersistenceModule {
    val persistenceModule = module {
      single { provideAppDatabase(get()) }

      single { provideLocalitiesDao(get()) }
    }

    private fun provideLocalitiesDao(appDatabase: AppDatabase):NewsLocalityDao {
         return appDatabase.newsLocalityDao()
    }

    private fun provideAppDatabase(context: Context):AppDatabase{
        return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,
            context.applicationContext.getString(R.string.app_database_name)).build()
    }
}




