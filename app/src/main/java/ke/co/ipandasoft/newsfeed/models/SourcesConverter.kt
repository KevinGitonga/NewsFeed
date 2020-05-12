/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 4:27 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 4:27 PM
 *
 */

package ke.co.ipandasoft.newsfeed.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ke.co.ipandasoft.newsfeed.data.remote.responses.Source
import java.lang.reflect.Type

class SourcesConverter{

    val gson = Gson()

    val type: Type = object : TypeToken<Source?>() {}.type


    @TypeConverter
    fun fromSource(source: Source?): String{
        return gson.toJson(source,type)
    }

    @TypeConverter
    fun toSource(json: String?): Source {
        return gson.fromJson(json,type)
    }
}