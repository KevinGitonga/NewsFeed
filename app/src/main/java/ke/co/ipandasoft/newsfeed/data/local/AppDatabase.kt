/*
 * *
 *  * Created by Kevin Gitonga on 5/8/20 1:22 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 1:22 PM
 *
 */

package ke.co.ipandasoft.newsfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ke.co.ipandasoft.newsfeed.models.NewsLocality

@Database(entities = [NewsLocality::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsLocalityDao(): NewsLocalityDao
}