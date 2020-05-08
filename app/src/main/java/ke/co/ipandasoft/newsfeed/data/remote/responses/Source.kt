/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 10:34 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 10:34 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    var id: Any?,
    @SerializedName("name")
    var name: String?
)