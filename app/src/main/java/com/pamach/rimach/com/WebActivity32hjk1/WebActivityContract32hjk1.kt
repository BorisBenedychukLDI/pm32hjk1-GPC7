package com.pamach.rimach.com.WebActivity32hjk1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.webkit.WebView
import kotlinx.coroutines.Job
import java.io.File

interface WebActivityContract32hjk1 {
    interface WebActivityMethods32hjk1 {
        fun giveWebView32hjk1(): WebView
        fun giveSharedPreffs32hjk1 (): SharedPreferences
        fun checkForPermissionPresence32hjk1 ()
        fun contextGiver32hjk1(): Context
        fun startActivityForResult32hjk1 (intent32hjk1: Intent)
        fun getIntentURL32hjk1 (): String?
        fun handleNetworkMissing32hjk1 ()
    }

    interface WebActivityPresenterMethods32hjk1 {
        fun setupWebView32hjk1 ()
        fun setUpWebViewClient32hjk1 ()
        fun setUptWebChromeClient32hjk1 ()
        fun loadTheOne32hjk1 ()
        fun internetCheckWork32hjk1 ()
        fun isNetworkIsPresent32hjk1 (): Boolean
        fun stopChecking32hjk1 ()
    }
}