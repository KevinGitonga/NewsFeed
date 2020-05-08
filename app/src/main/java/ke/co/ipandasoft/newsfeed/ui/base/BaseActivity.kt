/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 11:27 AM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
    }


    /**
     *  GET LAYOUT
     */
    abstract fun layoutId(): Int

    /**
     * INITDATA
     */
    abstract fun initData()

    /**
     * INIT ViewS
     */
    abstract fun initView()

    /**
     * START ACTIVITY
     */
    abstract fun start()

}