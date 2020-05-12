/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 10:28 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 10:28 AM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.newsdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragment

class NewsDetailFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewsDetailFragment()
    }

    private lateinit var viewModel: NewsDetailViewModel

    override fun getLayoutId(): Int {
        return R.layout.news_detail_fragment
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }


}
