/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 10:37 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 10:37 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.repository

import ke.co.ipandasoft.newsfeed.data.remote.responses.NewsResponse
import ke.co.ipandasoft.newsfeed.data.remote.responses.ResultWrapper
import ke.co.ipandasoft.newsfeed.models.NewsLocality


interface Repository{

    suspend fun getNewsHeadLines(countryCode:String):ResultWrapper<NewsResponse>

    suspend fun  getApiNewsCountries():ResultWrapper<List<NewsLocality>>
}