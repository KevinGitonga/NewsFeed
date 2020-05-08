/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:09 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(){

    /**
     * Whether the view is loaded
     */
    private var isViewPrepare = false
    /**
     * Has the data been loaded
     */
    private var hasLoadData = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }


    @SuppressLint("deprecated")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView()
        lazyLoadDataIfPrepared()

    }

    @SuppressLint("deprecated")
    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }


    /**
     * LOAD VIEW LAYOUT
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * initialization ViewI
     */
    abstract fun initView()

    /**
     * Lazy loading when fragment is only visible to user
     */
    abstract fun lazyLoad()

    override fun onDestroy() {
        super.onDestroy()
    }

}