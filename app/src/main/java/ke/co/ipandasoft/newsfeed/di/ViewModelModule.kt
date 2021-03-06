/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:03 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:27 PM
 *
 */

package ke.co.ipandasoft.newsfeed.di

import ke.co.ipandasoft.newsfeed.ui.BookMarksViewModel
import ke.co.ipandasoft.newsfeed.ui.categories.NewsCategoriesViewModel
import ke.co.ipandasoft.newsfeed.ui.home.NewsFragmentViewModel
import ke.co.ipandasoft.newsfeed.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
  viewModel { SplashViewModel(get(),get()) }
  viewModel { NewsFragmentViewModel(get(),get(),get()) }
  viewModel { NewsCategoriesViewModel(get(),get(),get()) }
  viewModel { BookMarksViewModel(get())}
}