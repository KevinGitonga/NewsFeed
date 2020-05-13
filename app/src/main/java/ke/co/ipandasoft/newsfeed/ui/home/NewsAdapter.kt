/*
 * *
 *  * Created by Kevin Gitonga on 5/11/20 3:49 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/11/20 3:49 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.databinding.ItemNewsLayoutBinding
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import timber.log.Timber

class NewsAdapter(private val newsArticles:List<Article>, private val  adapterEventListener: AdapterEventListener): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
      return NewsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
          R.layout.item_news_layout,parent,false))
    }

    override fun getItemCount(): Int {
       return newsArticles.size
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article=newsArticles[position]
        if (article.isBookmark!!){
            holder.itemNewsLayoutBinding.article=article
            holder.itemNewsLayoutBinding.bookmarkIv.setImageResource(R.drawable.ic_bookmarked_item)
            holder.itemNewsLayoutBinding.cardView.setOnClickListener{adapterEventListener.onNewsClickListener(article)}
            holder.itemNewsLayoutBinding.bookmarkIv.setOnClickListener{
                holder.itemNewsLayoutBinding.bookmarkIv.setImageResource(R.drawable.ic_bookmark_border)
                adapterEventListener.onNewsLikeListener(position,article)}
        }else{
            val articleTime=AppUtils.getDateIso(article.publishedAt!!)
            Timber.e("ARTICLE TIME $articleTime")
            article.publishedAt=AppUtils.getRelativeDateTimeString(articleTime).toString()
            holder.itemNewsLayoutBinding.article=article
            holder.itemNewsLayoutBinding.cardView.setOnClickListener{adapterEventListener.onNewsClickListener(article)}
            holder.itemNewsLayoutBinding.bookmarkIv.setOnClickListener{
                holder.itemNewsLayoutBinding.bookmarkIv.setImageResource(R.drawable.ic_bookmarked_item)
                adapterEventListener.onNewsLikeListener(position,article)}
        }

    }


    inner class NewsViewHolder(val itemNewsLayoutBinding: ItemNewsLayoutBinding):RecyclerView.ViewHolder(itemNewsLayoutBinding.root) {

    }
}