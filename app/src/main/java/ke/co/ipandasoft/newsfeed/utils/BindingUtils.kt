/*
 * *
 *  * Created by Kevin Gitonga on 5/11/20 3:39 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/11/20 3:39 PM
 *
 */

package ke.co.ipandasoft.newsfeed.utils

import android.R
import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


 @BindingAdapter("coverImage")
 fun loadImage(imageView: ImageView, imageURL: String?) {
        Glide.with(imageView.context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .circleCrop()
            )
            .load(imageURL)
            .centerCrop()
            .into(imageView)
    }