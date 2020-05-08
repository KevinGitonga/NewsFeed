/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 11:27 AM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.base

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class BaseFragmentAdapter : FragmentPagerAdapter {

    /**
     *  BASE FRAGMENT ADAPTER TO BIND FRAGMENTS TO VIEW PAGER
     */

    private var fragmentList: List<Fragment>? = ArrayList()
    private var mTitles: List<String>? = null

    @SuppressLint("deprecated")
    constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    @SuppressLint("deprecated")
    constructor(fm: FragmentManager, fragmentList: List<Fragment>, mTitles: List<String>) : super(fm) {
        this.mTitles = mTitles
        setFragments(fm, fragmentList, mTitles)
    }


    @SuppressLint("CommitTransaction")
    private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, mTitles: List<String>) {
        this.mTitles = mTitles
        if (this.fragmentList != null) {
            val ft = fm.beginTransaction()
            fragmentList?.forEach {
                ft.remove(it)
            }
            ft?.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (null != mTitles) mTitles!![position] else ""
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }

}