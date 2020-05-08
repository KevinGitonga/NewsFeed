/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 10:30 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 10:30 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.remote.responses


sealed class ResultWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    data class Error(val exception: Exception) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}