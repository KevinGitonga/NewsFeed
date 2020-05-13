/*
 * *
 *  * Created by Kevin Gitonga on 5/10/20 12:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/10/20 12:32 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.home

import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragment
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import kotlinx.android.synthetic.main.news_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NewsFragment : BaseFragment(), AdapterEventListener {

    val newsFragmentViewModel by viewModel<NewsFragmentViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun getLayoutId(): Int {
        return R .layout.news_fragment
    }

    override fun initView() {
        multipleStatusView.showLoading()
    }


    override fun lazyLoad() {
        newsFragmentViewModel.getAppLocalityLocal()
        newsFragmentViewModel.newsLocalityLocal.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                newsFragmentViewModel.getLocalizedNews(it.countryCode)
                observeLatestNews()
            }
        })
    }

    private fun observeLatestNews() {
        newsFragmentViewModel.recentNewsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                multipleStatusView.showContent()
                Timber.e("LATEST NEWS ${Gson().toJson(it)}")
                mRecyclerView.layoutManager=LinearLayoutManager(requireContext())
                newsAdapter= NewsAdapter(it,this)
                mRecyclerView.adapter=newsAdapter

            }
        })
    }

    override fun onNewsClickListener(article: Article) {
        Timber.e("ITEM CLICKED ${article.author}")
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this!!.requireContext(), R.color.toolbarBackground))
        builder.setShowTitle(true)
        builder.setStartAnimations(this!!.requireContext(), android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this!!.requireContext(), android.R.anim.fade_in, android.R.anim.fade_out)

        val customTabsIntent = builder.build()
        // check is chrom available
        val packageName = AppUtils.getPackageNameToUse(this!!.requireContext(), article.url!!)

        when {
            packageName != null -> {
                customTabsIntent.intent.setPackage(packageName)
                customTabsIntent.launchUrl(this!!.requireContext(), Uri.parse(article.url))
            }
            else -> {
                // if chrome not available open in web view
            }
        }
    }

    override fun onNewsLikeListener(position:Int,article: Article) {
        article.isBookmark=true
        newsFragmentViewModel.saveBookmark(article)
        Toast.makeText(context,"Article saved for Later",Toast.LENGTH_SHORT).show()
    }
}