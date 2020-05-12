/*
 * *
 *  * Created by Kevin Gitonga on 5/7/20 11:00 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 11:00 AM
 *
 */

package ke.co.ipandasoft.newsfeed.data.repository

import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.BuildConfig
import ke.co.ipandasoft.newsfeed.constants.Constants
import ke.co.ipandasoft.newsfeed.data.remote.ApiService
import ke.co.ipandasoft.newsfeed.data.remote.responses.NewsResponse
import ke.co.ipandasoft.newsfeed.data.remote.responses.ResultWrapper
import ke.co.ipandasoft.newsfeed.models.NewsLocality
import timber.log.Timber
import java.lang.Exception

class NewsRepository(private val apiService: ApiService):Repository {

    override suspend fun getNewsHeadLines(countryCode: String):ResultWrapper<NewsResponse>{
         return try {
             val dataResponse=apiService.loadHeadlines(countryCode)
             var newsResponse:NewsResponse?=null
             when {
                 dataResponse.isSuccessful -> {
                     Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                     newsResponse= dataResponse.body()!!
                 }
                 else -> {
                     Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                 }
             }
             return ResultWrapper.Success(newsResponse!!)

        }catch (exception:Exception){

            val errorResponse=ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getCategorizedNewsHeadLines(countryCode: String,category:String):ResultWrapper<NewsResponse>{
        return try {
            val dataResponse=apiService.loadCategoryHeadlines(countryCode,category)
            var newsResponse:NewsResponse?=null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    newsResponse= dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(newsResponse!!)

        }catch (exception:Exception){

            val errorResponse=ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getApiNewsCountries(): ResultWrapper<List<NewsLocality>>{
        return try {
            val countriesResponse=apiService.getNewsApiCountries(BuildConfig.API_NEWS_COUNTRIES+Constants.API_NEWS_CATEGORY_ENDPOINT)
            val countriesList:MutableList<NewsLocality> = mutableListOf()
            when {
                countriesResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(countriesResponse.body()!!.size)}")
                    countriesList.addAll(countriesResponse.body()!!)
                }
                else -> {
                    Timber.e("Error RESP ${Gson().toJson(countriesResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(countriesList)
        }catch (exception:Exception){
            val errorResponse=ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }
}