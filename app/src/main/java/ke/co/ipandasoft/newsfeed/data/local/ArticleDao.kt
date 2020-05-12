/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 4:00 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 4:00 PM
 *
 */

package ke.co.ipandasoft.newsfeed.data.local

import androidx.room.*
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.models.NewsLocality

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * from `newsArticle`")
    suspend fun getLocalArticles(): List<Article>

    @Delete
    suspend fun deleteArticle(article: Article)
}