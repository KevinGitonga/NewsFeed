/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 12:03 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 12:03 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.data.local.ArticleDao
import ke.co.ipandasoft.newsfeed.data.local.NewsLocalityDao
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.data.remote.responses.ResultWrapper
import ke.co.ipandasoft.newsfeed.data.repository.NewsRepository
import ke.co.ipandasoft.newsfeed.models.NewsLocality
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsCategoriesViewModel(private val repository: NewsRepository,
                              private val newsLocalityDao: NewsLocalityDao,
                              private val articleDao: ArticleDao) : ViewModel() {

    private val recentNewsMutableData: MutableLiveData<List<Article>> = MutableLiveData()
    val recentNewsLiveData: LiveData<List<Article>>
        get() = recentNewsMutableData

    private val newsLocalityLiveLocal= MutableLiveData<NewsLocality>()
    val newsLocalityLocal: LiveData<NewsLocality>
        get() = newsLocalityLiveLocal


    fun getLocalizedNews(countryCode: String,category:String){
        viewModelScope.launch {
            when(val resultResponse =repository.getCategorizedNewsHeadLines(countryCode,category)) {
                is ResultWrapper.Success ->{
                    val latestNews=resultResponse.data
                    recentNewsMutableData.postValue(latestNews.articles)
                }
                is ResultWrapper.Error->{
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
            }
        }
    }

    fun getAppLocalityLocal(){
        viewModelScope.launch {
            val newLocalityLocal=newsLocalityDao.getAppLocality()
            Timber.e("NEWS LOCALITY LOCAL ${Gson().toJson(newLocalityLocal)}")
            newsLocalityLiveLocal.postValue(newLocalityLocal)
        }
    }

    fun saveBookmark(article: Article){
        viewModelScope.launch {
            articleDao.insertArticle(article)
        }
}
}
