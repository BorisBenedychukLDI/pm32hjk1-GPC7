package com.pamach.rimach.com.ApplicationPackage32hj1k

import android.app.Application

class Application32hjk1 : Application(), ApplicationContract32hjk1.ApplicationMethods32hjk1 {

    private lateinit var applicationContract32hjk1: ApplicationContract32hjk1.ApplicationSetuperMethods32hjk1

    override fun onCreate() {
        applicationContract32hjk1 = ApplicationSetuper32hjk1(this)
        applicationContract32hjk1.onesignalSetup32hjk1()
        super.onCreate()
    }

    override fun getContext32hkj1() = this
}