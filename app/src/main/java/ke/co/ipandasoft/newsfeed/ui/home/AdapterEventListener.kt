/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 1:49 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 1:49 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.home

import ke.co.ipandasoft.newsfeed.models.Article

interface AdapterEventListener {
    fun onNewsClickListener(article: Article)

    fun onNewsLikeListener(position:Int,article: Article)
}