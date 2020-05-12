/*
 * *
 *  * Created by Kevin Gitonga on 5/5/20 1:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 7:18 PM
 *
 */

package ke.co.ipandasoft.newsfeed.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.browser.customtabs.CustomTabsService
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

        var sPackageNameToUse: String? = null
        val STABLE_PACKAGE = "com.android.chrome"
        val BETA_PACKAGE = "com.chrome.beta"
        val DEV_PACKAGE = "com.chrome.dev"
        val LOCAL_PACKAGE = "com.google.android.apps.chrome"


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

        fun getPackageNameToUse(context: Context, url: String): String? {

            sPackageNameToUse?.let {
                return it
            }

            val pm = context.getPackageManager()

            val activityIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            val defaultViewHandlerInfo = pm.resolveActivity(activityIntent, 0)
            var defaultViewHandlerPackageName: String? = null

            defaultViewHandlerInfo?.let {
                defaultViewHandlerPackageName = it.activityInfo.packageName
            }

            val resolvedActivityList = pm.queryIntentActivities(activityIntent, 0)
            val packagesSupportingCustomTabs = ArrayList<String>()
            for (info in resolvedActivityList) {
                val serviceIntent = Intent()
                serviceIntent.action = CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
                serviceIntent.setPackage(info.activityInfo.packageName)

                pm.resolveService(serviceIntent, 0)?.let {
                    packagesSupportingCustomTabs.add(info.activityInfo.packageName)
                }
            }

            when {
                packagesSupportingCustomTabs.isEmpty() -> sPackageNameToUse = null
                packagesSupportingCustomTabs.size == 1 -> sPackageNameToUse = packagesSupportingCustomTabs.get(0)
                !TextUtils.isEmpty(defaultViewHandlerPackageName)
                        && !hasSpecializedHandlerIntents(context, activityIntent)
                        && packagesSupportingCustomTabs.contains(defaultViewHandlerPackageName) ->
                    sPackageNameToUse = defaultViewHandlerPackageName
                packagesSupportingCustomTabs.contains(STABLE_PACKAGE) -> sPackageNameToUse = STABLE_PACKAGE
                packagesSupportingCustomTabs.contains(BETA_PACKAGE) -> sPackageNameToUse = BETA_PACKAGE
                packagesSupportingCustomTabs.contains(DEV_PACKAGE) -> sPackageNameToUse = DEV_PACKAGE
                packagesSupportingCustomTabs.contains(LOCAL_PACKAGE) -> sPackageNameToUse = LOCAL_PACKAGE
            }
            return sPackageNameToUse
        }

        private fun hasSpecializedHandlerIntents(context: Context, intent: Intent): Boolean {
            try {
                val pm = context.getPackageManager()
                val handlers = pm.queryIntentActivities(
                    intent,
                    PackageManager.GET_RESOLVED_FILTER)
                if (handlers == null || handlers.size == 0) {
                    return false
                }
                for (resolveInfo in handlers) {
                    val filter = resolveInfo.filter ?: continue
                    if (filter.countDataAuthorities() == 0 || filter.countDataPaths() == 0) continue
                    if (resolveInfo.activityInfo == null) continue
                    return true
                }
            } catch (e: RuntimeException) {
            }
            return false
        }

        fun getNewsCategories():List<String>{

            return listOf("business","entertainment","technology","health","science","sports"
                ,"general")
        }
    }


}