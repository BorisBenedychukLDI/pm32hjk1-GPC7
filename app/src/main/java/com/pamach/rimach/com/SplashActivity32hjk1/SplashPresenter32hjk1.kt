package com.pamach.rimach.com.SplashActivity32hjk1

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
                finalString32jkl1 = decodeString32hjk1(CODE_BINOM_32hjk1)

                splashActivityMethods32hjk.startNewIntentWithURL32hjk1(
                    finalString32jkl1 ?: return@launch
                )
            }
        }
    }
}

