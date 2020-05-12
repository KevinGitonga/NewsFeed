/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 3:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 3:04 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.categories

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.models.Article
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragment
import ke.co.ipandasoft.newsfeed.ui.home.AdapterEventListener
import ke.co.ipandasoft.newsfeed.ui.home.NewsAdapter
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import kotlinx.android.synthetic.main.news_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NewsCategoryListFragment : BaseFragment(), AdapterEventListener {

    val newsCategoriesViewModel by viewModel<NewsCategoriesViewModel>()
    private lateinit var newsAdapter: NewsAdapter
    private var newsCategory:String?=null

    companion object{
        fun getInstance(newsCategory: String):NewsCategoryListFragment{
            val fragment = NewsCategoryListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.newsCategory=newsCategory
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R .layout.news_fragment
    }

    override fun initView() {
        multipleStatusView.showLoading()
    }


    override fun lazyLoad() {
        newsCategoriesViewModel.getAppLocalityLocal()
        newsCategoriesViewModel.newsLocalityLocal.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                newsCategoriesViewModel.getLocalizedNews(it.countryCode, this!!.newsCategory!!)
                observeLatestNews()
            }
        })
    }

    private fun observeLatestNews() {
        newsCategoriesViewModel.recentNewsLiveData.observe(viewLifecycleOwner, Observer {
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
        article.isBookmark=true
        newsCategoriesViewModel.saveBookmark(article)
        Toast.makeText(context,"Article saved for Later",Toast.LENGTH_SHORT).show()
    }
}