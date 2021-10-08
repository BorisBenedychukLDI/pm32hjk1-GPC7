package com.pamach.rimach.com.ApplicationPackage32hj1k

import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.pamach.rimach.com.URLStuff32hjk1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationSetuper32hjk1(private val applicationMethods32hjk1: ApplicationContract32hjk1.ApplicationMethods32hjk1) : ApplicationContract32hjk1.ApplicationSetuperMethods32hjk1 {

    override fun appsFlyerSetup32hjk1() {
        MobileAds.initialize(applicationMethods32hjk1.getContext32hkj1())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                AID32hjk1 =
                    AdvertisingIdClient.getAdvertisingIdInfo(applicationMethods32hjk1.getContext32hkj1())?.id
            } catch (e2wlkzq1e: Exception) {

            }
        }
    }

    override fun onesignalSetup32hjk1() {
        OneSignal.initWithContext(applicationMethods32hjk1.getContext32hkj1())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(decodeString32hjk1(CODED_ONESIGNAL_32hjk1))
    }

    override fun mobileAdsSetup34hjk1() {
        val conversionListener32hjk1 = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                p0?.run {
                    status32hjk1 =
                        if (getValue(APPSFLYER_STATUS_TAG_32hjk1).toString() == "Organic") "Organic" else "Non-organic"

                    getValue(APPSFLYER_CAMPAIGN_TAG_32hjk1)
                        .toString()
                        .split("||").drop(1)
                        .map {
                            it.split(":")[1]
                        }.let { list32hjk1 ->
                            key32hjk1 =
                                if (list32hjk1.isNotEmpty()) list32hjk1[0] else "empty"
                            sub232hjk1 =
                                if (list32hjk1.size >= 2) list32hjk1[1] else "empty"
                            sub332hjk1 =
                                if (list32hjk1.size >= 3) list32hjk1[2] else "empty"
                        }


                    mediaSource32hjk1 = getValue(APPSFLYER_MEDIA_SOURCE_TAG_32hjk1).toString()
                    afChannel32hjk1 = getValue(APPSFLYER_AF_CHANNEL_TAG_32hjk1).toString()
                    adGroup32hjk1 = getValue(APPSFLYER_ADGROUP_TAG_32hjk1).toString()
                    adSet32hjk1 = getValue(APPSFLYER_ADSET_TAG_32hjk1).toString()

                }
            }

            override fun onConversionDataFail(p0: String?) {
            }

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(p0: String?) {
            }
        }
        AppsFlyerLib.getInstance().run {
            uid32hjk1 = getAppsFlyerUID(applicationMethods32hjk1.getContext32hkj1())
            init(
                decodeString32hjk1(CODE_APPSFLYER_32hjk1),
                conversionListener32hjk1,
                applicationMethods32hjk1.getContext32hkj1()
            )
            start(applicationMethods32hjk1.getContext32hkj1())
        }
    }

}