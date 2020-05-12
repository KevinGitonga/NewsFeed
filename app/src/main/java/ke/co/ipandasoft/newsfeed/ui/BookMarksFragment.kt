/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 12:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 12:04 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragment
import ke.co.ipandasoft.newsfeed.ui.home.AdapterEventListener
import ke.co.ipandasoft.newsfeed.ui.home.NewsAdapter
import ke.co.ipandasoft.newsfeed.ui.splash.SplashViewModel
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import kotlinx.android.synthetic.main.book_marks_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class BookMarksFragment : BaseFragment(), AdapterEventListener {

     val bookMarksViewModel by viewModel<BookMarksViewModel>()
     private lateinit var newsAdapter: NewsAdapter
    private var articleList : MutableList<Article>? = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.book_marks_fragment
    }

    override fun initView() {
        multipleStatusView.showLoading()
    }

    override fun lazyLoad() {
       bookMarksViewModel.bookmarkNewsLive.observe(viewLifecycleOwner, Observer {
           if (it.isEmpty()){
               multipleStatusView.showEmpty()
           }else{
               articleList!!.addAll(it)
               multipleStatusView.showContent()
               mRecyclerView.layoutManager= LinearLayoutManager(requireContext())
               newsAdapter= NewsAdapter(articleList!!,this)
               mRecyclerView.adapter=newsAdapter
           }
       })
    }

    override fun onNewsClickListener(article: Article) {
        Timber.e("ITEM CLICKED ${article.author}")
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this!!.context!!, R.color.colorPrimary))
        builder.setShowTitle(true)
        builder.setStartAnimations(this!!.context!!, android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this!!.context!!, android.R.anim.fade_in, android.R.anim.fade_out)

        val customTabsIntent = builder.build()
        // check is chrom available
        val packageName = AppUtils.getPackageNameToUse(this!!.context!!, article.url!!)

        when {
            packageName != null -> {
                customTabsIntent.intent.setPackage(packageName)
                customTabsIntent.launchUrl(this!!.context!!, Uri.parse(article.url))
            }
            else -> {
                // if chrome not available open in web view
            }
        }
    }

    override fun onNewsLikeListener(position:Int,article: Article) {
        Timber.e("UNLIKE NEWS")
        article.isBookmark=false
        bookMarksViewModel.deleteArticle(article)
        Toast.makeText(context,"Article removed",Toast.LENGTH_SHORT).show()

        //UPDATE RECYCLER UI
        articleList!!.remove(article)
        mRecyclerView.removeViewAt(position);
        newsAdapter.notifyItemRemoved(position);
        newsAdapter.notifyItemRangeChanged(position, articleList!!.size)
        newsAdapter.notifyDataSetChanged()
    }


}
