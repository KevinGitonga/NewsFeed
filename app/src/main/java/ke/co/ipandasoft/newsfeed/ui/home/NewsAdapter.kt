/*
 * *
 *  * Created by Kevin Gitonga on 5/11/20 3:49 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/11/20 3:49 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.data.remote.responses.Article
import ke.co.ipandasoft.newsfeed.databinding.ItemNewsBinding
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import timber.log.Timber

class NewsAdapter(private val newsArticles:List<Article>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
      return NewsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
          R.layout.item_news,parent,false))
    }

    override fun getItemCount(): Int {
       return newsArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article=newsArticles[position]
        val articleTime=AppUtils.getDateIso(article.publishedAt!!)
        Timber.e("ARTICLE TIME $articleTime")
        article.publishedAt=AppUtils.getRelativeDateTimeString(articleTime).toString()
        holder.itemNewsBinding.article=article

    }


    inner class NewsViewHolder(val itemNewsBinding: ItemNewsBinding):RecyclerView.ViewHolder(itemNewsBinding.root) {

    }
}