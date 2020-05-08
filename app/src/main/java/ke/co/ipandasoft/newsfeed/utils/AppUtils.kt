/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 7:18 PM
 *
 */

package ke.co.ipandasoft.newsfeed.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.text.TextUtils
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


//utils to provide app functionalities

class AppUtils private constructor() {


    init {
        throw Error("Do not need instantiate!")
    }

    companion object {

        private val DEBUG = true
        private val TAG = "AppUtils"
        private const val TIME_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssZ"


        fun clearCache(context: Context): Boolean {
            var result = context.cacheDir.deleteRecursively()
            Timber.e("DELETE CACHE  $result")
            return result
        }


        @SuppressLint("deprecated")
        fun getVerCode(context: Context): Int {
            var verCode = -1
            try {
                val packageName = context.packageName
                verCode = context.packageManager
                        .getPackageInfo(packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verCode
        }

        fun getLocation(context: Context):String{
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.networkCountryIso
        }



        val maxMemory: Long
            get() = Runtime.getRuntime().maxMemory() / 1024


        fun getVerName(context: Context): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager
                        .getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verName
        }






        fun getDateIso(str:String):Date {
            if (TextUtils.isEmpty(str))
            {
                return Date()
            }
            try
            {
                return SimpleDateFormat(TIME_FORMAT_WITH_TIMEZONE, Locale.ENGLISH).parse(str)
            }
            catch (e: ParseException) {
                e.printStackTrace()
                return Date()
            }
        }
    }


}