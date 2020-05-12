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
import androidx.room.TypeConverters
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.models.NewsLocality
import ke.co.ipandasoft.newsfeed.models.SourcesConverter

@Database(entities = [NewsLocality::class,Article::class], version = 1, exportSchema = false)
@TypeConverters(SourcesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsLocalityDao(): NewsLocalityDao

    abstract fun articleDao():ArticleDao
}