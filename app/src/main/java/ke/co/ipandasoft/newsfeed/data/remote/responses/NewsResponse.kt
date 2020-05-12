/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 10:34 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 10:34 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.remote.responses


import com.google.gson.annotations.SerializedName
import ke.co.ipandasoft.newsfeed.models.Article

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<Article>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?
)