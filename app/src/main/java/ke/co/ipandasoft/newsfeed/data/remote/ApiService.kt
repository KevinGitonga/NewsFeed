/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 9:01 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 9:01 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.remote

import ke.co.ipandasoft.newsfeed.data.remote.responses.NewsResponse
import ke.co.ipandasoft.newsfeed.models.NewsLocality
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun loadHeadlines(@Query("country") countryCode:String):Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun loadAllNews():Response<NewsResponse>

    @GET("/v2/sources")
    suspend fun getSourceBased():Response<NewsResponse>

    @GET
    suspend fun getNewsApiCountries(@Url url:String):Response<List<NewsLocality>>
}