/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 12:03 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 12:03 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.categories

import androidx.fragment.app.Fragment

import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragment
import ke.co.ipandasoft.newsfeed.ui.base.BaseFragmentAdapter
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import kotlinx.android.synthetic.main.news_categories_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsCategoriesFragment : BaseFragment() {

    private var newsCategoryList: List<String> = arrayListOf()
    private val newsCategoriesViewModel by viewModel<NewsCategoriesViewModel>()
    private val tabList = ArrayList<String>()
    private val fragments = ArrayList<Fragment>()


    override fun getLayoutId(): Int {
        return R.layout.news_categories_fragment
    }

    override fun initView() {
        newsCategoryList=AppUtils.getNewsCategories()
        for (String in newsCategoryList){
            tabList.add(String)
            fragments.add(NewsCategoryListFragment.getInstance(String))
        }

    }

    override fun lazyLoad() {
        viewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit=4
        viewPager.currentItem = 2
    }


}
