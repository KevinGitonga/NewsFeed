/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/5/20 12:27 PM
 *
 */

package ke.co.ipandasoft.newsfeed.di

import ke.co.ipandasoft.newsfeed.data.repository.NewsRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { NewsRepository(get())}

}