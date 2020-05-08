/*
 * *
 *  * Created by Kevin Gitonga on 5/8/20 1:27 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 1:27 PM
 *
 */

package ke.co.ipandasoft.newsfeed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ke.co.ipandasoft.newsfeed.models.NewsLocality

@Dao
interface NewsLocalityDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNewsLocality(newsLocality: NewsLocality)

        @Query("SELECT * from `newslocality`")
        suspend fun getAppLocality():NewsLocality

}