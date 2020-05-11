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
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import org.ocpsoft.prettytime.PrettyTime
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

        //"publishedAt":"2020-05-11T08:24:14Z
        private const val TIME_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss"


        fun getRelativeDateTimeString(date: Date):CharSequence {
            var locale = Locale.getDefault()
            if (locale.language == "kk" && Build.VERSION.SDK_INT >= 21)
            {
                locale = Locale.forLanguageTag("ru")
            }
            val prettyTime = PrettyTime(locale)
            return prettyTime.format(date)
        }



        fun getLocation(context: Context):String{
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.networkCountryIso
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
                Timber.e("exception date"+e.localizedMessage)
                return Date()
            }
        }
    }


}