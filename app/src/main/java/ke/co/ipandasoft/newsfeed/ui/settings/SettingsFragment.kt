/*
 * *
 *  * Created by Kevin Gitonga on 5/12/20 12:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/12/20 12:06 PM
 *
 */

package ke.co.ipandasoft.newsfeed.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat
import ke.co.ipandasoft.newsfeed.R
import timber.log.Timber


class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Timber.e("PREFS CHANGE $key")
        Timber.e("SHARED PREFS DATA  ${sharedPreferences!!.all}")
        val themePreferenceKey = getString(R.string.app_theme_key)
        if (key == themePreferenceKey) {
            val themePreference = findPreference<Preference>(themePreferenceKey)
            val selectedOption = sharedPreferences.getString(themePreferenceKey,getString(R.string.system_default_value))
            themePreference?.summary = selectedOption

            when (selectedOption) {
                getString(R.string.light_theme_value) -> changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
                getString(R.string.dark_theme_value) -> changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
                getString(R.string.system_default_value) -> changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

    }

}

    private fun changeTheme(modeNightNo: Int) {
        AppCompatDelegate.setDefaultNightMode(modeNightNo)
    }
}




