/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 3:57 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 10:38 AM
 *
 */

package ke.co.ipandasoft.newsfeed.models


import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import ke.co.ipandasoft.newsfeed.data.remote.responses.Source

@Entity(tableName = "newsArticle")
data class Article(
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @PrimaryKey
    @SerializedName("publishedAt")
    var publishedAt: String,
    @SerializedName("source")
    var source: Source?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    var isBookmark:Boolean=false
)