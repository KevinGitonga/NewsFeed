/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 1:15 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.data.local.NewsLocalityDao
import ke.co.ipandasoft.newsfeed.data.remote.responses.ResultWrapper
import ke.co.ipandasoft.newsfeed.data.repository.NewsRepository
import ke.co.ipandasoft.newsfeed.models.NewsLocality
import kotlinx.coroutines.launch
import timber.log.Timber

//dummy view model for splash
class SplashViewModel(private val newsRepository: NewsRepository,
                      private val newsLocalityDao: NewsLocalityDao) :ViewModel(){

    private val countryCodeLive= MutableLiveData<NewsLocality>()
    val countryCodeLiveData: LiveData<NewsLocality>
        get() = countryCodeLive

    private val newsLocalityLiveLocal=MutableLiveData<NewsLocality>()
    val newsLocalityLocal:LiveData<NewsLocality>
      get() = newsLocalityLiveLocal

    private val isLoading=MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean>
            get() = isLoading


    fun checkCountry(countryCode:String) {
        viewModelScope.launch{
            isLoading.postValue(true)
            when (val newsCountriesResponse=newsRepository.getApiNewsCountries()) {
                is ResultWrapper.Success -> {
                     isLoading.postValue(false)
                    Timber.e("SUCCESS RESPONSE ${Gson().toJson(newsCountriesResponse.data)}")

                    for (NewsLocality in newsCountriesResponse.data){
                        if (countryCode == NewsLocality.countryCode){
                            Timber.e("COUNTRY CODE MATCHING ${Gson().toJson(NewsLocality)}")
                            countryCodeLive.postValue(NewsLocality)
                        }else{
                            Timber.e("COUNTRY OUT OF NEWSAPI SCOPE FOR NOW")
                            countryCodeLive.postValue(NewsLocality("us","United States"))
                        }
                    }
                }
                is ResultWrapper.Error -> {
                    Timber.e("ERROR RESPONSE ${Gson().toJson(newsCountriesResponse.exception)}")
                }
            }

        }
    }

    fun saveLocalityLocal(newsLocality: NewsLocality){
        viewModelScope.launch {
            newsLocalityDao.insertNewsLocality(newsLocality)
        }

    }

    fun getAppLocalityLocal(){
        viewModelScope.launch {
            val newLocalityLocal=newsLocalityDao.getAppLocality()
            Timber.e("NEWS LOCALITY LOCAL ${Gson().toJson(newLocalityLocal)}")
            newsLocalityLiveLocal.postValue(newLocalityLocal)
        }
    }


}