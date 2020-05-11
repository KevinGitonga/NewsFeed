/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:20 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.gson.Gson
import ke.co.ipandasoft.newsfeed.BuildConfig
import ke.co.ipandasoft.newsfeed.R
import ke.co.ipandasoft.newsfeed.models.NewsLocality
import ke.co.ipandasoft.newsfeed.ui.base.BaseActivity
import ke.co.ipandasoft.newsfeed.ui.main.MainActivity
import ke.co.ipandasoft.newsfeed.utils.AppUtils
import ke.co.ipandasoft.newsfeed.utils.NavigationUtils
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import timber.log.Timber

class SplashActivity : BaseActivity() {

    private val splashViewModel by viewModel<SplashViewModel>()

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    //load data from bundles here
    override fun initData() {

    }

    //bind view elements here
    override fun initView() {
        splashViewModel.getAppLocalityLocal()
         observeLocalViewModels()
    }

    private fun observeLocalViewModels() {
        splashViewModel.newsLocalityLocal.observe(this, Observer {
            when {
                it!=null -> {
                 Timber.e("LOCAL SAVED LOC ${Gson().toJson(it)}")
                    navigateToMain()
                }
                else -> {
                    val locationCode=AppUtils.getLocation(this)
                    Timber.e("PHONE LOCATION $locationCode")
                    splashViewModel.checkCountry(locationCode)
                    observeRemoteViewModels()
                }
            }
        })
    }

    private fun observeRemoteViewModels() {
        splashViewModel.countryCodeLiveData.observe(this, Observer {
            Timber.e("LOCALIZED COUNTRY DATA"+ Gson().toJson(it))
            persistLocality(it)
        })
    }

    private fun persistLocality(it: NewsLocality?) {
       splashViewModel.saveLocalityLocal(it!!)
        navigateToMain()
    }

    private fun navigateToMain(){
        NavigationUtils.navigate(this,MainActivity::class.java)
    }

    //bind to business logic load data
    override fun start() {
    }


}
