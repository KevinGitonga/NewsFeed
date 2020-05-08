/*
 * *
 *  * Created by Kevin Gitonga on 5/8/20 10:59 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 10:59 AM
 *
 */

package ke.co.ipandasoft.newsfeed.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "newslocality")
data class NewsLocality(
    @PrimaryKey
    @SerializedName("countryCode")
    var countryCode: String,
    @SerializedName("countryName")
    var countryName: String?
)