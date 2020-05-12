/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 12:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 12:04 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.data.local.ArticleDao
import ke.co.ipandasoft.newsfeed.models.Article
import kotlinx.coroutines.launch
import timber.log.Timber

class BookMarksViewModel(private val articleDao: ArticleDao) : ViewModel() {

    private val bookmarkNews: MutableLiveData<List<Article>> = MutableLiveData()
    val bookmarkNewsLive:LiveData<List<Article>>
        get()  = bookmarkNews

    init {
        loadBookmarkNews()
    }

    private fun loadBookmarkNews() {
        viewModelScope.launch {
            val newsData=articleDao.getLocalArticles()
            Timber.e("DATA FROM DB ${Gson().toJson(newsData)}")
            bookmarkNews.postValue(newsData)
        }

    }

    fun deleteArticle(article: Article){
        viewModelScope.launch {
            articleDao.deleteArticle(article)
        }
    }

}
