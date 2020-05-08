/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 10:34 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 10:34 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("source")
    var source: Source?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?
)