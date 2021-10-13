package com.pamach.rimach.com.ApplicationPackage32hj1k

import com.onesignal.OneSignal
import com.pamach.rimach.com.URLStuff32hjk1.CODED_ONESIGNAL_32hjk1
import com.pamach.rimach.com.URLStuff32hjk1.decodeString32hjk1

class ApplicationSetuper32hjk1(private val applicationMethods32hjk1: ApplicationContract32hjk1.ApplicationMethods32hjk1) : ApplicationContract32hjk1.ApplicationSetuperMethods32hjk1 {


    override fun onesignalSetup32hjk1() {
        OneSignal.initWithContext(applicationMethods32hjk1.getContext32hkj1())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(decodeString32hjk1(CODED_ONESIGNAL_32hjk1))
    }


}