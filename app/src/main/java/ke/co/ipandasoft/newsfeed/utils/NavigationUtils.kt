/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/23/20 8:11 PM
 *
 */

package ke.co.ipandasoft.newsfeed.utils

import android.app.Activity
import android.content.Intent
import android.provider.SyncStateContract
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

//Navigation helper class
class NavigationUtils{

    companion object{

        fun navigate(activity: AppCompatActivity,javaClass: Class<out Activity>){
            val navigationIntent=Intent(activity,javaClass)
            activity.startActivity(navigationIntent)
        }


        fun navigateWithBundle(activity: AppCompatActivity,boolean: Boolean,newsUrl:String,newsTitle:String,javaClass: Class<out Activity>){
            val navigationIntent=Intent(activity,javaClass)
            navigationIntent.putExtra("isArticle",boolean)
            navigationIntent.putExtra("news_url",newsUrl)
            navigationIntent.putExtra("newsTitle",newsTitle)
            activity.startActivity(navigationIntent)
        }


    }

}