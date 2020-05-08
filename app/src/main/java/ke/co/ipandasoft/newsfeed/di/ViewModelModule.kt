/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:03 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:27 PM
 *
 */

package ke.co.ipandasoft.newsfeed.di

import ke.co.ipandasoft.newsfeed.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
  viewModel { SplashViewModel(get(),get()) }
}