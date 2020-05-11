/*
 * *
 *  * Created by Kevin Gitonga on 5/10/20 12:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/10/20 12:32 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.news_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NewsFragment : BaseFragment() {

    val newsFragmentViewModel by viewModel<NewsFragmentViewModel>()

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
                mRecyclerView.adapter=NewsAdapter(it)
            }
        })
    }
}